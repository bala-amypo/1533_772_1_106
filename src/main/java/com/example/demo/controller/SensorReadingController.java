package com.example.demo.controller;

import com.example.demo.entity.SensorReading;
import com.example.demo.service.SensorReadingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readings")
@Tag(name = "Sensor Reading Controller", description = "Submit and retrieve readings")
public class SensorReadingController {

    private final SensorReadingService readingService;

    public SensorReadingController(SensorReadingService readingService) {
        this.readingService = readingService;
    }

    @PostMapping("/{sensorId}")
    @Operation(summary = "Submit a reading for a sensor")
    public ResponseEntity<SensorReading> submitReading(@PathVariable Long sensorId, @RequestBody SensorReading reading) {
        return ResponseEntity.ok(readingService.submitReading(sensorId, reading));
    }

    @GetMapping("/sensor/{sensorId}")
    @Operation(summary = "Get readings for a specific sensor")
    public ResponseEntity<List<SensorReading>> getReadingsBySensor(@PathVariable Long sensorId) {
        return ResponseEntity.ok(readingService.getReadingsBySensor(sensorId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get reading by ID")
    public ResponseEntity<SensorReading> getReading(@PathVariable Long id) {
        return ResponseEntity.ok(readingService.getReading(id));
    }
}