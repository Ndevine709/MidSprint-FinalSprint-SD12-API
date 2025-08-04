package com.airportAPI.rest.gates;

import com.airportAPI.rest.airport.Airport;
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

    @ManyToOne
    @JoinColumn(name = "airport_id", nullable = false)
    private Airport airport;

    public Gates() {}

    public Gates(String gateNumber, String terminal, Airport airport) {
        this.gateNumber = gateNumber;
        this.terminal = terminal;
        this.airport = airport;
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
}
