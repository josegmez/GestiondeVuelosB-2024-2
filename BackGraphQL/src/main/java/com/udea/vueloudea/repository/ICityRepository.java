package com.udea.vueloudea.repository;

import com.udea.vueloudea.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ICityRepository extends JpaRepository<City, String> {

    // Método para buscar ciudad por código IATA
    Optional<City> findByIataCode(String iataCode);
}
