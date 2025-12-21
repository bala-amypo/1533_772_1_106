package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.ComplianceThreshold;

public interface ComplianceThresholdService {

    ComplianceThreshold createThreshold(ComplianceThreshold threshold);

    ComplianceThreshold getThresholdBySensorType(String sensorType);

    List<ComplianceThreshold> getAllThresholds();
}
