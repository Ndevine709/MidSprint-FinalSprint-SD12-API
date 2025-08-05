package com.airportAPI.rest.gates;

import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.flights.Flight;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "gates")
public class Gates {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String gateNumber;

    @Column(nullable = false)
    private String terminal;

    @Column(nullable = false)
    private boolean isDepartureGate; 

    @ManyToOne
    @JoinColumn(name = "airport_id", nullable = false)
    private Airport airport;

    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @OneToMany(mappedBy = "departureGate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Flight> departureFlights = new HashSet<>();

    @OneToMany(mappedBy = "arrivalGate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Flight> arrivalFlights = new HashSet<>();

    public Gates() {}

    public Gates(String gateNumber, String terminal, Airport airport, boolean isDepartureGate) {
        this.gateNumber = gateNumber;
        this.terminal = terminal;
        this.airport = airport;
        this.isDepartureGate = isDepartureGate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public boolean isDepartureGate() {
        return isDepartureGate;
    }

    public void setDepartureGate(boolean departureGate) {
        this.isDepartureGate = departureGate;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Set<Flight> getDepartureFlights() {
        return departureFlights;
    }

    public void setDepartureFlights(Set<Flight> departureFlights) {
        this.departureFlights = departureFlights;
    }

    public Set<Flight> getArrivalFlights() {
        return arrivalFlights;
    }

    public void setArrivalFlights(Set<Flight> arrivalFlights) {
        this.arrivalFlights = arrivalFlights;
    }
}
