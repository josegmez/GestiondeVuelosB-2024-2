package com.udea.vueloudea.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightItinerary {
    private List<Flight> flights;  // Lista de vuelos que forman el itinerario
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Duration totalDuration;
    private List<Duration> layoverTimes;  // Lista de tiempos de escala
    private double totalPrice;

    public List<Flight> getFlights() {
        return flights;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public Duration getTotalDuration() {
        return totalDuration;
    }

    public List<Duration> getLayoverTimes() {
        return layoverTimes;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public FlightItinerary(List<Flight> flights) {
        this.flights = flights;
        this.origin = flights.get(0).getOrigin().getIataCode();  // Obtén el código IATA de la ciudad de origen
        this.destination = flights.get(flights.size() - 1).getDestination().getIataCode();  // Obtén el código IATA de la ciudad de destino
        this.departureDate = flights.get(0).getDepartureDate();
        this.arrivalDate = flights.get(flights.size() - 1).getArrivalDate();
        this.totalDuration = calculateTotalDuration();
        this.layoverTimes = calculateLayoverTimes();
        this.totalPrice = calculateTotalPrice();
    }

    private Duration calculateTotalDuration() {
        Duration totalDuration = Duration.ZERO;

        // Sumar duración de todos los vuelos
        for (Flight flight : flights) {
            Duration flightDuration = Duration.between(flight.getDepartureTime(), flight.getArrivalTime());
            totalDuration = totalDuration.plus(flightDuration);
        }

        // Añadir tiempos de escala
        for (Duration layover : calculateLayoverTimes()) {
            totalDuration = totalDuration.plus(layover);
        }

        return totalDuration;
    }

    private List<Duration> calculateLayoverTimes() {
        List<Duration> layoverTimes = new ArrayList<>();

        for (int i = 0; i < flights.size() - 1; i++) {
            Flight currentFlight = flights.get(i);
            Flight nextFlight = flights.get(i + 1);
            Duration layover = Duration.between(currentFlight.getArrivalTime(), nextFlight.getDepartureTime());
            layoverTimes.add(layover);
        }

        return layoverTimes;
    }

    private double calculateTotalPrice() {
        double totalPrice = 0;

        for (Flight flight : flights) {
            double priceWithTax = flight.getPrice() * (1 + flight.getTaxPercentage() / 100);
            double priceWithSurcharge = priceWithTax * (1 + flight.getSurchargePercentage() / 100);
            totalPrice += priceWithSurcharge;
        }

        return totalPrice;
    }

    // Getters y otros métodos útiles pueden añadirse aquí.
}
