package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Sensor;
import com.example.demo.service.SensorService;

@RestController
public class SensorController {

    @Autowired
    private SensorService sensorService;


    @PostMapping("/sensors/location/{locationId}")
    public Sensor createSensor(@PathVariable Long locationId,
                               @RequestBody Sensor sensor) {
        return sensorService.createSensor(locationId, sensor);
    }

   
    @GetMapping("/sensors/{id}")
    public Sensor getSensor(@PathVariable Long id) {
        return sensorService.getSensor(id);
    }


    @GetMapping("/sensors")
    public List<Sensor> getAllSensors() {
        return sensorService.getAllSensors();
    }
}
