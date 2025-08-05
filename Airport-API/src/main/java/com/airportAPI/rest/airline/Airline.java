package com.airportAPI.rest.airline;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.flights.Flight;
import jakarta.persistence.*;
@Entity
@Table(name = "airline")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code; // Ex = AC for air canada

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Aircraft> aircraft = new HashSet<>();

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Flight> flights = new HashSet<>();

    public Airline () {}

    public Airline(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Aircraft> getAircraft() {
        return aircraft;
    }

    public void setAircraft(Set<Aircraft> aircraft) {
        this.aircraft = aircraft;
    }

    public void addAircraft(Aircraft aircraft) {
        this.aircraft.add(aircraft);
        aircraft.setAirline(this);
    }

    public void removeAircraft(Aircraft aircraft) {
        this.aircraft.remove(aircraft);
        aircraft.setAirline(null);
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
        flight.setAirline(this);
    }

    public void removeFlight(Flight flight) {
        this.flights.remove(flight);
        flight.setAirline(null);
    }
}
