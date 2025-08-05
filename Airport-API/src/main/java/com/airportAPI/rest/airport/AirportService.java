package com.airportAPI.rest.airport;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.airportAPI.rest.city.City;
import com.airportAPI.rest.city.CityRepository;
import org.springframework.web.bind.annotation.PathVariable;
import com.airportAPI.rest.gates.Gates;
import com.airportAPI.rest.gates.GatesRepository;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.aircraft.AircraftRepository;

@Service
public class AirportService {
    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;
    private final GatesRepository gatesRepository;
    private final AircraftRepository aircraftRepository;

    public AirportService(AirportRepository airportRepository, CityRepository cityRepository, GatesRepository gatesRepository, AircraftRepository aircraftRepository){
        this.airportRepository = airportRepository;
        this.cityRepository = cityRepository;
        this.gatesRepository = gatesRepository;
        this.aircraftRepository = aircraftRepository;
    }

    public List<Airport> getAllAirports(){
        List<Airport> airports = new ArrayList<>();
        airportRepository.findAll().forEach(airports::add);
        return airports;
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Airport not found with ID: " + id));
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

    public List<Gates> getGatesByAirport(Long airportId) {
        return gatesRepository.findByAirportId(airportId);
    }

    public Gates addGateToAirport(Long airportId, Gates gate) {
        Airport airport = airportRepository.findById(airportId).orElseThrow(() -> new RuntimeException("Airport not found with ID: " + airportId));
        gate.setAirport(airport);
        return gatesRepository.save(gate);
    }

    public List<Aircraft> getAircraftByAirport(Long airportId) {
        Airport airport = airportRepository.findById(airportId)
            .orElseThrow(() -> new RuntimeException("Airport not found with ID: " + airportId));
        return new ArrayList<>(airport.getAircraft());
    }

    public Aircraft getAircraftById(Long aircraftId) {
        return aircraftRepository.findById(aircraftId)
            .orElseThrow(() -> new RuntimeException("Aircraft not found with ID: " + aircraftId));
    }
}
