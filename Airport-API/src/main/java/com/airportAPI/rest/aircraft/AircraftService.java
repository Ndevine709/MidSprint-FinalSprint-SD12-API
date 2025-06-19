package com.airportAPI.rest.aircraft;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.airport.AirportRepository;

@Service
public class AircraftService {

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private AirportRepository airportRepository;

    public List<Aircraft> getAllAircraft() {
        return (List<Aircraft>) aircraftRepository.findAll();
    }

    public Aircraft getAircraftById(Long id) {
        return aircraftRepository.findById(id).orElse(null);
    }

    public Aircraft createAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public Aircraft updateAircraft(Long id, Aircraft updatedAircraft) {
        Optional<Aircraft> opt = aircraftRepository.findById(id);
        if (opt.isPresent()) {
            Aircraft a = opt.get();
            a.setTailNumber(updatedAircraft.getTailNumber());
            a.setModel(updatedAircraft.getModel());
            a.setCapacity(updatedAircraft.getCapacity());
            a.setAirports(updatedAircraft.getAirports());
            a.setPassengers(updatedAircraft.getPassengers());
            return aircraftRepository.save(a);
        }
        return null;
    }

    public void deleteAircraftById(Long id) {
        aircraftRepository.deleteById(id);
    }

    public Aircraft addAirportToAircraft(Long aircraftId, Long airportId) {
        Aircraft aircraft = aircraftRepository.findById(aircraftId)
                .orElseThrow(() -> new EntityNotFoundException("Aircraft not found: " + aircraftId));
        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(() -> new EntityNotFoundException("Airport not found: " + airportId));

        aircraft.addAirport(airport);

        return aircraftRepository.save(aircraft);
    }
}
