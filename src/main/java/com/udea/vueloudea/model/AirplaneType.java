package com.udea.vueloudea.model;

import jakarta.persistence.*;

@Entity
@Table(name = "airplane_type", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class AirplaneType {

    @Id
    @Column(unique = true, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    private int maxSeats;

    private String seatsDistribution; // "2-4-2", "3-3-3", etc.

    public AirplaneType(String id, Type type, int maxSeats, String seatsDistribution) {
        this.id = id;
        this.type = type;
        this.maxSeats = maxSeats;
        this.seatsDistribution = seatsDistribution;
    }

    public AirplaneType() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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