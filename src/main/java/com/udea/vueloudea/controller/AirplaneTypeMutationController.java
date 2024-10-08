package com.udea.vueloudea.controller;

import com.udea.vueloudea.model.AirplaneType;
import com.udea.vueloudea.model.Type;
import com.udea.vueloudea.service.AirplaneTypeService;
import com.udea.vueloudea.service.TypeService;
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
    public AirplaneType createAirplaneType(@Argument Long typeId, @Argument int maxSeats, @Argument String seatsDistribution) {
        Type existingType = typeService.getTypeById(typeId);
        AirplaneType airplaneType = new AirplaneType();
        airplaneType.setType(existingType);
        airplaneType.setMaxSeats(maxSeats);
        airplaneType.setSeatsDistribution(seatsDistribution);
        return airplaneTypeService.createOrUpdateAirplaneType(airplaneType);
    }

    @MutationMapping
    public AirplaneType updateAirplaneType(@Argument Long id, @Argument Long typeId, @Argument Integer maxSeats, @Argument String seatsDistribution) {
        AirplaneType existingAirplaneType = airplaneTypeService.getAirplaneTypeById(id);
        if (typeId != null) existingAirplaneType.setType(typeService.getTypeById(typeId));
        if (maxSeats != null) existingAirplaneType.setMaxSeats(maxSeats);
        if (seatsDistribution != null) existingAirplaneType.setSeatsDistribution(seatsDistribution);
        return airplaneTypeService.createOrUpdateAirplaneType(existingAirplaneType);
    }

    @MutationMapping
    public boolean deleteAirplaneType(@Argument Long id) {
        airplaneTypeService.deleteAirplaneType(id);
        return true;
    }
}