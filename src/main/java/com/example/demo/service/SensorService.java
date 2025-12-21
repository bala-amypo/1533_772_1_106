package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Sensor;

public interface SensorService {

    Sensor createSensor(Long locationId, Sensor sensor);

    Sensor getSensor(Long id);

    List<Sensor> getAllSensors();
}
