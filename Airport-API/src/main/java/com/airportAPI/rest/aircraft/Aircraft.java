package com.airportAPI.rest.aircraft;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.passenger.Passenger;
import com.airportAPI.rest.gates.Gates;
import com.airportAPI.rest.airline.Airline;
import com.airportAPI.rest.flights.Flight;

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
    @JoinTable(name = "aircraft_airports", 
            joinColumns = @JoinColumn(name = "aircraft_id"), inverseJoinColumns = @JoinColumn(name = "airport_id"))
    private Set<Airport> airports = new HashSet<>();

    @ManyToMany(mappedBy = "flights")
    @JsonIgnore
    private Set<Passenger> passengers = new HashSet<>();
    
    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Gates> assignedGates = new HashSet<>();

    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Flight> flights = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    public Aircraft() {
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

    public Set<Gates> getAssignedGates() {
        return assignedGates;
    }

    public void setAssignedGates(Set<Gates> assignedGates) {
        this.assignedGates = assignedGates;
    }

    public void addAssignedGate(Gates gate) {
        this.assignedGates.add(gate);
        gate.setAircraft(this);
    }

    public void removeAssignedGate(Gates gate) {
        this.assignedGates.remove(gate);
        gate.setAircraft(null);
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
        flight.setAircraft(this);
    }

    public void removeFlight(Flight flight) {
        this.flights.remove(flight);
        flight.setAircraft(null);
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
