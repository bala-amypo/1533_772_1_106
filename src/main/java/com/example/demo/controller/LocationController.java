package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/api/locations")
    public Location createLocation(@RequestBody Location location) {
        return locationService.createLocation(location);
    }


    @GetMapping("/api/locations")
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/api/locations/{id}")
    public Location getLocationById(@PathVariable Long id) {
        return locationService.getLocation(id);
    }
}
