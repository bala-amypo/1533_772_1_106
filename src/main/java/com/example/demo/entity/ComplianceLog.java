package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_logs")
public class ComplianceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "sensor_reading_id")
    private Long sensorReadingId;

    
    @Column(name = "threshold_used_id")
    private Long thresholdUsedId;

    @Column(name = "status_assigned", nullable = false)
    private String statusAssigned;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "logged_at", nullable = false)
    private LocalDateTime loggedAt;

    
    public ComplianceLog() {
    }

   
    public ComplianceLog(Long sensorReadingId, Long thresholdUsedId, 
                        String statusAssigned, String remarks, LocalDateTime loggedAt) {
        this.sensorReadingId = sensorReadingId;
        this.thresholdUsedId = thresholdUsedId;
        this.statusAssigned = statusAssigned;
        this.remarks = remarks;
        this.loggedAt = loggedAt;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSensorReadingId() {
        return sensorReadingId;
    }

    public void setSensorReadingId(Long sensorReadingId) {
        this.sensorReadingId = sensorReadingId;
    }

    public Long getThresholdUsedId() {
        return thresholdUsedId;
    }

    public void setThresholdUsedId(Long thresholdUsedId) {
        this.thresholdUsedId = thresholdUsedId;
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

    // Helper method to validate status (Rule: must be SAFE or UNSAFE)
    public boolean isValidStatus() {
        return "SAFE".equalsIgnoreCase(statusAssigned) || "UNSAFE".equalsIgnoreCase(statusAssigned);
    }

    @Override
    public String toString() {
        return "ComplianceLog{" +
                "id=" + id +
                ", sensorReadingId=" + sensorReadingId +
                ", thresholdUsedId=" + thresholdUsedId +
                ", statusAssigned='" + statusAssigned + '\'' +
                ", remarks='" + remarks + '\'' +
                ", loggedAt=" + loggedAt +
                '}';
    }
}