package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ComplianceThreshold;

@Repository
public interface ComplianceThresholdRepository extends JpaRepository<ComplianceThreshold, Long> {

    Optional<ComplianceThreshold> findBySensorType(String sensorType);
}
