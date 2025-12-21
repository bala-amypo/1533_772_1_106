package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Location;
import com.example.demo.entity.Sensor;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.SensorRepository;
import com.example.demo.service.SensorService;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Sensor createSensor(Long locationId, Sensor sensor) {

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));


        if (sensor.getSensorType() == null || sensor.getSensorType().isEmpty()) {
            throw new IllegalArgumentException("sensorType is required");
        }

        sensor.setLocationId(location.getId());


        if (sensor.getInstalledAt() == null) {
            sensor.setInstalledAt(LocalDateTime.now());
        }
        if (sensor.getIsActive() == null) {
            sensor.setIsActive(true);
        }

        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor getSensor(Long id) {
        return sensorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor not found"));
    }

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }
}
