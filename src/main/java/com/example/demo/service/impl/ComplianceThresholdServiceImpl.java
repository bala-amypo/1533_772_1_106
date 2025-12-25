package com.example.demo.service.impl;

import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.service.ComplianceThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceThresholdServiceImpl implements ComplianceThresholdService {

    @Autowired
    private ComplianceThresholdRepository thresholdRepository;

    @Override
    public ComplianceThreshold createThreshold(ComplianceThreshold threshold) {
        return thresholdRepository.save(threshold);
    }

    @Override
    public ComplianceThreshold getThreshold(Long id) {
        return thresholdRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Threshold not found"));
    }

    @Override
    public List<ComplianceThreshold> getAllThresholds() {
        return thresholdRepository.findAll();
    }

    @Override
    public void deleteThreshold(Long id) {
        ComplianceThreshold threshold = getThreshold(id);
        thresholdRepository.delete(threshold);
    }
}
