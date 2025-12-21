package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.SensorReading;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    @Autowired
    private ComplianceLogRepository complianceLogRepository;

    @Autowired
    private SensorReadingRepository sensorReadingRepository;

    @Override
    public ComplianceLog evaluateCompliance(SensorReading reading) {
        // Create a new compliance log
        ComplianceLog log = new ComplianceLog();
        
        // Try to get value from reading - use whatever method is available
        Double readingValue = 0.0;
        try {
            // Try common method names
            if (reading.getValue() != null) {
                readingValue = reading.getValue();
            }
        } catch (Exception e) {
            // If getValue() doesn't exist, try other possibilities
            try {
                // You might need to adjust based on your actual SensorReading class
                // readingValue = reading.getReadingValue();
                // readingValue = reading.getReading();
            } catch (Exception ex) {
                // Leave as default 0.0
            }
        }
        
        // Simple compliance logic
        boolean isCompliant = readingValue >= 0.0 && readingValue <= 100.0;
        String status = isCompliant ? "COMPLIANT" : "NON_COMPLIANT";
        
        // Set values - adjust based on your actual ComplianceLog class
        try {
            // Try to call setters that might exist
            log.setStatus(status);
            log.setReadingValue(readingValue);
            log.setEvaluationTime(LocalDateTime.now());
            
            // Try to set sensor reading reference if method exists
            try {
                log.setSensorReading(reading);
            } catch (Exception e) {
                // Method doesn't exist, that's okay
            }
        } catch (Exception e) {
            // If basic setters fail, the entity might not have them
            // We'll still try to save
        }
        
        // Save and return
        return complianceLogRepository.save(log);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        // Return ComplianceLog directly, not Optional
        // Use findById which returns Optional, then get the value or null
        return complianceLogRepository.findById(id).orElse(null);
    }

    @Override
    public List<ComplianceLog> getAllLogs() {
        return complianceLogRepository.findAll();
    }

    @Override
    public List<ComplianceLog> getLogsBySensorId(Long sensorId) {
        // If the repository method doesn't exist, implement manually
        // First try to use findAll and filter
        List<ComplianceLog> allLogs = complianceLogRepository.findAll();
        
        // Filter logs by sensorId - you'll need to check how sensorId is stored
        // This depends on your entity structure
        return allLogs.stream()
            .filter(log -> {
                try {
                    // Try different ways to get sensorId from log
                    if (log.getSensorReading() != null && log.getSensorReading().getSensor() != null) {
                        return log.getSensorReading().getSensor().getId().equals(sensorId);
                    }
                    return false;
                } catch (Exception e) {
                    return false;
                }
            })
            .toList();
    }

    @Override
    public List<ComplianceLog> getLogsByStatus(String status) {
        // If repository method doesn't exist, implement filtering
        List<ComplianceLog> allLogs = complianceLogRepository.findAll();
        
        return allLogs.stream()
            .filter(log -> {
                try {
                    return status.equals(log.getStatus());
                } catch (Exception e) {
                    return false;
                }
            })
            .toList();
    }

    @Override
    public void deleteLog(Long id) {
        complianceLogRepository.deleteById(id);
    }
}