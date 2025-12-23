package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ComplianceLog;

public interface ComplianceEvaluationService {

    ComplianceLog evaluateReading(Long readingId);

    ComplianceLog getLog(Long id);

    List<ComplianceLog> getLogsByReading(Long readingId);
}
