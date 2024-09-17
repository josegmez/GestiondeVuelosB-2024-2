package com.udea.vueloudea.service;

import com.udea.vueloudea.model.Status;
import com.udea.vueloudea.repository.IStatusRepository; // Asegúrate de tener este repositorio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    @Autowired
    private IStatusRepository statusRepository;

    // Método para obtener un Status por su ID
    public Status getStatusById(Long id) {
        Optional<Status> statusOpt = statusRepository.findById(id);
        return statusOpt.orElse(null); // Retorna null si no se encuentra
    }

    // Método para listar todos los estados (opcional)
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }
}