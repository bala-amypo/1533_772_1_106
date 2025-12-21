package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sensor_readings")
public class SensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long sensorId; // store sensor ID only

    @Column(nullable = false)
    private Double readingValue;

    @Column(nullable = false)
    private LocalDateTime readingTime;

    @Column(nullable = false)
    private String status;

    // Constructors
    public SensorReading() {
    }

    public SensorReading(Long sensorId, Double readingValue, LocalDateTime readingTime, String status) {
        this.sensorId = sensorId;
        this.readingValue = readingValue;
        this.readingTime = readingTime;
        this.status = status;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public Double getReadingValue() {
        return readingValue;
    }

    public void setReadingValue(Double readingValue) {
        this.readingValue = readingValue;
    }

    public LocalDateTime getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(LocalDateTime readingTime) {
        this.readingTime = readingTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
