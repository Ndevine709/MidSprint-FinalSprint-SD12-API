package com.airportAPI.rest.aircraft;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.passenger.Passenger;

@Entity
@Table(name = "aircraft")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tailNumber;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int capacity;

    @ManyToMany
    @JoinTable(name = "aircraft_airports", // ← make sure this matches your DB table
            joinColumns = @JoinColumn(name = "aircraft_id"), inverseJoinColumns = @JoinColumn(name = "airport_id"))
    private Set<Airport> airports = new HashSet<>();

    @ManyToMany(mappedBy = "flights")
    @JsonIgnore
    private Set<Passenger> passengers = new HashSet<>();

    protected Aircraft() {
    }

    public Aircraft(String tailNumber, String model, int capacity) {
        this.tailNumber = tailNumber;
        this.model = model;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<Airport> getAirports() {
        return airports;
    }

    public void setAirports(Set<Airport> airports) {
        this.airports = airports;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void addAirport(Airport airport) {
        this.airports.add(airport);
        airport.getAircraft().add(this);
    }

    public void removeAirport(Airport airport) {
        this.airports.remove(airport);
        airport.getAircraft().remove(this);
    }
}
