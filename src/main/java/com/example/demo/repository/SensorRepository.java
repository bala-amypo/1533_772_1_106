package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SensorEntity;

public interface SensorRepository extends JpaRepository<SensorEntity, Long> {

}
