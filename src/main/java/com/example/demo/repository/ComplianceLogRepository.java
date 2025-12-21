package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ComplianceLog;

public interface ComplianceLogRepository 
        extends JpaRepository<ComplianceLog, Long> {

    List<ComplianceLog> findByReadingId(Long readingId);
}
