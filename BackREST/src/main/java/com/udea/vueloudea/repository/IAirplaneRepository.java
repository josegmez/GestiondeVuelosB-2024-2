package com.udea.vueloudea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.udea.vueloudea.model.Airplane;

@Repository
public interface IAirplaneRepository extends JpaRepository<Airplane, Long> {
}