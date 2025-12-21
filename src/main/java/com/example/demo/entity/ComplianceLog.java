package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "compliance_logs",
    uniqueConstraints = {@UniqueConstraint(columnNames = "sensor_reading_id")}
)
public class ComplianceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sensor_reading_id", nullable = false)
    private Long sensorReadingId;

    @Column(name = "threshold_id", nullable = false)
    private Long thresholdId;

    @Column(nullable = false)
    private String statusAssigned;

    private String remarks;

    @Column(nullable = false)
    private LocalDateTime loggedAt;


    public ComplianceLog() {
    }

    public ComplianceLog(Long sensorReadingId, Long thresholdId, String statusAssigned, String remarks, LocalDateTime loggedAt) {
        this.sensorReadingId = sensorReadingId;
        this.thresholdId = thresholdId;
        this.statusAssigned = statusAssigned;
        this.remarks = remarks;
        this.loggedAt = loggedAt;
    }


    public Long getId() {
        return id;
    }

    public Long getSensorReadingId() {
        return sensorReadingId;
    }

    public void setSensorReadingId(Long sensorReadingId) {
        this.sensorReadingId = sensorReadingId;
    }

    public Long getThresholdId() {
        return thresholdId;
    }

    public void setThresholdId(Long thresholdId) {
        this.thresholdId = thresholdId;
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
