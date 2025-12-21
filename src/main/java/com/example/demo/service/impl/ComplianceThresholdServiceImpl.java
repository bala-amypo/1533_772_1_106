package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.service.ComplianceThresholdService;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class ComplianceThresholdServiceImpl implements ComplianceThresholdService {

    @Autowired
    private ComplianceThresholdRepository thresholdRepository;

    @Override
    public ComplianceThreshold createThreshold(ComplianceThreshold threshold) {
        if (threshold.getMinValue() >= threshold.getMaxValue()) {
            throw new IllegalArgumentException("minvalue should be less than maxvalue");
        }
        if (threshold.getSeverityLevel() == null || threshold.getSeverityLevel().isEmpty()) {
            throw new IllegalArgumentException("severityLevel is required");
        }
        return thresholdRepository.save(threshold);
    }

    @Override
    public ComplianceThreshold getThreshold(Long id) {
        return thresholdRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));
    }

    @Override
    public ComplianceThreshold getThresholdBySensorType(String sensorType) {
        return thresholdRepository.findBySensorType(sensorType)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));
    }

    @Override
    public List<ComplianceThreshold> getAllThresholds() {
        return thresholdRepository.findAll();
    }
}
