package com.airportAPI.rest.airport;

import java.util.List;


public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports(){
        return airportRepository.findAll();
    }

    public Airport createAirport(Airport airport){
        return airportRepository.save(airport);
    }

    public Airport updateAirport(Long id, Airport airportDetails){
        return airportRepository.findById(id).map(airport -> {
            airport.setName("Toronto Pearson Airport");
            airport.setCode("YYZ");
            // airport.setCity(null);
            return airportRepository.save(airport);
        }).orElseThrow(()-> new RuntimeException("Airport not found"));
    }

    public void deleteAirport(Long id){
        airportRepository.deleteById(id);
    }
}
