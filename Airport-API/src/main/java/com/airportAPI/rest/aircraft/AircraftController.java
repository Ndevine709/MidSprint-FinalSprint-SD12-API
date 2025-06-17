package com.airportAPI.rest.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AircraftController {

    @Autowired
    private AircraftService aircraftService;

    @GetMapping("/aircrafts")
    public List<Aircraft> getAllAircraft() {
        return aircraftService.getAllAircraft();
    }

    @GetMapping("/aircraft/{id}")
    public Aircraft getAircraftById(@PathVariable long id) {
        return aircraftService.getAircraftById(id);
    }

    @PostMapping("/aircraft")
    public Aircraft createAircraft(@RequestBody Aircraft aircraft) {
        return aircraftService.createAircraft(aircraft);
    }

    @PutMapping("/aircraft/{id}")
    public ResponseEntity<Aircraft> updateAircraft(
            @PathVariable long id,
            @RequestBody Aircraft aircraft
    ) {
        Aircraft updated = aircraftService.updateAircraft(id, aircraft);
        return (updated != null)
            ? ResponseEntity.ok(updated)
            : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/aircraft/{id}")
    public void deleteAircraft(@PathVariable long id) {
        aircraftService.deleteAircraftById(id);
    }
}
