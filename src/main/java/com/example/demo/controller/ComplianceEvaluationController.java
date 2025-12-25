package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.ComplianceLog;
import com.example.demo.service.ComplianceEvaluationService;
import java.util.List;

@RestController
@RequestMapping("/compliance")
public class ComplianceEvaluationController {

    @Autowired
    private ComplianceEvaluationService complianceEvaluationService;

    @PostMapping("/evaluate/{readingId}")
    public ComplianceLog evaluateReading(@PathVariable Long readingId) {
        return complianceEvaluationService.evaluateReading(readingId);
    }

    @GetMapping("/log/{id}")
    public ComplianceLog getLog(@PathVariable Long id) {
        return complianceEvaluationService.getLog(id);
    }

    @GetMapping("/logs/{readingId}")
    public List<ComplianceLog> getLogsByReading(@PathVariable Long readingId) {
        return complianceEvaluationService.getLogsByReading(readingId);
    }
}
