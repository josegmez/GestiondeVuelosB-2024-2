package com.udea.vueloudea.repository;

import com.udea.vueloudea.model.Flight;
import com.udea.vueloudea.model.FlightType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlightTypeRepository extends JpaRepository<FlightType, Long> {
    // No es necesario agregar métodos adicionales
}
