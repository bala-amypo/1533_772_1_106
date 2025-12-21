package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.SensorReading;
import com.example.demo.service.SensorReadingService;

@RestController
@RequestMapping("/api/readings")
public class SensorReadingController {

    @Autowired
    private SensorReadingService sensorReadingService;


    @PostMapping("/{sensorId}")
    public SensorReading submitReading(@PathVariable Long sensorId, @RequestBody SensorReading reading) {
        return sensorReadingService.submitReading(sensorId, reading);
    }


    @GetMapping("/sensor/{sensorId}")
    public List<SensorReading> getReadingsBySensor(@PathVariable Long sensorId) {
        return sensorReadingService.getReadingsBySensor(sensorId);
    }


    @GetMapping("/{id}")
    public SensorReading getReading(@PathVariable Long id) {
        return sensorReadingService.getReading(id);
    }
}
