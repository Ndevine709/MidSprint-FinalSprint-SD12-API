package com.airportAPI.rest.gates;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.airport.AirportRepository;

@Service
public class GatesService {
    
    @Autowired
    private GatesRepository gatesRepository;

    @Autowired
    private AirportRepository airportRepository;

    public List<Gates> getAllGates() {
        List<Gates> gates = new ArrayList<>();
        gatesRepository.findAll().forEach(gates::add);
        return gates;
    }

    public Gates getGatesById(Long id) {
        return gatesRepository.findById(id).orElse(null);
    }

    public Gates createGates(Gates gates) {
        Long airportId = gates.getAirport().getId();
        Airport airport = airportRepository.findById(airportId).orElseThrow(() -> new RuntimeException("Airport not found with ID: " + airportId));
        gates.setAirport(airport);
        return gatesRepository.save(gates);
    }

    public Gates updateGates(Long id, Gates updatedGates) {
        return gatesRepository.findById(id).map(g -> {
            g.setGateNumber(updatedGates.getGateNumber());
            g.setTerminal(updatedGates.getTerminal());
            g.setAirport(updatedGates.getAirport());
            return gatesRepository.save(g);
        }).orElse(null);
    }

    public void deleteGateById(Long id) {
        gatesRepository.deleteById(id);
    }
}
