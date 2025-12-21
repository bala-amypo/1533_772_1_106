package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.SensorReading;

public interface SensorReadingService {

    SensorReading submitReading(Long sensorId, SensorReading reading);

    SensorReading getReading(Long id);

    List<SensorReading> getReadingsBySensor(Long sensorId);
}
