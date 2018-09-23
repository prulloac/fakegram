package com.example.fakegram.service;

import com.example.fakegram.dao.CountryDAO;
import com.example.fakegram.dao.GeographicZoneDAO;
import com.example.fakegram.model.Country;
import com.example.fakegram.model.GeographicZone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GeographicZoneService {

    private final GeographicZoneDAO geographicZoneDAO;
    private final CountryDAO countryDAO;

    @Autowired
    public GeographicZoneService(CountryDAO countryDAO, GeographicZoneDAO geographicZoneDAO) {
        this.countryDAO = countryDAO;
        this.geographicZoneDAO = geographicZoneDAO;
    }

    public Country getOrCreateCountry(String name) {
        return countryDAO.findByName(name).orElseGet(() -> {
            Country country = new Country();
            country.setName(name);
            countryDAO.saveAndFlush(country);
            return country;
        });
    }

    public GeographicZone getOrCreateGeographicZone(String name, Country country) {
        return geographicZoneDAO.findByNameAndCountry(name, country).orElseGet(()-> {
            GeographicZone geographicZone = new GeographicZone();
            geographicZone.setCountry(country);
            geographicZone.setName(name);
            geographicZoneDAO.saveAndFlush(geographicZone);
            return geographicZone;
        });
    }

    public GeographicZone getFromLocationString(String location) {
        if (null == location)
            return null;
        String countryName = location.split(", ")[1];
        String zoneName = location.split(", ")[0];
        Country country = getOrCreateCountry(countryName);
        return getOrCreateGeographicZone(zoneName, country);
    }

}
