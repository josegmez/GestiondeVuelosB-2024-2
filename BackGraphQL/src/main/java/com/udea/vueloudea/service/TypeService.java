package com.udea.vueloudea.service;

import com.udea.vueloudea.model.Type;
import com.udea.vueloudea.repository.ITypeRepository; // Asegúrate de tener este repositorio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    private ITypeRepository typeRepository;

    // Método para obtener un Type por su ID
    public Type getTypeById(Long id) {
        Optional<Type> typeOpt = typeRepository.findById(id);
        return typeOpt.orElse(null); // Retorna null si no se encuentra
    }

    // Método para listar todos los tipos (opcional)
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }
}