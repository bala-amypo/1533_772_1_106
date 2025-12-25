package com.example.demo.controller;

import com.example.demo.entity.Sensor;
import com.example.demo.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
@Tag(name = "Sensor Controller", description = "Manage sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/{locationId}")
    @Operation(summary = "Create a sensor at a location")
    public ResponseEntity<Sensor> createSensor(@PathVariable Long locationId, @RequestBody Sensor sensor) {
        return ResponseEntity.ok(sensorService.createSensor(locationId, sensor));
    }

    @GetMapping
    @Operation(summary = "Get all sensors")
    public ResponseEntity<List<Sensor>> getAllSensors() {
        return ResponseEntity.ok(sensorService.getAllSensors());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get sensor by ID")
    public ResponseEntity<Sensor> getSensor(@PathVariable Long id) {
        return ResponseEntity.ok(sensorService.getSensor(id));
    }
}