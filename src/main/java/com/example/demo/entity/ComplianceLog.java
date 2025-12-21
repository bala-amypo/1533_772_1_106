package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity

public class ComplianceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many logs -> One sensor reading
    // Rule: One compliance log per reading (unique constraint)
    @ManyToOne
    @JoinColumn(name = "sensor_reading_id", nullable = false)
    private SensorReading sensorReading;

    // Many logs -> One compliance threshold
    @ManyToOne
    @JoinColumn(name = "threshold_id", nullable = false)
    private ComplianceThreshold thresholdUsed;

    @Column(nullable = false)
    private String statusAssigned; // SAFE or UNSAFE

    private String remarks;

    @Column(nullable = false)
    private LocalDateTime loggedAt;

    // No-arg constructor
    public ComplianceLog() {
    }

    // Parameterized constructor
    public ComplianceLog(
            SensorReading sensorReading,
            ComplianceThreshold thresholdUsed,
            String statusAssigned,
            String remarks,
            LocalDateTime loggedAt) {

        this.sensorReading = sensorReading;
        this.thresholdUsed = thresholdUsed;
        this.statusAssigned = statusAssigned;
        this.remarks = remarks;
        this.loggedAt = loggedAt;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public SensorReading getSensorReading() {
        return sensorReading;
    }

    public void setSensorReading(SensorReading sensorReading) {
        this.sensorReading = sensorReading;
    }

    public ComplianceThreshold getThresholdUsed() {
        return thresholdUsed;
    }

    public void setThresholdUsed(ComplianceThreshold thresholdUsed) {
        this.thresholdUsed = thresholdUsed;
    }

    public String getStatusAssigned() {
        return statusAssigned;
    }

    public void setStatusAssigned(String statusAssigned) {
        this.statusAssigned = statusAssigned;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }
}
