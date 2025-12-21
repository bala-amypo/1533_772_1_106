package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ComplianceLog;

public interface ComplianceLogRepository extends JpaRepository<ComplianceLog, Long> {

    // ✅ field name = sensorReadingId
    List<ComplianceLog> findBySensorReadingId(Long sensorReadingId);
}
