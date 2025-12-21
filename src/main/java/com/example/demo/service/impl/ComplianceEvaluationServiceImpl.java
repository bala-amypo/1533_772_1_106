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
        

        String status = "COMPLIANT";
        Double readingValue = 0.0;
        

        return complianceLogRepository.save(log);
    }

    @Override
    public Optional<ComplianceLog> getLog(Long id) {
        return complianceLogRepository.findById(id);
    }

    @Override
    public List<ComplianceLog> getAllLogs() {
        return complianceLogRepository.findAll();
    }

    @Override
    public List<ComplianceLog> getLogsBySensorId(Long sensorId) {
        return complianceLogRepository.findBySensorReading_SensorId(sensorId);
    }

    @Override
    public List<ComplianceLog> getLogsByStatus(String status) {
        return complianceLogRepository.findByStatus(status);
    }

    @Override
    public void deleteLog(Long id) {
        complianceLogRepository.deleteById(id);
    }
}