package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.Sensor;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.service.ComplianceEvaluationService;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    private final SensorReadingRepository sensorReadingRepository;
    private final ComplianceThresholdRepository complianceThresholdRepository;
    private final ComplianceLogRepository complianceLogRepository;

    public ComplianceEvaluationServiceImpl(
            SensorReadingRepository sensorReadingRepository,
            ComplianceThresholdRepository complianceThresholdRepository,
            ComplianceLogRepository complianceLogRepository) {

        this.sensorReadingRepository = sensorReadingRepository;
        this.complianceThresholdRepository = complianceThresholdRepository;
        this.complianceLogRepository = complianceLogRepository;
    }

    @Override
    public ComplianceLog evaluateReading(Long readingId) {

        // 1️⃣ Fetch SensorReading
        SensorReading reading = sensorReadingRepository.findById(readingId)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));

        // 2️⃣ Get sensorType from Sensor
        Sensor sensor = reading.getSensor();
        String sensorType = sensor.getSensorType();

        // 3️⃣ Fetch ComplianceThreshold
        ComplianceThreshold threshold = complianceThresholdRepository
                .findBySensorType(sensorType)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));

    
        String statusAssigned;
        if (reading.getReadingValue() >= threshold.getMinValue()
                && reading.getReadingValue() <= threshold.getMaxValue()) {
            statusAssigned = "SAFE";
        } else {
            statusAssigned = "UNSAFE";
        }

     
        List<ComplianceLog> existingLogs =
                complianceLogRepository.findBySensorReading_Id(readingId);

        ComplianceLog log;
        if (existingLogs.isEmpty()) {
            log = new ComplianceLog();
            log.setSensorReading(reading);
        } else {
            log = existingLogs.get(0);
        }

      
        log.setStatus(statusAssigned);
        log.setEvaluatedAt(LocalDateTime.now());

      
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
