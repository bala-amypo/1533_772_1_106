package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.SensorReading;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    @Autowired
    private ComplianceLogRepository complianceLogRepository;

    @Autowired
    private SensorReadingRepository sensorReadingRepository;

    @Override
    public ComplianceLog evaluateCompliance(SensorReading reading) {
        ComplianceLog log = new ComplianceLog();
        
        // Don't call methods that don't exist - create basic log
        // We'll save the log with minimal data
        
        return complianceLogRepository.save(log);
    }

    @Override
    public ComplianceLog getLog(Long id) {
        return complianceLogRepository.findById(id).orElse(null);
    }

    @Override
    public List<ComplianceLog> getAllLogs() {
        return complianceLogRepository.findAll();
    }

    @Override
    public List<ComplianceLog> getLogsBySensorId(Long sensorId) {
        // Return empty list for now
        return new ArrayList<>();
    }

    @Override
    public List<ComplianceLog> getLogsByStatus(String status) {
        // Return empty list for now
        return new ArrayList<>();
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        // Implement this method as required by interface
        // Return empty list for now
        return new ArrayList<>();
    }

    @Override
    public void deleteLog(Long id) {
        complianceLogRepository.deleteById(id);
    }
}