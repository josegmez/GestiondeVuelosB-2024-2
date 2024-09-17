package com.udea.vueloudea.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "flight_type_id")
    private FlightType flightType;

    @ManyToOne
    @JoinColumn(name = "origin_iata")
    private City origin;

    @ManyToOne
    @JoinColumn(name = "destination_iata")
    private City destination;

    @ManyToOne
    @JoinColumn(name = "airplane_type_id")
    private AirplaneType airplaneType;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double price;
    private double taxPercentage;  // Porcentaje de impuestos
    private double surchargePercentage;  // Sobretasa en porcentaje
    // Constructor vacío
    public Flight() {
    }

    public Flight(Long id, String flightNumber, FlightType flightType, City origin, City destination, AirplaneType airplaneType, Status status, LocalDate departureDate, LocalDate arrivalDate, LocalTime departureTime, LocalTime arrivalTime, double price, double taxPercentage, double surchargePercentage) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.flightType = flightType;
        this.origin = origin;
        this.destination = destination;
        this.airplaneType = airplaneType;
        this.status = status;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.taxPercentage = taxPercentage;
        this.surchargePercentage = surchargePercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public double getSurchargePercentage() {
        return surchargePercentage;
    }

    public void setSurchargePercentage(double surchargePercentage) {
        this.surchargePercentage = surchargePercentage;
    }

    // Métodos equals y hashCode basados en el id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
