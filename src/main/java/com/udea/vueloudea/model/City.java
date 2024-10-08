package com.udea.vueloudea.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class City {
    @Id
    private String iataCode; // Código IATA (ej: BOG, MDE)
    private String airportName; // Nombre completo del aeropuerto
    private String country;

    public City() {

    }

    public City(String iataCode, String airportName, String country) {
        this.iataCode = iataCode;
        this.airportName = airportName;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
    // Getters, Setters, Constructor, etc.
}
