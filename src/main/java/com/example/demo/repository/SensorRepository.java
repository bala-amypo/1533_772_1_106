package com.example.demo.repository;

import com.example.demo.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    
    // Required by Test 4.1: Sensor entity uniqueness constraint check
    Optional<Sensor> findBySensorCode(String code);
    
    // Required by Test 6.6 and 8.4: Validate retrieval of sensors by region
    List<Sensor> findByLocation_Region(String region);
}