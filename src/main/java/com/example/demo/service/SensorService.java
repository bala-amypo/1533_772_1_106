package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.SensorEntity;

public interface SensorService {

    SensorEntity createSensor(SensorEntity sensor);

    List<SensorEntity> getAllSensors();
}
