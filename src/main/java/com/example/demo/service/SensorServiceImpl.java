package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SensorEntity;
import com.example.demo.repository.SensorRepository;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    SensorRepository sensorRepository;

    @Override
    public SensorEntity createSensor(SensorEntity sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public List<SensorEntity> getAllSensors() {
        return sensorRepository.findAll();
    }
}
