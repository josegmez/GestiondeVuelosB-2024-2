package com.udea.vueloudea.service;


import com.udea.vueloudea.model.City;
import com.udea.vueloudea.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private ICityRepository cityRepository;

    // Método para buscar una ciudad por su código IATA
    public City getCityByIataCode(String iataCode) {
        return cityRepository.findByIataCode(iataCode)
                .orElseThrow(() -> new RuntimeException("City with IATA code " + iataCode + " not found"));
    }
}