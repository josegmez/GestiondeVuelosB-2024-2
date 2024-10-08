package com.udea.vueloudea.controller;

import com.udea.vueloudea.model.*;
import com.udea.vueloudea.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Controller
public class FlightMutationController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private CityService cityService;
    @Autowired
    private FlightTypeService flightTypeService;
    @Autowired
    private AirplaneTypeService airplaneTypeService;
    @Autowired
    private StatusService statusService;

    @MutationMapping
    public Flight createFlight(@Argument String flightNumber,
                               @Argument String originIata,
                               @Argument String destinationIata,
                               @Argument double price,
                               @Argument double taxPercentage,
                               @Argument double surchargePercentage,
                               @Argument Long flightTypeId,
                               @Argument Long airplaneTypeId,
                               @Argument String departureDate,
                               @Argument String arrivalDate,
                               @Argument String departureTime,
                               @Argument String arrivalTime,
                               @Argument Long statusId) {

        Flight flight = new Flight();
        flight.setFlightNumber(flightNumber);
        flight.setOrigin(cityService.getCityByIataCode(originIata));
        flight.setDestination(cityService.getCityByIataCode(destinationIata));
        flight.setPrice(price);
        flight.setTaxPercentage(taxPercentage);
        flight.setSurchargePercentage(surchargePercentage);
        flight.setDepartureDate(LocalDate.parse(departureDate));
        flight.setArrivalDate(LocalDate.parse(arrivalDate));
        flight.setDepartureTime(LocalTime.parse(departureTime));
        flight.setArrivalTime(LocalTime.parse(arrivalTime));
        flight.setFlightType(flightTypeService.getFlightTypeById(flightTypeId));
        flight.setAirplaneType(airplaneTypeService.getAirplaneTypeById(airplaneTypeId));
        flight.setStatus(statusService.getStatusById(statusId));

        return flightService.createOrUpdateFlight(flight);
    }

    @MutationMapping
    public Flight updateFlight(@Argument Long id,
                               @Argument String flightNumber,
                               @Argument String originIata,
                               @Argument String destinationIata,
                               @Argument Double price,
                               @Argument Double taxPercentage,
                               @Argument Double surchargePercentage,
                               @Argument Long flightTypeId,
                               @Argument Long airplaneTypeId,
                               @Argument String departureDate,
                               @Argument String arrivalDate,
                               @Argument String departureTime,
                               @Argument String arrivalTime,
                               @Argument Long statusId) {

        Optional<Flight> existingFlightOpt = flightService.getFlightById(id);
        if (existingFlightOpt.isPresent()) {
            Flight existingFlight = existingFlightOpt.get();
            if (flightNumber != null) existingFlight.setFlightNumber(flightNumber);
            if (originIata != null) existingFlight.setOrigin(cityService.getCityByIataCode(originIata));
            if (destinationIata != null) existingFlight.setDestination(cityService.getCityByIataCode(destinationIata));
            if (price != null) existingFlight.setPrice(price);
            if (taxPercentage != null) existingFlight.setTaxPercentage(taxPercentage);
            if (surchargePercentage != null) existingFlight.setSurchargePercentage(surchargePercentage);
            if (departureDate != null) existingFlight.setDepartureDate(LocalDate.parse(departureDate));
            if (arrivalDate != null) existingFlight.setArrivalDate(LocalDate.parse(arrivalDate));
            if (departureTime != null) existingFlight.setDepartureTime(LocalTime.parse(departureTime));
            if (arrivalTime != null) existingFlight.setArrivalTime(LocalTime.parse(arrivalTime));
            if (flightTypeId != null) existingFlight.setFlightType(flightTypeService.getFlightTypeById(flightTypeId));
            if (airplaneTypeId != null) existingFlight.setAirplaneType(airplaneTypeService.getAirplaneTypeById(airplaneTypeId));
            if (statusId != null) existingFlight.setStatus(statusService.getStatusById(statusId));

            return flightService.createOrUpdateFlight(existingFlight);
        } else {
            throw new RuntimeException("Flight not found with id: " + id);
        }
    }

    @MutationMapping
    public boolean deleteFlight(@Argument Long id) {
        flightService.deleteFlight(id);
        return true;
    }
}