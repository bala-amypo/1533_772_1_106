package com.example.demo.service.impl;

import java.util.List;

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

        // 1. Reading fetch
        SensorReading reading = sensorReadingRepository.findById(readingId)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));

        // ✅ CORRECT: getSensorId()
        Long sensorId = reading.getSensorId();

        // 2. Threshold fetch (sensorId based)
        ComplianceThreshold threshold = complianceThresholdRepository.findBySensorId(sensorId)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));

        // ✅ CORRECT: getReadingValue()
        Double value = reading.getReadingValue();

        // 3. SAFE / UNSAFE logic
        String status;
        if (value >= threshold.getMinValue() && value <= threshold.getMaxValue()) {
            status = "SAFE";
        } else {
            status = "UNSAFE";
        }

        // 4. Existing log check
        List<ComplianceLog> logs = complianceLogRepository.findByReadingId(readingId);

        ComplianceLog log;
        if (logs.isEmpty()) {
            log = new ComplianceLog();
            log.setReadingId(readingId);
        } else {
            log = logs.get(0);
        }

        log.setReadingValue(value);
        log.setStatus(status);

        // 5. Save log
        return complianceLogRepository.save(log);
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        return complianceLogRepository.findByReadingId(readingId);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        return complianceLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }
}
