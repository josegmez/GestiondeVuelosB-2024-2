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
    private IFlightRepository IFlightRepository;

    public List<FlightItinerary> getAllFlightItineraries() {
        List<Flight> allFlights = IFlightRepository.findAll();
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

    private boolean isValidLayover(Flight flight1, Flight flight2) {
        // Verificar si el destino del vuelo 1 es el origen del vuelo 2
        if (!flight1.getDestination().equals(flight2.getOrigin())) {
            return false;
        }
        // Verificar si el tiempo de escala es razonable
        Duration layover = Duration.between(flight1.getArrivalTime(), flight2.getDepartureTime());
        return layover.toHours() >= 1 && layover.toHours() <= 6;
    }

    public List<Flight> getAllFlights() {
        return IFlightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return IFlightRepository.findById(id);
    }

    public Flight createOrUpdateFlight(Flight flight) {
        return IFlightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        IFlightRepository.deleteById(id);
    }


}
