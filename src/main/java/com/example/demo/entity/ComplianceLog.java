package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ComplianceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean compliant;
    private LocalDateTime checkedAt;

    @ManyToOne
    @JoinColumn(name = "reading_id")
    private SensorReading reading;

    public ComplianceLog() {}

    public ComplianceLog(boolean compliant, LocalDateTime checkedAt, SensorReading reading) {
        this.compliant = compliant;
        this.checkedAt = checkedAt;
        this.reading = reading;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean isCompliant() { return compliant; }
    public void setCompliant(boolean compliant) { this.compliant = compliant; }

    public LocalDateTime getCheckedAt() { return checkedAt; }
    public void setCheckedAt(LocalDateTime checkedAt) { this.checkedAt = checkedAt; }

    public SensorReading getReading() { return reading; }
    public void setReading(SensorReading reading) { this.reading = reading; }
}
