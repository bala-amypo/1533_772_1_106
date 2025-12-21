package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


        Sensor sensor = reading.getSensor();
        String sensorType = sensor.getSensorType();


        ComplianceThreshold threshold = complianceThresholdRepository
                .findBySensorType(sensorType)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));


        Double value = reading.getReadingValue();
        String status;

        if (value >= threshold.getMinValue() && value <= threshold.getMaxValue()) {
            status = "SAFE";
        } else {
            status = "UNSAFE";
        }


        List<ComplianceLog> logs =
                complianceLogRepository.findBySensorReadingId(readingId);

        ComplianceLog log;
        if (logs.isEmpty()) {
            log = new ComplianceLog();
            log.setSensorReading(reading);
            log.setEvaluatedAt(LocalDateTime.now());
        } else {
            log = logs.get(0);
        }

        log.setStatus(status);
        log.setRemarks("Auto evaluated");


        return complianceLogRepository.save(log);
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        return complianceLogRepository.findBySensorReadingId(readingId);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        return complianceLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }
}
