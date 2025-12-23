package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.ComplianceEvaluationService;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    @Autowired
    private SensorReadingRepository sensorReadingRepository;

    @Autowired
    private ComplianceThresholdRepository thresholdRepository;

    @Autowired
    private ComplianceLogRepository logRepository;

    @Override
    public ComplianceLog evaluateReading(Long readingId) {

        SensorReading reading = sensorReadingRepository.findById(readingId)
                .orElseThrow(() -> new RuntimeException("Reading not found"));

        String sensorType = reading.getSensor().getSensorType();

        ComplianceThreshold threshold = thresholdRepository.findBySensorType(sensorType)
                .orElseThrow(() -> new RuntimeException("Threshold not found"));

        String status;
        if (reading.getReadingValue() >= threshold.getMinValue()
                && reading.getReadingValue() <= threshold.getMaxValue()) {
            status = "NORMAL";
        } else {
            status = "NOT_COMPLIANT";
        }

        ComplianceLog log = new ComplianceLog();
        log.setReadingId(reading.getId());
        log.setReadingValue(reading.getReadingValue());
        log.setStatus(status);

        return logRepository.save(log);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log not found"));
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        return logRepository.findByReadingId(readingId);
    }
}
