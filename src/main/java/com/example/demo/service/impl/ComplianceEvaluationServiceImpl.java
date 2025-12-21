package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.Sensor;
import com.example.demo.entity.SensorReading;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    private final SensorReadingRepository sensorReadingRepository;
    private final ComplianceThresholdRepository complianceThresholdRepository;
    private final ComplianceLogRepository complianceLogRepository;

    @Autowired
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
        // 1. Fetch SensorReading by readingId
        SensorReading reading = sensorReadingRepository.findById(readingId)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));

        // 2. Fetch Sensor from reading and get sensorType
        Sensor sensor = reading.getSensor();
        if (sensor == null) {
            throw new ResourceNotFoundException("Sensor not found for reading");
        }
        String sensorType = sensor.getSensorType();

        // 3. Fetch ComplianceThreshold by sensorType
        ComplianceThreshold threshold = complianceThresholdRepository.findBySensorType(sensorType)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));

        // 4. Determine statusAssigned
        Double readingValue = reading.getValue();
        Double minValue = threshold.getMinValue();
        Double maxValue = threshold.getMaxValue();
        
        String statusAssigned;
        String remarks;
        
        if (readingValue >= minValue && readingValue <= maxValue) {
            statusAssigned = "SAFE";
            remarks = "Reading value " + readingValue + " is within safe range [" + minValue + ", " + maxValue + "]";
        } else {
            statusAssigned = "UNSAFE";
            if (readingValue < minValue) {
                remarks = "Reading value " + readingValue + " is below minimum threshold " + minValue;
            } else {
                remarks = "Reading value " + readingValue + " is above maximum threshold " + maxValue;
            }
        }

        // 5. Check if log already exists for reading
        Optional<ComplianceLog> existingLog = complianceLogRepository.findById(readingId)
                .or(() -> complianceLogRepository.findBySensorReadingId(readingId).stream().findFirst());

        ComplianceLog complianceLog;
        
        if (existingLog.isPresent()) {
            // Update existing log
            complianceLog = existingLog.get();
            complianceLog.setThresholdUsedId(threshold.getId());
            complianceLog.setStatusAssigned(statusAssigned);
            complianceLog.setRemarks(remarks);
            complianceLog.setLoggedAt(LocalDateTime.now());
        } else {
            // Create new log
            complianceLog = new ComplianceLog();
            complianceLog.setSensorReadingId(readingId);
            complianceLog.setThresholdUsedId(threshold.getId());
            complianceLog.setStatusAssigned(statusAssigned);
            complianceLog.setRemarks(remarks);
            complianceLog.setLoggedAt(LocalDateTime.now());
        }

        // 6. Save via complianceLogRepository
        return complianceLogRepository.save(complianceLog);
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        // Use complianceLogRepository.findBySensorReading_Id(readingId)
        return complianceLogRepository.findBySensorReadingId(readingId);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        // If not found, throw ResourceNotFoundException
        return complianceLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }
}