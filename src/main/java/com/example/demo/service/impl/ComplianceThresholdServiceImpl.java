package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.service.ComplianceThresholdService;

@Service
public class ComplianceThresholdServiceImpl implements ComplianceThresholdService {

    @Autowired
    private ComplianceThresholdRepository thresholdRepository;

    @Override
    public ComplianceThreshold createThreshold(ComplianceThreshold threshold) {
        return thresholdRepository.save(threshold);
    }

    @Override
    public ComplianceThreshold getBySensorId(Long sensorId) {
        return thresholdRepository.findBySensorId(sensorId)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));
    }

    @Override
    public List<ComplianceThreshold> getAllThresholds() {
        return thresholdRepository.findAll();
    }
}
