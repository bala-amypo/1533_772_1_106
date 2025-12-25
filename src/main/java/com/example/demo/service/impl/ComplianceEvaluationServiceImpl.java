package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.SensorReading;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.service.ComplianceEvaluationService;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    @Autowired
    private ComplianceLogRepository complianceLogRepository;

    @Autowired
    private SensorReadingRepository sensorReadingRepository;

    @Override
    public ComplianceLog evaluateReading(Long readingId) {
        SensorReading reading = sensorReadingRepository.findById(readingId)
            .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));

        ComplianceLog log = new ComplianceLog();
        log.setReading(reading);

        // Dummy logic for status
        if (reading.getValue() != null && reading.getValue() > 50) {
            log.setStatus("");
        } else {
            log.setStatus("PASS");
        }

        return complianceLogRepository.save(log);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        return complianceLogRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        return complianceLogRepository.findByReadingId(readingId);
    }
}
