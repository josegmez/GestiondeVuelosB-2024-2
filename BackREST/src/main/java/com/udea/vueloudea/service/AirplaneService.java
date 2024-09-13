package com.udea.vueloudea.service;

import com.udea.vueloudea.repository.IAirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udea.vueloudea.model.Airplane;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {

    @Autowired
    private IAirplaneRepository airplaneRepository;

    public List<Airplane> getAllAirplanes() {
        return airplaneRepository.findAll();
    }

    public Optional<Airplane> getAirplaneById(Long id) {
        return airplaneRepository.findById(id);
    }

    public Airplane createOrUpdateAirplane(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    public void deleteAirplane(Long id) {
        airplaneRepository.deleteById(id);
    }
}