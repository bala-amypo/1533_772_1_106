package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.SensorReading;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.service.ComplianceEvaluationService;
import com.example.demo.exception.ResourceNotFoundException;

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
        SensorReading reading = sensorReadingRepository.findById(readingId)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor reading not found with id: " + readingId));

        ComplianceThreshold threshold = complianceThresholdRepository.findBySensorType(reading.getSensorType())
                .orElseThrow(() -> new ResourceNotFoundException("Compliance threshold not found for sensor type: " + reading.getSensorType()));

        ComplianceLog log = new ComplianceLog();
        log.setReading(reading); // <-- this now works because ComplianceLog has setReading()

        // Simple compliance evaluation
        if (reading.getValue() >= threshold.getMinValue() && reading.getValue() <= threshold.getMaxValue()) {
            log.setStatus("PASS");
        } else {
            log.setStatus("FAIL");
        }

        return complianceLogRepository.save(log);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        return complianceLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compliance log not found with id: " + id));
    }
}
