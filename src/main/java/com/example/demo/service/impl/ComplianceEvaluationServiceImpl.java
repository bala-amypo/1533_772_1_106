package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.service.ComplianceEvaluationService;

@Service
public class ComplianceEvaluationServiceImpl
        implements ComplianceEvaluationService {

    @Autowired
    private ComplianceThresholdRepository complianceThresholdRepository;

    @Autowired
    private ComplianceLogRepository complianceLogRepository;

    @Override
    public ComplianceLog evaluateCompliance(SensorReading reading) {

        // ❌ OLD (WRONG)
        // complianceThresholdRepository.findBySensorId(reading.getSensorId());

        // ✅ NEW (CORRECT)
        String sensorType = reading.getStatus(); 
        // ⚠️ temporary usage since no relationship

        ComplianceThreshold threshold =
                complianceThresholdRepository
                        .findBySensorType(sensorType)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Threshold not found"));

        String complianceStatus =
                reading.getReadingValue() >= threshold.getMinValue()
                && reading.getReadingValue() <= threshold.getMaxValue()
                ? "COMPLIANT"
                : "NON_COMPLIANT";

        ComplianceLog log = new ComplianceLog();
        log.setReadingId(reading.getId());
        log.setStatus(complianceStatus);
        log.setCheckedAt(LocalDateTime.now());

        return complianceLogRepository.save(log);
    }

    @Override
    public ComplianceLog getLog(Long readingId) {
        return complianceLogRepository.findByReadingId(readingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Log not found"));
    }
}
