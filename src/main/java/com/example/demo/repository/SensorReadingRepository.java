package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SensorReading;

public interface SensorReadingRepository
        extends JpaRepository<SensorReading, Long> {

    List<SensorReading> findBySensorId(Long sensorId);
}
