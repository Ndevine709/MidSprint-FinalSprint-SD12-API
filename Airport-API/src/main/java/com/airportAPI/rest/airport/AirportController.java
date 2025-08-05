package com.airportAPI.rest.airport;

import com.airportAPI.rest.gates.Gates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/airport")
    public List<Airport> getAllAirports(){
        return airportService.getAllAirports();
    }

    @GetMapping("/airport/city/{cityId}")
    public List<Airport> getAirportsByCity(@PathVariable Long cityId) {
        return airportService.getAirportsByCity(cityId);
    }

    @PostMapping("/airport")
    public Airport createAirport(@RequestBody Airport airport){
        return airportService.createAirport(airport);
    }

    @PutMapping("/airport/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport airport){
        try{
            Airport updatedAirport = airportService.updateAirport(id, airport);
            return ResponseEntity.ok(updatedAirport);
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/airport/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id){
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }

    // Add these new endpoints
    @GetMapping("/airport/{airportId}/gates")
    public List<Gates> getGatesByAirport(@PathVariable Long airportId) {
        return airportService.getGatesByAirport(airportId);
    }

    @PostMapping("/airport/{airportId}/gates")
    public Gates addGateToAirport(@PathVariable Long airportId, @RequestBody Gates gate) {
        return airportService.addGateToAirport(airportId, gate);
    }

}
