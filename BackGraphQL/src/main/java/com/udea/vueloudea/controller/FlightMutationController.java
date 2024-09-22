package com.udea.vueloudea.controller;

import com.udea.vueloudea.model.*;
import com.udea.vueloudea.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class FlightMutationController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private CityService cityService;

    @Autowired
    private FlightTypeService flightTypeService; // Asegúrate de tener este servicio
    @Autowired
    private AirplaneTypeService airplaneTypeService; // Asegúrate de tener este servicio
    @Autowired
    private StatusService statusService; // Asegúrate de tener este servicio

    @MutationMapping
    public Flight createFlight(@Argument String flightNumber,
                               @Argument String originIata,
                               @Argument String destinationIata,
                               @Argument double price,
                               @Argument double taxPercentage,
                               @Argument double surchargePercentage,
                               @Argument Long flightTypeId,
                               @Argument Long airplaneTypeId,
                               @Argument Long statusId) {

        Flight flight = new Flight();
        flight.setFlightNumber(flightNumber);

        City originCity = cityService.getCityByIataCode(originIata);
        City destinationCity = cityService.getCityByIataCode(destinationIata);

        flight.setOrigin(originCity);
        flight.setDestination(destinationCity);
        flight.setPrice(price);
        flight.setTaxPercentage(taxPercentage);
        flight.setSurchargePercentage(surchargePercentage);

        FlightType flightType = flightTypeService.getFlightTypeById(flightTypeId);
        AirplaneType airplaneType = airplaneTypeService.getAirplaneTypeById(airplaneTypeId);

        flight.setFlightType(flightType);
        flight.setAirplaneType(airplaneType);

        Status status = statusService.getStatusById(statusId);
        flight.setStatus(status);

        return flightService.createOrUpdateFlight(flight);
    }

    @MutationMapping
    public Flight updateFlight(
            @Argument Long id,
            @Argument String flightNumber,
            @Argument String originIata,
            @Argument String destinationIata,
            @Argument double price,
            @Argument double taxPercentage,
            @Argument double surchargePercentage,
            @Argument Long flightTypeId,
            @Argument Long airplaneTypeId,
            @Argument Long statusId) {

        // Buscar el vuelo existente por ID
        Optional<Flight> existingFlightOpt = flightService.getFlightById(id);

        // Verificar si el vuelo existe
        if (existingFlightOpt.isPresent()) {
            Flight existingFlight = existingFlightOpt.get(); // Obtener el vuelo

            // Actualizar los campos del vuelo
            existingFlight.setFlightNumber(flightNumber);

            // Buscar las ciudades por código IATA
            City originCity = cityService.getCityByIataCode(originIata);
            City destinationCity = cityService.getCityByIataCode(destinationIata);

            // Asignar las ciudades al vuelo
            existingFlight.setOrigin(originCity);
            existingFlight.setDestination(destinationCity);

            // Asignar otros atributos
            existingFlight.setPrice(price);
            existingFlight.setTaxPercentage(taxPercentage);
            existingFlight.setSurchargePercentage(surchargePercentage);

            // Obtener los tipos de vuelo, avión y estado
            FlightType flightType = flightTypeService.getFlightTypeById(flightTypeId);
            AirplaneType airplaneType = airplaneTypeService.getAirplaneTypeById(airplaneTypeId);
            Status status = statusService.getStatusById(statusId);

            // Asignar tipos y estado al vuelo
            existingFlight.setFlightType(flightType);
            existingFlight.setAirplaneType(airplaneType);
            existingFlight.setStatus(status);

            // Guardar el vuelo actualizado usando el servicio correspondiente
            return flightService.createOrUpdateFlight(existingFlight);
        } else {
            throw new RuntimeException("Flight not found with id: " + id); // Manejar el caso donde no se encuentra el vuelo
        }
    }

    @MutationMapping
    public boolean deleteFlight(@Argument Long id) {
        flightService.deleteFlight(id);
        return true;
    }
}