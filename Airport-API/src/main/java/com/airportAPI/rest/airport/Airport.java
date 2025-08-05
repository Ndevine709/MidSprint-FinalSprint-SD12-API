package com.airportAPI.rest.airport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.airportAPI.rest.city.City;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.gates.Gates;
import com.airportAPI.rest.flights.Flight;

@Entity
@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany(mappedBy = "airports")
    @JsonIgnore 
    private Set<Aircraft> aircraft = new HashSet<>();

    @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Gates> gates = new HashSet<>();

    // Add these new relationships
    @OneToMany(mappedBy = "departureAirport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Flight> departures = new HashSet<>();

    @OneToMany(mappedBy = "arrivalAirport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Flight> arrivals = new HashSet<>();

    protected Airport() {
    }

    public Airport(String name, String code, City city) {
        this.name = name;
        this.code = code;
        this.city = city;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Aircraft> getAircraft() {
        return aircraft;
    }

    public void setAircraft(Set<Aircraft> aircraft) {
        this.aircraft = aircraft;
    }

    public void addAircraft(Aircraft ac) {
        this.aircraft.add(ac);
        ac.getAirports().add(this);
    }

    public void removeAircraft(Aircraft ac) {
        this.aircraft.remove(ac);
        ac.getAirports().remove(this);
    }

    public Set<Gates> getGates() {
        return gates;
    }

    public void setGates(Set<Gates> gates) {
        this.gates = gates;
    }

    public void addGate(Gates gate) {
        this.gates.add(gate);
        gate.setAirport(this);
    }

    public void removeGate(Gates gate) {
        this.gates.remove(gate);
        gate.setAirport(null);
    }

    public Set<Flight> getDepartures() {
        return departures;
    }

    public void setDepartures(Set<Flight> departures) {
        this.departures = departures;
    }

    public Set<Flight> getArrivals() {
        return arrivals;
    }

    public void setArrivals(Set<Flight> arrivals) {
        this.arrivals = arrivals;
    }

    public void addDeparture(Flight flight) {
        this.departures.add(flight);
        flight.setDepartureAirport(this);
    }

    public void addArrival(Flight flight) {
        this.arrivals.add(flight);
        flight.setArrivalAirport(this);
    }
}
