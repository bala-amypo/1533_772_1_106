package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String sensorCode;

    @Column(nullable = false)
    private String sensorType;

    @Column(nullable = false)
    private Long locationId; // store location ID only

    @Column(nullable = false)
    private LocalDateTime installedAt;

    @Column(nullable = false)
    private Boolean isActive;

    // Constructors
    public Sensor() {
    }

    public Sensor(String sensorCode, String sensorType, Long locationId, LocalDateTime installedAt, Boolean isActive) {
        this.sensorCode = sensorCode;
        this.sensorType = sensorType;
        this.locationId = locationId;
        this.installedAt = installedAt;
        this.isActive = isActive;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public LocalDateTime getInstalledAt() {
        return installedAt;
    }

    public void setInstalledAt(LocalDateTime installedAt) {
        this.installedAt = installedAt;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
