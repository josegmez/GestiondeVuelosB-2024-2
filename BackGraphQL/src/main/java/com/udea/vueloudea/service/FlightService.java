package com.udea.vueloudea.service;

import com.udea.vueloudea.model.Flight;
import com.udea.vueloudea.model.FlightItinerary;
import com.udea.vueloudea.repository.IFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
public class FlightService {

    @Autowired
    private IFlightRepository flightRepository;

    // Método que retorna todos los itinerarios de vuelo (vuelos directos y con escalas)
    public List<FlightItinerary> getAllFlightItineraries() {
        List<Flight> allFlights = flightRepository.findAll();
        List<FlightItinerary> itineraries = new ArrayList<>();

        // Agregar todos los vuelos directos como itinerarios
        for (Flight flight : allFlights) {
            itineraries.add(new FlightItinerary(Collections.singletonList(flight)));
        }

        // Combinar vuelos para crear itinerarios con escalas
        for (Flight flight1 : allFlights) {
            for (Flight flight2 : allFlights) {
                if (isValidLayover(flight1, flight2)) {
                    List<Flight> flights = Arrays.asList(flight1, flight2);
                    itineraries.add(new FlightItinerary(flights));
                }
            }
        }

        return itineraries;
    }

    // Método para verificar si una escala entre dos vuelos es válida
    private boolean isValidLayover(Flight flight1, Flight flight2) {
        // Verificar si el destino del vuelo 1 es el origen del vuelo 2
        if (!flight1.getDestination().equals(flight2.getOrigin())) {
            return false;
        }
        // Verificar si el tiempo de escala es razonable
        Duration layover = Duration.between(flight1.getArrivalTime(), flight2.getDepartureTime());
        return layover.toHours() >= 1 && layover.toHours() <= 6;
    }

    // Retornar todos los vuelos
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Retornar un vuelo por su ID
    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    // Crear o actualizar un vuelo
    public Flight createOrUpdateFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // Eliminar un vuelo
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
