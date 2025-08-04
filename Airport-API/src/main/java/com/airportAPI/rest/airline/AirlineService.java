package com.airportAPI.rest.airline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineService {
    
    @Autowired
    private AirlineRepository airlineRepository;

    public List<Airline> getAllAirlines() {
        return (List<Airline>) airlineRepository.findAll();
    }

    public Airline getAirlineById(Long id) {
        return airlineRepository.findById(id).orElse(null);
    }

    public Airline getAirlineByCode(String code) {
        return airlineRepository.findByCode(code);
    }

    public Airline createAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    public Airline updateAirline(Long id, Airline updatedAirline) {
        return airlineRepository.findById(id).map(a -> {
            a.setName(updatedAirline.getName());
            a.setCode(updatedAirline.getCode());
            return airlineRepository.save(a);
        }).orElse(null);
    }

    public void deleteAirlineById(Long id) {
        airlineRepository.deleteById(id);
    }
}
