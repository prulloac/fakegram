package com.example.fakegram.dao;

import com.example.fakegram.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryDAO extends JpaRepository<Country, Long> {

    Optional<Country> findByName(String name);
}
