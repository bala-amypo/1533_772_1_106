package com.example.demo.controller;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compliance")
public class ComplianceEvaluationController {

    private final ComplianceEvaluationService evaluationService;

    public ComplianceEvaluationController(
            ComplianceEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/evaluate/{readingId}")
    public ComplianceLog evaluateReading(
            @PathVariable Long readingId) {
        return evaluationService.evaluateReading(readingId);
    }

    @GetMapping("/reading/{readingId}")
    public List<ComplianceLog> getLogsByReading(
            @PathVariable Long readingId) {
        return evaluationService.getLogsByReading(readingId);
    }

    @GetMapping("/{id}")
    public ComplianceLog getLog(@PathVariable Long id) {
        return evaluationService.getLog(id);
    }
}
