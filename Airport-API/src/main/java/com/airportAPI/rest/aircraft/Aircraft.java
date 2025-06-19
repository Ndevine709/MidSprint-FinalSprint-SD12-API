package com.airportAPI.rest.aircraft;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

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

    // which airports this aircraft can use
    @ManyToMany
    @JoinTable(
      name = "aircraft_airport",
      joinColumns = @JoinColumn(name = "aircraft_id"),
      inverseJoinColumns = @JoinColumn(name = "airport_id")
    )
    private Set<Airport> airports = new HashSet<>();

    // we don’t want to serialize passengers here or you'll get nesting again
    @ManyToMany(mappedBy = "flights")
    @JsonIgnore
    private Set<Passenger> passengers = new HashSet<>();

    protected Aircraft() {
        // JPA
    }

    public Aircraft(String tailNumber, String model, int capacity) {
        this.tailNumber = tailNumber;
        this.model      = model;
        this.capacity   = capacity;
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

    // passengers will be ignored in JSON
    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }
}
