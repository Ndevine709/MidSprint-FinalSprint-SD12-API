package com.airportAPI.rest.airline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AirlineController {
    
    @Autowired
    private AirlineService airlineService;

    @GetMapping("/airlines")
    public List<Airline> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @GetMapping("/airline/{id}")
    public Airline getAirlineById(@PathVariable long id) {
        return airlineService.getAirlineById(id);
    }

    @GetMapping("/airline_search")
    public Airline getAirlineByCode(@RequestParam("code") String code) {
        return airlineService.getAirlineByCode(code);
    }

    @PostMapping("/airline")
    public Airline creatAirline(@RequestBody Airline airline) {
        return airlineService.createAirline(airline);
    }

    @PutMapping("/airline/{id}")
    public ResponseEntity<Airline> updateAirline(@PathVariable long id, @RequestBody Airline airline) {
        Airline updated = airlineService.updateAirline(id, airline);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/airline/{id}")
    public void deleteAirline(@PathVariable long id) {
        airlineService.deleteAirlineById(id);
    }
}
