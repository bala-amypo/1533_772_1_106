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
    private ComplianceEvaluationService complianceEvaluationService;


    @PostMapping("/evaluate/{readingId}")
    public ComplianceLog evaluateReading(@PathVariable Long readingId) {
        return complianceEvaluationService.evaluateReading(readingId);
    }

  
    @GetMapping("/reading/{readingId}")
    public List<ComplianceLog> getLogsByReading(@PathVariable Long readingId) {
        return complianceEvaluationService.getLogsByReading(readingId);
    }

    }
    @GetMapping("/{id}")
    public ComplianceLog getLog(@PathVariable Long id) {
        return complianceEvaluationService.getLog(id);
    }
}
