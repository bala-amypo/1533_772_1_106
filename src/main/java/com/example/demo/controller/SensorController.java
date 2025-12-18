package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SensorEntity;
import com.example.demo.service.SensorService;

@RestController
public class SensorController {

    @Autowired
    SensorService sensorService;

    @PostMapping("/addsensor")
    public SensorEntity addSensor(@RequestBody SensorEntity sensor) {
        return sensorService.createSensor(sensor);
    }

    @GetMapping("/showsensors")
    public List<SensorEntity> showSensors() {
        return sensorService.getAllSensors();
    }
}