package com.udea.vueloudea.controller;

import com.udea.vueloudea.model.Flight;
import com.udea.vueloudea.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @QueryMapping
    public Optional<Flight> getFlightById(@Argument Long id) {
        return flightService.getFlightById(id);
    }

    @QueryMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }
}
