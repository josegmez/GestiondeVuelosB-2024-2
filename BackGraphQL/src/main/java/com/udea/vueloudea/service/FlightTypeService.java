package com.udea.vueloudea.service;

import com.udea.vueloudea.model.FlightType;
import com.udea.vueloudea.repository.IFlightTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightTypeService {

    @Autowired
    private IFlightTypeRepository flightTypeRepository;

    // Método para obtener un FlightType por su ID
    public FlightType getFlightTypeById(Long id) {
        Optional<FlightType> flightTypeOpt = flightTypeRepository.findById(id);
        return flightTypeOpt.orElse(null); // Retorna null si no se encuentra
    }

    // Método para listar todos los tipos de vuelo (opcional)
    public List<FlightType> getAllFlightTypes() {
        return flightTypeRepository.findAll();
    }
}