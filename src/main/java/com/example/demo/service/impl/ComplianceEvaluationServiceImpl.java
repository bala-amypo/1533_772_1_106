package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public ComplianceEvaluationServiceImpl(SensorReadingRepository sensorReadingRepository,
                                           ComplianceThresholdRepository complianceThresholdRepository,
                                           ComplianceLogRepository complianceLogRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
        this.complianceThresholdRepository = complianceThresholdRepository;
        this.complianceLogRepository = complianceLogRepository;
    }

    @Override
    public ComplianceLog evaluateReading(Long readingId) {
        // 1. Fetch SensorReading
        SensorReading reading = sensorReadingRepository.findById(readingId)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));

        // 2. Get sensorType from associated Sensor
        Sensor sensor = reading.getSensor(); // Make sure SensorReading has getSensor()
        if (sensor == null) {
            throw new ResourceNotFoundException("Sensor not found for this reading");
        }
        String sensorType = sensor.getSensorType(); // Make sure Sensor has getSensorType()

        // 3. Fetch ComplianceThreshold
        ComplianceThreshold threshold = complianceThresholdRepository.findBySensorType(sensorType)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));

        // 4. Determine status
        String statusAssigned;
        Double value = reading.getReadingValue();
        if (value >= threshold.getMinValue() && value <= threshold.getMaxValue()) {
            statusAssigned = "SAFE";
        } else {
            statusAssigned = "UNSAFE";
        }

        // 5. Check if log exists
        Optional<ComplianceLog> existingLogOpt = complianceLogRepository.findBySensorReadingId(reading.getId());
        ComplianceLog log;
        if (existingLogOpt.isPresent()) {
            log = existingLogOpt.get();
            log.setStatusAssigned(statusAssigned);
            log.setLoggedAt(LocalDateTime.now());
        } else {
            log = new ComplianceLog();
            log.setSensorReading(reading); // Make sure ComplianceLog has setSensorReading()
            log.setThresholdUsed(threshold); // Make sure ComplianceLog has setThresholdUsed()
            log.setStatusAssigned(statusAssigned); // Make sure ComplianceLog has setStatusAssigned()
            log.setLoggedAt(LocalDateTime.now()); // Make sure ComplianceLog has setLoggedAt()
        }

        return complianceLogRepository.save(log);
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        return complianceLogRepository.findAllBySensorReadingId(readingId);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        return complianceLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }
}
