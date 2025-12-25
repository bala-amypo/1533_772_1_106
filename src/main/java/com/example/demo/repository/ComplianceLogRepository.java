package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.ComplianceLog;
import java.util.List;

public interface ComplianceLogRepository extends JpaRepository<ComplianceLog, Long> {
    List<ComplianceLog> findByReadingId(Long readingId);
}
