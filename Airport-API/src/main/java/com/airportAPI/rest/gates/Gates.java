package com.airportAPI.rest.gates;

import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.aircraft.Aircraft;
import jakarta.persistence.*;

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

    // Add this new relationship
    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

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
        isDepartureGate = departureGate;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }
}
