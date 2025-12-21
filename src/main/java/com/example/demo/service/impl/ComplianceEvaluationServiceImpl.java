package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.SensorReading;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.service.ComplianceEvaluationService;

@Service
public class ComplianceEvaluationServiceImpl
        implements ComplianceEvaluationService {

    private final ComplianceLogRepository complianceLogRepository;

    public ComplianceEvaluationServiceImpl(
            ComplianceLogRepository complianceLogRepository) {
        this.complianceLogRepository = complianceLogRepository;
    }

    @Override
    public ComplianceLog evaluateCompliance(
            SensorReading reading,
            ComplianceThreshold threshold,
            String status,
            String remarks) {

        // Create new compliance log
        ComplianceLog log = new ComplianceLog();

        // Option A: ID fields only (NO relations)
        log.setSensorReadingId(reading.getId());
        log.setThresholdId(threshold.getId());
        log.setStatusAssigned(status);
        log.setRemarks(remarks);
        log.setLoggedAt(LocalDateTime.now());

        // Save & return
        return complianceLogRepository.save(log);
    }
}
