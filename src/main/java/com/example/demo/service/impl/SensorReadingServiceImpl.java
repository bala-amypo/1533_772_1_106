package com.example.demo.service.impl;

import com.example.demo.entity.Sensor;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.repository.SensorRepository;
import com.example.demo.service.SensorReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorReadingServiceImpl implements SensorReadingService {

    @Autowired
    private SensorReadingRepository readingRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public SensorReading createReading(Long sensorId, SensorReading reading) {
        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found"));
        reading.setSensor(sensor);
        return readingRepository.save(reading);
    }

    @Override
    public SensorReading getReading(Long id) {
        return readingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));
    }

    @Override
    public List<SensorReading> getAllReadings() {
        return readingRepository.findAll();
    }

    @Override
    public void deleteReading(Long id) {
        SensorReading reading = getReading(id);
        readingRepository.delete(reading);
    }
}
