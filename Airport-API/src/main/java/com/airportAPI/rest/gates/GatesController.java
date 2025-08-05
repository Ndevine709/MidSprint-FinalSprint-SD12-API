package com.airportAPI.rest.gates;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GatesController {
    
    @Autowired
    private GatesService gatesService;

    @GetMapping("/gates")
    public List<Gates> getAllGates() {
        return gatesService.getAllGates();
    }

    @GetMapping("/gates/{id}")
    public Gates getGatesById(@PathVariable Long id) {
        return gatesService.getGatesById(id);
    }

    @PostMapping("/gates")
    public Gates createGates(@RequestBody Gates gates) {
        return gatesService.createGates(gates);
    }

    @PutMapping("/gates/{id}")
    public ResponseEntity<Gates> updateGates(@PathVariable long id, @RequestBody Gates gates) {
        Gates updated = gatesService.updateGates(id, gates);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/gates/{id}")
    public void deleteGates(@PathVariable long id) {
        gatesService.deleteGateById(id);
    }

    @GetMapping("/gates/airport/{airportId}/departures")
    public List<Gates> getDepartureGates(@PathVariable Long airportId) {
        return gatesService.getDepartureGates(airportId);
    }

    @GetMapping("/gates/airport/{airportId}/arrivals")
    public List<Gates> getArrivalGates(@PathVariable Long airportId) {
        return gatesService.getArrivalGates(airportId);
    }

    @PostMapping("/gates/{gateId}/aircraft/{aircraftId}")
    public Gates assignAircraftToGate(@PathVariable Long gateId, @PathVariable Long aircraftId) {
        return gatesService.assignAircraftToGate(gateId, aircraftId);
    }

    @DeleteMapping("/gates/{gateId}/aircraft")
    public Gates removeAircraftFromGate(@PathVariable Long gateId) {
        return gatesService.removeAircraftFromGate(gateId);
    }

    @GetMapping("/gates/airport/{airportId}/departures/with-aircraft")
    public List<Gates> getDepartureGatesWithAircraft(@PathVariable Long airportId) {
        return gatesService.getGatesWithAircraftByAirport(airportId, true);
    }

    @GetMapping("/gates/airport/{airportId}/arrivals/with-aircraft")
    public List<Gates> getArrivalGatesWithAircraft(@PathVariable Long airportId) {
        return gatesService.getGatesWithAircraftByAirport(airportId, false);
    }
}
