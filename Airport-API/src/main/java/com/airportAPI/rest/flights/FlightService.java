package com.airportAPI.rest.flights;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.airportAPI.rest.airline.Airline;
import com.airportAPI.rest.airline.AirlineRepository;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.aircraft.AircraftRepository;
import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.airport.AirportRepository;
import com.airportAPI.rest.gates.Gates;
import com.airportAPI.rest.gates.GatesRepository;

@Service
public class FlightService {
    
    private final FlightRepository flightRepository;
    private final AirlineRepository airlineRepository;
    private final AircraftRepository aircraftRepository;
    private final AirportRepository airportRepository;
    private final GatesRepository gatesRepository;

    public FlightService(FlightRepository flightRepository, AirlineRepository airlineRepository,
                        AircraftRepository aircraftRepository, AirportRepository airportRepository,
                        GatesRepository gatesRepository) {
        this.flightRepository = flightRepository;
        this.airlineRepository = airlineRepository;
        this.aircraftRepository = aircraftRepository;
        this.airportRepository = airportRepository;
        this.gatesRepository = gatesRepository;
    }

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        flightRepository.findAll().forEach(flights::add);
        return flights;
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + id));
    }

    public Flight createFlight(Flight flight) {
        // Validate and set related entities
        Long airlineId = flight.getAirline().getId();
        Airline airline = airlineRepository.findById(airlineId)
            .orElseThrow(() -> new RuntimeException("Airline not found with ID: " + airlineId));
        flight.setAirline(airline);

        Long aircraftId = flight.getAircraft().getId();
        Aircraft aircraft = aircraftRepository.findById(aircraftId)
            .orElseThrow(() -> new RuntimeException("Aircraft not found with ID: " + aircraftId));
        flight.setAircraft(aircraft);

        Long departureAirportId = flight.getDepartureAirport().getId();
        Airport departureAirport = airportRepository.findById(departureAirportId)
            .orElseThrow(() -> new RuntimeException("Departure airport not found with ID: " + departureAirportId));
        flight.setDepartureAirport(departureAirport);

        Long arrivalAirportId = flight.getArrivalAirport().getId();
        Airport arrivalAirport = airportRepository.findById(arrivalAirportId)
            .orElseThrow(() -> new RuntimeException("Arrival airport not found with ID: " + arrivalAirportId));
        flight.setArrivalAirport(arrivalAirport);

        if (flight.getDepartureGate() != null) {
            Long departureGateId = flight.getDepartureGate().getId();
            Gates departureGate = gatesRepository.findById(departureGateId)
                .orElseThrow(() -> new RuntimeException("Departure gate not found with ID: " + departureGateId));
            flight.setDepartureGate(departureGate);
        }

        if (flight.getArrivalGate() != null) {
            Long arrivalGateId = flight.getArrivalGate().getId();
            Gates arrivalGate = gatesRepository.findById(arrivalGateId)
                .orElseThrow(() -> new RuntimeException("Arrival gate not found with ID: " + arrivalGateId));
            flight.setArrivalGate(arrivalGate);
        }

        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight flightDetails) {
        return flightRepository.findById(id).map(flight -> {
            flight.setFlightNumber(flightDetails.getFlightNumber());
            flight.setScheduledDeparture(flightDetails.getScheduledDeparture());
            flight.setScheduledArrival(flightDetails.getScheduledArrival());
            flight.setActualDeparture(flightDetails.getActualDeparture());
            flight.setActualArrival(flightDetails.getActualArrival());
            flight.setStatus(flightDetails.getStatus());
            flight.setDelayReason(flightDetails.getDelayReason());
            
            if (flightDetails.getAirline() != null) {
                Long airlineId = flightDetails.getAirline().getId();
                Airline airline = airlineRepository.findById(airlineId)
                    .orElseThrow(() -> new RuntimeException("Airline not found with ID: " + airlineId));
                flight.setAirline(airline);
            }
            
            if (flightDetails.getAircraft() != null) {
                Long aircraftId = flightDetails.getAircraft().getId();
                Aircraft aircraft = aircraftRepository.findById(aircraftId)
                    .orElseThrow(() -> new RuntimeException("Aircraft not found with ID: " + aircraftId));
                flight.setAircraft(aircraft);
            }
            
            return flightRepository.save(flight);
        }).orElseThrow(() -> new RuntimeException("Flight not found with ID: " + id));
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public List<Flight> getDeparturesByAirport(Long airportId) {
        return flightRepository.findByDepartureAirportId(airportId);
    }

    public List<Flight> getArrivalsByAirport(Long airportId) {
        return flightRepository.findByArrivalAirportId(airportId);
    }

    public List<Flight> getFlightsByAirline(Long airlineId) {
        return flightRepository.findByAirlineId(airlineId);
    }

    public List<Flight> getFlightsByStatus(String status) {
        return flightRepository.findByStatus(status);
    }

    public List<Flight> getDeparturesByAirportAndStatus(Long airportId, String status) {
        return flightRepository.findByDepartureAirportIdAndStatus(airportId, status);
    }

    public List<Flight> getArrivalsByAirportAndStatus(Long airportId, String status) {
        return flightRepository.findByArrivalAirportIdAndStatus(airportId, status);
    }
}
