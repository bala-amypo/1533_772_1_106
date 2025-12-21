package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.service.ComplianceEvaluationService;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    @Autowired
    private SensorReadingRepository sensorReadingRepository;

    @Autowired
    private ComplianceThresholdRepository complianceThresholdRepository;

    @Autowired
    private ComplianceLogRepository complianceLogRepository;

    @Override
    public ComplianceLog evaluateReading(Long readingId) {
        // Fetch the sensor reading
        SensorReading reading = sensorReadingRepository.findById(readingId)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));

        // Get sensor type
        String sensorType = reading.getSensorType();
        if (sensorType == null || sensorType.isEmpty()) {
            throw new IllegalArgumentException("Sensor type is missing for the reading");
        }

        // Fetch threshold for the sensor type
        ComplianceThreshold threshold = complianceThresholdRepository.findBySensorType(sensorType)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));

        // Determine status
        String statusAssigned;
        Double value = reading.getReadingValue();
        if (value >= threshold.getMinValue() && value <= threshold.getMaxValue()) {
            statusAssigned = "SAFE";
        } else {
            statusAssigned = "UNSAFE";
        }

        // Check if log already exists
        Optional<ComplianceLog> existingLog = complianceLogRepository.findBySensorReading_Id(readingId);
        ComplianceLog log;
        if (existingLog.isPresent()) {
            log = existingLog.get();
            log.setStatus(statusAssigned);
            log.setReadingValue(value);
        } else {
            log = new ComplianceLog();
            log.setSensorReading(reading);
            log.setStatus(statusAssigned);
            log.setReadingValue(value);
        }

        // Save log
        return complianceLogRepository.save(log);
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        return complianceLogRepository.findBySensorReading_Id(readingId);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        return complianceLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }
}
