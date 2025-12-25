package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class SensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sensorType;
    private Double value;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;  // <-- Add this field

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSensorType() { return sensorType; }
    public void setSensorType(String sensorType) { this.sensorType = sensorType; }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public Sensor getSensor() { return sensor; }    // <-- Getter
    public void setSensor(Sensor sensor) { this.sensor = sensor; } // <-- Setter
}
