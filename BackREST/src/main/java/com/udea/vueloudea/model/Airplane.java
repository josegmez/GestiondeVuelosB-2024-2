package com.udea.vueloudea.model;

import jakarta.persistence.*;

@Entity
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "airplane_type_id")
    private AirplaneType airplaneType;

    private int maxSeats;

    private String seatsDistribution; // "2-4-2", "3-3-3", etc.

    public Airplane(Long id, AirplaneType airplaneType, int maxSeats, String seatsDistribution) {
        this.id = id;
        this.airplaneType = airplaneType;
        this.maxSeats = maxSeats;
        this.seatsDistribution = seatsDistribution;
    }

    public Airplane() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public String getSeatsDistribution() {
        return seatsDistribution;
    }

    public void setSeatsDistribution(String seatsDistribution) {
        this.seatsDistribution = seatsDistribution;
    }
}
