package com.example.demo.controller;

import com.example.demo.entity.Sensor;
import com.example.demo.service.SensorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/location/{locationId}")
    public Sensor createSensor(
            @PathVariable Long locationId,
            @RequestBody Sensor sensor) {
        return sensorService.createSensor(locationId, sensor);
    }

    @GetMapping("/{id}")
    public Sensor getSensor(@PathVariable Long id) {
        return sensorService.getSensor(id);
    }

    @GetMapping
    public List<Sensor> getAllSensors() {
        return sensorService.getAllSensors();
    }
}
