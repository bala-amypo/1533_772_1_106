package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.LocationEntity;

public interface LocationService {

    LocationEntity createLocation(LocationEntity location);

    List<LocationEntity> getAllLocations();
}
