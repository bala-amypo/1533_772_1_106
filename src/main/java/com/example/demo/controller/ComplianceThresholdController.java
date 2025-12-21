package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.service.ComplianceThresholdService;

@RestController
@RequestMapping("/api/thresholds")
public class ComplianceThresholdController {

    @Autowired
    private ComplianceThresholdService thresholdService;

    @PostMapping
    public ResponseEntity<ComplianceThreshold> createThreshold(
            @RequestBody ComplianceThreshold threshold) {
        return ResponseEntity.ok(
                thresholdService.createThreshold(threshold));
    }

    @GetMapping("/sensor/{sensorType}")
    public ResponseEntity<ComplianceThreshold> getBySensorType(
            @PathVariable String sensorType) {
        return ResponseEntity.ok(
                thresholdService.getThresholdBySensorType(sensorType));
    }

    @GetMapping
    public ResponseEntity<List<ComplianceThreshold>> getAll() {
        return ResponseEntity.ok(
                thresholdService.getAllThresholds());
    }
}
