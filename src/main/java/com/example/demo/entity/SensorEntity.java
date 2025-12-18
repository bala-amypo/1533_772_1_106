package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.peristance.Entity;


@Entity
public class SensorEntity{
    @Id
    @GeneratedValue(strategy=Generation)
}