package com.udea.vueloudea.controller;

import com.udea.vueloudea.model.*;
import com.udea.vueloudea.service.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    public Flight createFlight(@Argument @NotBlank @NotNull String flightNumber,
                               @Argument @NotBlank @NotNull String originIata,
                               @Argument @NotBlank @NotNull String destinationIata,
                               @Argument @NotBlank @NotNull @Positive double price,
                               @Argument @NotBlank @NotNull @Positive double taxPercentage,
                               @Argument @NotBlank @NotNull @Positive double surchargePercentage,
                               @Argument @NotBlank @NotNull @Positive Long flightTypeId,
                               @Argument @NotBlank @NotNull String airplaneTypeId,
                               @Argument @NotBlank @NotNull String departureDate,
                               @Argument @NotBlank @NotNull String arrivalDate,
                               @Argument @NotBlank @NotNull String departureTime,
                               @Argument @NotBlank @NotNull String arrivalTime,
                               @Argument @NotBlank @NotNull Long statusId) {

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

        return flightService.createFlight(flight);
    }

    @MutationMapping
    public Flight updateFlight(@Argument @NotBlank @NotNull @Positive Long id,
                               @Argument String flightNumber,
                               @Argument String originIata,
                               @Argument String destinationIata,
                               @Argument @Positive Double price,
                               @Argument @Positive Double taxPercentage,
                               @Argument @Positive Double surchargePercentage,
                               @Argument @Positive Long flightTypeId,
                               @Argument String airplaneTypeId,
                               @Argument String departureDate,
                               @Argument String arrivalDate,
                               @Argument String departureTime,
                               @Argument String arrivalTime,
                               @Argument @Positive Long statusId) {

        id = Long.valueOf(sanitize(id.toString()));
        Optional<Flight> existingFlightOpt = flightService.getFlightById(id);
        if (existingFlightOpt.isPresent()) {
            Flight existingFlight = existingFlightOpt.get();
            if (flightNumber != null) existingFlight.setFlightNumber(sanitize(flightNumber));
            if (originIata != null) existingFlight.setOrigin(cityService.getCityByIataCode(sanitize(originIata)));
            if (destinationIata != null) existingFlight.setDestination(cityService.getCityByIataCode(sanitize(destinationIata)));
            if (price != null) existingFlight.setPrice(price);
            if (taxPercentage != null) existingFlight.setTaxPercentage(taxPercentage);
            if (surchargePercentage != null) existingFlight.setSurchargePercentage(surchargePercentage);
            if (departureDate != null) existingFlight.setDepartureDate(LocalDate.parse(departureDate));
            if (arrivalDate != null) existingFlight.setArrivalDate(LocalDate.parse(arrivalDate));
            if (departureTime != null) existingFlight.setDepartureTime(LocalTime.parse(departureTime));
            if (arrivalTime != null) existingFlight.setArrivalTime(LocalTime.parse(arrivalTime));
            if (flightTypeId != null) existingFlight.setFlightType(flightTypeService.getFlightTypeById(flightTypeId));
            if (airplaneTypeId != null) existingFlight.setAirplaneType(airplaneTypeService.getAirplaneTypeById(sanitize(airplaneTypeId)));
            if (statusId != null) existingFlight.setStatus(statusService.getStatusById(statusId));

            return flightService.updateFlight(existingFlight);
        } else {
            throw new RuntimeException("Flight not found with id: " + id);
        }
    }

    @MutationMapping
    public boolean deleteFlight(@Argument @NotBlank @NotNull @Positive Long id) {
        flightService.deleteFlight(id);
        return true;
    }

    private String sanitize(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }
}