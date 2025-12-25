=package com.example.demo.controller;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance")
public class ComplianceEvaluationController {

    @Autowired
    private ComplianceEvaluationService evaluationService;

    @PostMapping("/evaluate/{readingId}")
    public ResponseEntity<ComplianceLog> evaluateReading(@PathVariable Long readingId) {
        return ResponseEntity.ok(evaluationService.evaluateReading(readingId));
    }

    @GetMapping("/log/{id}")
    public ResponseEntity<ComplianceLog> getLog(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationService.getLog(id));
    }

    @GetMapping("/logs/{readingId}")
    public ResponseEntity<List<ComplianceLog>> getLogsByReading(@PathVariable Long readingId) {
        return ResponseEntity.ok(evaluationService.getLogsByReading(readingId));
    }
}
