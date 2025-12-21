package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
    name = "sensors",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "sensor_code")
    }
)
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sensor_code", nullable = false, unique = true)
    private String sensorCode;

    @Column(nullable = false)
    private String sensorType;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    private LocalDateTime installedAt;

    @Column(nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "sensor")
    private List<SensorReading> sensorReadings;

    public Sensor() {}

    public Sensor(String sensorCode, String sensorType, Location location, LocalDateTime installedAt, Boolean isActive) {
        this.sensorCode = sensorCode;
        this.sensorType = sensorType;
        this.location = location;
        this.installedAt = installedAt;
        this.isActive = (isActive != null) ? isActive : true;
    }

    public Long getId() { 
          return id; }
    public String getSensorCode() { return sensorCode; }
    public void setSensorCode(String sensorCode) { this.sensorCode = sensorCode; }
    public String getSensorType() { return sensorType; }
    public void setSensorType(String sensorType) { this.sensorType = sensorType; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public LocalDateTime getInstalledAt() { return installedAt; }
    public void setInstalledAt(LocalDateTime installedAt) { this.installedAt = installedAt; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = (isActive != null) ? isActive : true; }
}
