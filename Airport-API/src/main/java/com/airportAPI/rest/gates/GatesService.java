package com.airportAPI.rest.gates;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.airport.AirportRepository;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.aircraft.AircraftRepository;

@Service
public class GatesService {
    
    @Autowired
    private GatesRepository gatesRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

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
            g.setDepartureGate(updatedGates.isDepartureGate()); // Add this line
            return gatesRepository.save(g);
        }).orElse(null);
    }

    public List<Gates> getGatesByAirport(Long airportId) {
        return gatesRepository.findByAirportId(airportId);
    }

    public List<Gates> getDepartureGates(Long airportId) {
        return gatesRepository.findByAirportIdAndIsDepartureGate(airportId, true);
    }

    public List<Gates> getArrivalGates(Long airportId) {
        return gatesRepository.findByAirportIdAndIsDepartureGate(airportId, false);
    }

    public void deleteGateById(Long id) {
        gatesRepository.deleteById(id);
    }

    public Gates assignAircraftToGate(Long gateId, Long aircraftId) {
        Gates gate = gatesRepository.findById(gateId)
            .orElseThrow(() -> new RuntimeException("Gate not found"));
        
        Aircraft aircraft = aircraftRepository.findById(aircraftId)
            .orElseThrow(() -> new RuntimeException("Aircraft not found"));
        
        gate.setAircraft(aircraft);
        return gatesRepository.save(gate);
    }

    public Gates removeAircraftFromGate(Long gateId) {
        Gates gate = gatesRepository.findById(gateId)
            .orElseThrow(() -> new RuntimeException("Gate not found"));
        
        gate.setAircraft(null);
        return gatesRepository.save(gate);
    }

    public List<Gates> getGatesWithAircraftByAirport(Long airportId, boolean isDeparture) {
        List<Gates> gates = gatesRepository.findByAirportIdAndIsDepartureGate(airportId, isDeparture);
        return gates;
    }
}
