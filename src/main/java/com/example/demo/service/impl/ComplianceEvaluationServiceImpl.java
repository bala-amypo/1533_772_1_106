package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceLog;
import com.example.demo.entity.SensorReading;
import com.example.demo.repository.ComplianceLogRepository;
import com.example.demo.repository.SensorReadingRepository;
import com.example.demo.service.ComplianceEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComplianceEvaluationServiceImpl implements ComplianceEvaluationService {

    @Autowired
    private ComplianceLogRepository complianceLogRepository;

    @Autowired
    private SensorReadingRepository sensorReadingRepository;

    @Override
    public ComplianceLog evaluateCompliance(SensorReading reading) {
        ComplianceLog log = new ComplianceLog();
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
        return new ArrayList<>();
    }

    @Override
    public List<ComplianceLog> getLogsByStatus(String status) {
        return new ArrayList<>();
    }

    @Override
    public List<ComplianceLog> getLogsByReading(Long readingId) {
        return new ArrayList<>();
    }

    @Override
    public ComplianceLog evaluateReading(Long readingId) {
        // Create a basic compliance log
        ComplianceLog log = new ComplianceLog();
        return complianceLogRepository.save(log);
    }

    @Override
    public void deleteLog(Long id) {
        complianceLogRepository.deleteById(id);
    }
}