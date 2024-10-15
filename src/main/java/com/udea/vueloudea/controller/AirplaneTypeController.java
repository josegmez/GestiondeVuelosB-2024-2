package com.udea.vueloudea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.udea.vueloudea.model.AirplaneType;
import com.udea.vueloudea.service.AirplaneTypeService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class AirplaneTypeController {

    @Autowired
    private AirplaneTypeService airplaneTypeService;

    @QueryMapping
    public List<AirplaneType> getAllAirplaneTypes() {
        return airplaneTypeService.getAllAirplaneTypes();
    }

    @QueryMapping
    public AirplaneType getAirplaneTypeById(@Argument @NotBlank String id) {
        id = sanitize(id);
        return airplaneTypeService.getAirplaneTypeById(id);
    }

    private String sanitize(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }


}