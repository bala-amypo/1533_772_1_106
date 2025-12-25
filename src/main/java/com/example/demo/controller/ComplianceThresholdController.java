package com.example.demo.controller;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.service.ComplianceThresholdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thresholds")
@Tag(name = "Compliance Threshold Controller", description = "Manage thresholds")
public class ComplianceThresholdController {

    private final ComplianceThresholdService thresholdService;

    public ComplianceThresholdController(ComplianceThresholdService thresholdService) {
        this.thresholdService = thresholdService;
    }

    @PostMapping
    @Operation(summary = "Create a compliance threshold")
    public ResponseEntity<ComplianceThreshold> createThreshold(@RequestBody ComplianceThreshold threshold) {
        return ResponseEntity.ok(thresholdService.createThreshold(threshold));
    }

    @GetMapping
    @Operation(summary = "Get all thresholds")
    public ResponseEntity<List<ComplianceThreshold>> getAllThresholds() {
        return ResponseEntity.ok(thresholdService.getAllThresholds());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get threshold by ID")
    public ResponseEntity<ComplianceThreshold> getThreshold(@PathVariable Long id) {
        return ResponseEntity.ok(thresholdService.getThreshold(id));
    }

    @GetMapping("/type/{sensorType}")
    @Operation(summary = "Get threshold by sensor type")
    public ResponseEntity<ComplianceThreshold> getThresholdBySensorType(@PathVariable String sensorType) {
        return ResponseEntity.ok(thresholdService.getThresholdBySensorType(sensorType));
    }
}