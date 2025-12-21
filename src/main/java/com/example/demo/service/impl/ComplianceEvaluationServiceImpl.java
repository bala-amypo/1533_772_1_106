package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.SensorReading;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.service.ComplianceEvaluationService;
import com.example.demo.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    private final SensorReadingRepository sensorReadingRepository;
    private final ComplianceThresholdRepository complianceThresholdRepository;
    private final ComplianceLogRepository complianceLogRepository;

    @Autowired
    public ComplianceEvaluationServiceImpl(SensorReadingRepository sensorReadingRepository,
                                           ComplianceThresholdRepository complianceThresholdRepository,
                                           ComplianceLogRepository complianceLogRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
        this.complianceThresholdRepository = complianceThresholdRepository;
        this.complianceLogRepository = complianceLogRepository;
    }

    @Override
    public ComplianceLog evaluateReading(Long readingId) {
        // Fetch SensorReading
        SensorReading reading = sensorReadingRepository.findById(readingId)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));

        // Fetch ComplianceThreshold by sensor type
        String sensorType = reading.getSensor().getSensorType(); // getSensor() exists in entity
        ComplianceThreshold threshold = complianceThresholdRepository.findBySensorType(sensorType)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));

        // Determine status
        String statusAssigned = (reading.getReadingValue() >= threshold.getMinValue()
                && reading.getReadingValue() <= threshold.getMaxValue()) ? "SAFE" : "UNSAFE";

        // Check if log exists
        List<ComplianceLog> existingLogs = complianceLogRepository.findBySensorReading_Id(readingId);
        ComplianceLog log = existingLogs.isEmpty() ? new ComplianceLog() : existingLogs.get(0);

        // Set log details
        log.setSensorReading(reading);
        log.setThresholdUsed(threshold);
        log.setStatus(statusAssigned);
        log.setEvaluatedAt(LocalDateTime.now());

        return complianceLogRepository.save(log);
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        return complianceLogRepository.findAllBySensorReading_Id(readingId);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        return complianceLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }
}
