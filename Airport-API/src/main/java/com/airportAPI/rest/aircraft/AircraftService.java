package com.airportAPI.rest.aircraft;

import java.util.List;
import java.util.Optional;      
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AircraftService {

    @Autowired
    private AircraftRepository aircraftRepository;

    public List<Aircraft> getAllAircraft() {
        return (List<Aircraft>) aircraftRepository.findAll();
    }

    public Aircraft getAircraftById(Long id) {
        Optional<Aircraft> opt = aircraftRepository.findById(id);
        return opt.orElse(null);
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
}
