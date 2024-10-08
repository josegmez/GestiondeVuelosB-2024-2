package com.udea.vueloudea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.udea.vueloudea.model.AirplaneType;

@Repository
public interface IAirplaneTypeRepository extends JpaRepository<AirplaneType, Long> {
}