package com.airportAPI.rest.airport;

import java.util.ArrayList;
import java.util.List;


public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports(){
        List<Airport> airports = new ArrayList<>();
        airportRepository.findAll().forEach(airports::add);
        return airports;
    }

    public Airport createAirport(Airport airport){
        return airportRepository.save(airport);
    }

    public Airport updateAirport(Long id, Airport airportDetails){
        return airportRepository.findById(id).map(airport -> {
            airport.getName();
            airport.getCode();
            airport.getCity();
            return airportRepository.save(airport);
        }).orElseThrow(()-> new RuntimeException("Airport not found"));
    }

    public void deleteAirport(Long id){
        airportRepository.deleteById(id);
    }
}

