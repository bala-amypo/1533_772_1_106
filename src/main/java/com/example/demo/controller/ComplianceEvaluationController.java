package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.service.ComplianceEvaluationService;

@RestController
@RequestMapping("/api/compliance")
public class ComplianceEvaluationController {

    @Autowired
    private ComplianceEvaluationService complianceService;


    @PostMapping("/evaluate/{readingId}")
    public ComplianceLog evaluate(@PathVariable Long readingId) {
        return complianceService.evaluateReading(readingId);
    }

    @GetMapping("/{id}")
    public ComplianceLog getLog(@PathVariable Long id) {
        return complianceService.getLog(id);
    }


    @GetMapping("/reading/{readingId}")
    public List<ComplianceLog> getLogsByReading(@PathVariable Long readingId) {
        return complianceService.getLogsByReading(readingId);
    }
}
