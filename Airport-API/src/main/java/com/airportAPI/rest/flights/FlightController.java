package com.airportAPI.rest.flights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class FlightController {
    
    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        try {
            Flight flight = flightService.getFlightById(id);
            return ResponseEntity.ok(flight);
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/flights")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        try {
            Flight createdFlight = flightService.createFlight(flight);
            return ResponseEntity.ok(createdFlight);
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/flights/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        try {
            Flight updatedFlight = flightService.updateFlight(id, flight);
            return ResponseEntity.ok(updatedFlight);
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/flights/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    // Convenience endpoints for arrivals and departures
    @GetMapping("/flights/airport/{airportId}/departures")
    public List<Flight> getDeparturesByAirport(@PathVariable Long airportId) {
        return flightService.getDeparturesByAirport(airportId);
    }

    @GetMapping("/flights/airport/{airportId}/arrivals")
    public List<Flight> getArrivalsByAirport(@PathVariable Long airportId) {
        return flightService.getArrivalsByAirport(airportId);
    }

    @GetMapping("/flights/airline/{airlineId}")
    public List<Flight> getFlightsByAirline(@PathVariable Long airlineId) {
        return flightService.getFlightsByAirline(airlineId);
    }

    @GetMapping("/flights/status/{status}")
    public List<Flight> getFlightsByStatus(@PathVariable String status) {
        return flightService.getFlightsByStatus(status);
    }

    @GetMapping("/flights/airport/{airportId}/departures/status/{status}")
    public List<Flight> getDeparturesByAirportAndStatus(@PathVariable Long airportId, @PathVariable String status) {
        return flightService.getDeparturesByAirportAndStatus(airportId, status);
    }

    @GetMapping("/flights/airport/{airportId}/arrivals/status/{status}")
    public List<Flight> getArrivalsByAirportAndStatus(@PathVariable Long airportId, @PathVariable String status) {
        return flightService.getArrivalsByAirportAndStatus(airportId, status);
    }

    // Update flight status
    @PutMapping("/flights/{id}/status")
    public ResponseEntity<Flight> updateFlightStatus(@PathVariable Long id, @RequestBody String status) {
        try {
            Flight flight = flightService.getFlightById(id);
            flight.setStatus(status);
            Flight updatedFlight = flightService.updateFlight(id, flight);
            return ResponseEntity.ok(updatedFlight);
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
