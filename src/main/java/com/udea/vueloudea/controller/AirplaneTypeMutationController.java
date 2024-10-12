package com.udea.vueloudea.controller;

import com.udea.vueloudea.model.AirplaneType;
import com.udea.vueloudea.model.Type;
import com.udea.vueloudea.service.AirplaneTypeService;
import com.udea.vueloudea.service.TypeService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;


@Controller
public class AirplaneTypeMutationController {

    @Autowired
    private AirplaneTypeService airplaneTypeService;
    @Autowired
    private TypeService typeService;

    @MutationMapping
    public AirplaneType createAirplaneType(@Argument @NotBlank @NotNull String id, @Argument @Positive Long typeId, @Argument @Positive int maxSeats, @Argument String seatsDistribution) {
        id = sanitize(id);
        Type existingType = typeService.getTypeById(typeId);
        AirplaneType airplaneType = new AirplaneType();
        airplaneType.setId(id);
        airplaneType.setType(existingType);
        airplaneType.setMaxSeats(maxSeats);
        airplaneType.setSeatsDistribution(seatsDistribution);
        return airplaneTypeService.createAirplaneType(airplaneType);
    }

    @MutationMapping
    public AirplaneType updateAirplaneType(@Argument @NotBlank @NotNull String id, @Argument @Positive Long typeId, @Argument @Positive Integer maxSeats, @Argument String seatsDistribution) {
        id = sanitize(id);
        AirplaneType existingAirplaneType = airplaneTypeService.getAirplaneTypeById(id);
        if (typeId != null) existingAirplaneType.setType(typeService.getTypeById(typeId));
        if (maxSeats != null) existingAirplaneType.setMaxSeats(maxSeats);
        if (seatsDistribution != null) existingAirplaneType.setSeatsDistribution(seatsDistribution);
        return airplaneTypeService.updateAirplaneType(existingAirplaneType);
    }

    @MutationMapping
    public boolean deleteAirplaneType(@Argument @NotBlank @NotNull String id) {
        id = sanitize(id);
        airplaneTypeService.deleteAirplaneType(id);
        return true;
    }

    private String sanitize(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }
}