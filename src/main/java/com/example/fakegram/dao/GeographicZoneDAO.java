package com.example.fakegram.dao;

import com.example.fakegram.model.Country;
import com.example.fakegram.model.GeographicZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeographicZoneDAO extends JpaRepository<GeographicZone, Long> {
    Optional<GeographicZone> findByName(String name);

    Optional<GeographicZone> findByNameAndCountry(String name, Country country);
}
