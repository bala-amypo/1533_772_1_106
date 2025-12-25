package com.example.demo.controller;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thresholds")
public class ComplianceThresholdController {

    @Autowired
    private ComplianceThresholdService thresholdService;

    @PostMapping
    public ResponseEntity<ComplianceThreshold> createThreshold(@RequestBody ComplianceThreshold threshold) {
        return ResponseEntity.ok(thresholdService.createThreshold(threshold));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplianceThreshold> getThreshold(@PathVariable Long id) {
        return ResponseEntity.ok(thresholdService.getThreshold(id));
    }

    @GetMapping
    public ResponseEntity<List<ComplianceThreshold>> getAllThresholds() {
        return ResponseEntity.ok(thresholdService.getAllThresholds());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThreshold(@PathVariable Long id) {
        thresholdService.deleteThreshold(id);
        return ResponseEntity.noContent().build();
    }
}
