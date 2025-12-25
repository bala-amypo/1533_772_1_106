package com.example.demo.service;

import com.example.demo.entity.SensorReading;

import java.util.List;

public interface SensorReadingService {
    SensorReading createReading(Long sensorId, SensorReading reading);
    SensorReading getReading(Long id);
    List<SensorReading> getAllReadings();
    void deleteReading(Long id);
}
