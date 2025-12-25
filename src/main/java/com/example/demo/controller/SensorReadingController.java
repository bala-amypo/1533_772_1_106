package com.example.demo.controller;

import com.example.demo.entity.SensorReading;
import com.example.demo.service.SensorReadingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readings")
public class SensorReadingController {

    private final SensorReadingService sensorReadingService;

    public SensorReadingController(SensorReadingService sensorReadingService) {
        this.sensorReadingService = sensorReadingService;
    }

    @PostMapping("/sensor/{sensorId}")
    public SensorReading submitReading(
            @PathVariable Long sensorId,
            @RequestBody SensorReading reading) {
        return sensorReadingService.submitReading(sensorId, reading);
    }

    @GetMapping("/{id}")
    public SensorReading getReading(@PathVariable Long id) {
        return sensorReadingService.getReading(id);
    }

    @GetMapping("/sensor/{sensorId}")
    public List<SensorReading> getReadingsBySensor(@PathVariable Long sensorId) {
        return sensorReadingService.getReadingsBySensor(sensorId);
    }
}
