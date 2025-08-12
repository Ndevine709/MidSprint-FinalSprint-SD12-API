package com.airportAPI.rest.flights;

import com.airportAPI.rest.airline.Airline;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.gates.Gates;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String flightNumber;
    
    @ManyToOne
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;
    
    @ManyToOne
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;
    
    @ManyToOne
    @JoinColumn(name = "departure_airport_id", nullable = false)
    private Airport departureAirport;
    
    @ManyToOne
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    private Airport arrivalAirport;
    
    @ManyToOne
    @JoinColumn(name = "departure_gate_id")
    private Gates departureGate;
    
    @ManyToOne
    @JoinColumn(name = "arrival_gate_id")
    private Gates arrivalGate;
    
    @Column(nullable = false)
    private LocalDateTime scheduledDeparture;
    
    @Column(nullable = false)
    private LocalDateTime scheduledArrival;
    
    private LocalDateTime actualDeparture;
    private LocalDateTime actualArrival;
    
    private String status; // "SCHEDULED", "BOARDING", "DEPARTED", "ARRIVED", "DELAYED", "CANCELLED"
    
    private String delayReason;
    
    public Flight() {}
    
    public Flight(String flightNumber, Airline airline, Aircraft aircraft, 
                 Airport departureAirport, Airport arrivalAirport,
                 LocalDateTime scheduledDeparture, LocalDateTime scheduledArrival) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.aircraft = aircraft;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.scheduledDeparture = scheduledDeparture;
        this.scheduledArrival = scheduledArrival;
        this.status = "SCHEDULED";
    }
    
    public Long getId() {
        return id; 
    }
    public void setId(Long id) {
        this.id = id; 
    }
    
    public String getFlightNumber() {
        return flightNumber; 
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber; 
    }
    
    public Airline getAirline() { 
        return airline; 
    }

    public void setAirline(Airline airline) {
        this.airline = airline; 
    }
    
    public Aircraft getAircraft() { 
        return aircraft; 
    }

    public void setAircraft(Aircraft aircraft) { 
        this.aircraft = aircraft; 
    }
    
    public Airport getDepartureAirport() {
        return departureAirport; 
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport; 
    }
    
    public Airport getArrivalAirport() { 
        return arrivalAirport; 
    }

    public void setArrivalAirport(Airport arrivalAirport) { 
        this.arrivalAirport = arrivalAirport; 
    }
    
    public Gates getDepartureGate() { 
        return departureGate;
    }

    public void setDepartureGate(Gates departureGate) {
        this.departureGate = departureGate; 
    }
    
    public Gates getArrivalGate() { 
        return arrivalGate;
    }

    public void setArrivalGate(Gates arrivalGate) {
        this.arrivalGate = arrivalGate;
    }
    
    public LocalDateTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(LocalDateTime scheduledDeparture) { 
        this.scheduledDeparture = scheduledDeparture; 
    }
    
    public LocalDateTime getScheduledArrival() { 
        return scheduledArrival; 
    }

    public void setScheduledArrival(LocalDateTime scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }
    
    public LocalDateTime getActualDeparture() { 
        return actualDeparture; 
    }

    public void setActualDeparture(LocalDateTime actualDeparture) { 
        this.actualDeparture = actualDeparture; 
    }
    
    public LocalDateTime getActualArrival() { 
        return actualArrival; 
    }

    public void setActualArrival(LocalDateTime actualArrival) { 
        this.actualArrival = actualArrival; 
    }
    
    public String getStatus() { 
        return status; 
    }

    public void setStatus(String status) { 
        this.status = status; 
    }
    
    public String getDelayReason() { 
        return delayReason; 
    }

    public void setDelayReason(String delayReason) { 
        this.delayReason = delayReason; 
    }
}
