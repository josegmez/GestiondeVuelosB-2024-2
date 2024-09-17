package com.udea.vueloudea.repository;

import com.udea.vueloudea.model.Flight;
import com.udea.vueloudea.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepository extends JpaRepository<Status, Long> {
    // No es necesario agregar métodos adicionales
}
