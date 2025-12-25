package com.example.demo.controller;

import com.example.demo.entity.SensorReading;
import com.example.demo.service.SensorReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readings")
public class SensorReadingController {

    @Autowired
    private SensorReadingService readingService;

    @PostMapping("/{sensorId}")
    public ResponseEntity<SensorReading> createReading(@PathVariable Long sensorId, @RequestBody SensorReading reading) {
        return ResponseEntity.ok(readingService.createReading(sensorId, reading));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorReading> getReading(@PathVariable Long id) {
        return ResponseEntity.ok(readingService.getReading(id));
    }

    @GetMapping
    public ResponseEntity<List<SensorReading>> getAllReadings() {
        return ResponseEntity.ok(readingService.getAllReadings());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReading(@PathVariable Long id) {
        readingService.deleteReading(id);
        return ResponseEntity.noContent().build();
    }
}
