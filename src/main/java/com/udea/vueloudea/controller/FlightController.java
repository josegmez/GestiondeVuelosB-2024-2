package com.udea.vueloudea.controller;

import com.udea.vueloudea.model.Flight;
import com.udea.vueloudea.service.FlightService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @QueryMapping
    public Flight getFlightById(@Argument @Positive @NotNull @NotBlank Long id) {
        return flightService.getFlightById(id);
    }

    @QueryMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }
}
