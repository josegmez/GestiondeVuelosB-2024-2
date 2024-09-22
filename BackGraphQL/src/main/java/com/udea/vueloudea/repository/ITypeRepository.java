package com.udea.vueloudea.repository;

import com.udea.vueloudea.model.Flight;
import com.udea.vueloudea.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeRepository extends JpaRepository<Type, Long> {
    // No es necesario agregar métodos adicionales
}
