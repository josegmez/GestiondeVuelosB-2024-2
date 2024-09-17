package com.udea.vueloudea.service;

import com.udea.vueloudea.repository.IAirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udea.vueloudea.model.AirplaneType;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {

    @Autowired
    private IAirplaneRepository airplaneRepository;

    public List<AirplaneType> getAllAirplanes() {
        return airplaneRepository.findAll();
    }

    public Optional<AirplaneType> getAirplaneById(Long id) {
        return airplaneRepository.findById(id);
    }

    public AirplaneType createOrUpdateAirplane(AirplaneType airplaneType) {
        return airplaneRepository.save(airplaneType);
    }

    public void deleteAirplane(Long id) {
        airplaneRepository.deleteById(id);
    }
}