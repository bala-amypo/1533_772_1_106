package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class SensorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String sensorCode;
    private String sensorType;
    private Location location;
    private LocalDateTime installedAt;
    private Boolean isActive;
    public SensorEntity(Long id, String sensorCode, String sensorType, Location location, LocalDateTime installedAt,
            Boolean isActive) {
        this.id = id;
        this.sensorCode = sensorCode;
        this.sensorType = sensorType;
        this.location = location;
        this.installedAt = installedAt;
        this.isActive = isActive;
    }
    public SensorEntity(){

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
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
