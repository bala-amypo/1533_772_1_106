package com.example.demo.repository;

import com.example.demo.entity.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {
    
    // Required by Test 8.1: Find readings by sensor id
    List<SensorReading> findBySensor_Id(Long id);
    
    // Required by Test 8.3: Complex date-range find readings simulation
    List<SensorReading> findBySensor_IdAndReadingTimeBetween(Long id, LocalDateTime start, LocalDateTime end);
}