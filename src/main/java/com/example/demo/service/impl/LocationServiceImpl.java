package com.example.demo.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Location;
import com.example.demo.repository.LocationRepository;
import com.example.demo.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location createLocation(Location location) {

        if (location.getRegion() == null || location.getRegion().isBlank()) {
            throw new IllegalArgumentException("region required");
        }


        locationRepository.findByLocationName(location.getLocationName())
                .ifPresent(l -> {
                    throw new IllegalArgumentException("Location name already exists");
                });

        location.setCreatedAt(LocalDateTime.now());

        return locationRepository.save(location);
    }
}
