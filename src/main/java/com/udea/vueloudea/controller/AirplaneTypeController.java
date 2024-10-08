package com.udea.vueloudea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.udea.vueloudea.model.AirplaneType;
import com.udea.vueloudea.service.AirplaneTypeService;
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
    public AirplaneType getAirplaneTypeById(@Argument Long id) {
        return airplaneTypeService.getAirplaneTypeById(id);
    }
}
