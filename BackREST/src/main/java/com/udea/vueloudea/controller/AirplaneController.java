package com.udea.vueloudea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.udea.vueloudea.model.AirplaneType;
import com.udea.vueloudea.service.AirplaneService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    @GetMapping
    public List<AirplaneType> getAllAirplanes() {
        return airplaneService.getAllAirplanes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirplaneType> getAirplaneById(@PathVariable Long id) {
        Optional<AirplaneType> airplane = airplaneService.getAirplaneById(id);
        return airplane.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AirplaneType createAirplane(@RequestBody AirplaneType airplaneType) {
        return airplaneService.createOrUpdateAirplane(airplaneType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirplaneType> updateAirplane(@PathVariable Long id, @RequestBody AirplaneType airplaneType) {
        if (airplaneService.getAirplaneById(id).isPresent()) {
            airplaneType.setId(id);
            return ResponseEntity.ok(airplaneService.createOrUpdateAirplane(airplaneType));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirplane(@PathVariable Long id) {
        if (airplaneService.getAirplaneById(id).isPresent()) {
            airplaneService.deleteAirplane(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
