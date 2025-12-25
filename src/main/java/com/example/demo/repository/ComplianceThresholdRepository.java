package com.example.demo.repository;

import com.example.demo.entity.ComplianceThreshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComplianceThresholdRepository extends JpaRepository<ComplianceThreshold, Long> {
    
    // Required by Test 6.2 and 8.2: Find threshold by sensor type
    Optional<ComplianceThreshold> findBySensorType(String type);
}