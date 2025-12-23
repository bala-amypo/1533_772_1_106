package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Sensor;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.repository.SensorRepository;
import com.example.demo.service.SensorReadingService;

@Service
public class SensorReadingServiceImpl implements SensorReadingService {

    @Autowired
    private SensorReadingRepository readingRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public SensorReading submitReading(Long sensorId, SensorReading reading) {

     
        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found"));

    
        if ( reading.getReadingValue() == 0) {
            throw new IllegalArgumentException("readingvalue");
        }

        
        if (reading.getReadingTime() != null &&
            reading.getReadingTime().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Reading time cannot be in future");
        }

     
        reading.setSensorId(sensor.getId());
        reading.setStatus("PENDING");

        return readingRepository.save(reading);
    }

    @Override
    public SensorReading getReading(Long id) {
        return readingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));
    }

    @Override
    public List<SensorReading> getReadingsBySensor(Long sensorId) {

   
        sensorRepository.findById(sensorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found"));
        return readingRepository.findBySensorId(sensorId);
    }
}
