package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SensorReading;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {

    List<SensorReading> findBySensor_Id(Long sensorId);

    List<SensorReading> findBySensor_IdAndReadingTimeBetween(Long sensorId, LocalDateTime start, LocalDateTime end);
}
