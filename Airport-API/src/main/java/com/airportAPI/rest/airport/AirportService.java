package com.airportAPI.rest.airport;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.airportAPI.rest.city.City;
import com.airportAPI.rest.city.CityRepository;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class AirportService {
    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    public AirportService(AirportRepository airportRepository, CityRepository cityRepository){
        this.airportRepository = airportRepository;
        this.cityRepository = cityRepository;
    }

    public List<Airport> getAllAirports(){
        List<Airport> airports = new ArrayList<>();
        airportRepository.findAll().forEach(airports::add);
        return airports;
    }

    public List<Airport> getAirportsByCity(@PathVariable Long cityId){
        return airportRepository.findByCityId(cityId);
    }

    public Airport createAirport(Airport airport){
        Long cityId = airport.getCity().getId();
        City fullCity = cityRepository.findById(cityId).orElseThrow(()-> new RuntimeException("City not found with ID: " + cityId));
        airport.setCity(fullCity);
        return airportRepository.save(airport);
    }

    public Airport updateAirport(Long id, Airport airportDetails){
        return airportRepository.findById(id).map(airport -> {
            airport.setName(airportDetails.getName());
            airport.setCode(airportDetails.getCode());

            Long cityId = airportDetails.getCity().getId();
            City fullCity = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City not found with ID: " + cityId));
            airport.setCity(fullCity);

            return airportRepository.save(airport);
        }).orElseThrow(() -> new RuntimeException("Airport not found"));
    }

    public void deleteAirport(Long id){
        airportRepository.deleteById(id);
    }
}
