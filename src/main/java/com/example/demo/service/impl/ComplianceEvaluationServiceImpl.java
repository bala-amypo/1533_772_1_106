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


        SensorReading reading = sensorReadingRepository.findById(readingId)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));


        String sensorType = reading.getSensorType();


        ComplianceThreshold threshold = complianceThresholdRepository
                .findBySensorType(sensorType)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));


        String status;
        if (reading.getReadingValue() >= threshold.getMinValue()
                && reading.getReadingValue() <= threshold.getMaxValue()) {
            status = "SAFE";
        } else {
            status = "UNSAFE";
        }

        // 5. Check existing log
        List<ComplianceLog> logs =
                complianceLogRepository.findBySensorReading_Id(reading.getId());

        ComplianceLog log;
        if (logs.isEmpty()) {
            log = new ComplianceLog();
            log.setSensorReadingId(reading.getId());
            log.setThresholdId(threshold.getId());
        } else {
            log = logs.get(0);
        }

        log.setStatusAssigned(status);
        log.setRemarks("Evaluated automatically");

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
