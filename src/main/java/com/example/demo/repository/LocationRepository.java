package com.example.demo.repository;

import com.example.demo.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    
    // Required by Test 4.2: Location unique name simulated
    Optional<Location> findByLocationName(String name);
    
    // Required by Test 6.6 and 8.9: HQL parameterized search/filtering
    List<Location> findByRegion(String region);
}