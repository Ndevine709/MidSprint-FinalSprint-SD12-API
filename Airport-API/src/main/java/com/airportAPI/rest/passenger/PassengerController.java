package com.airportAPI.rest.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/passengers")
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/passenger_search")
    public Passenger getPassengerByLastName(@RequestParam("last_name") String lastName) {
        return passengerService.getPassengerByLastName(lastName);
    }

    @GetMapping("/passenger/{id}")
    public Passenger getPassengerById(@PathVariable long id) {
        return passengerService.getPassengerById(id);
    }

    @PostMapping("/passenger")
    public Passenger createPassenger(@RequestBody Passenger passenger) {
        return passengerService.createPassenger(passenger);
    }

    @PutMapping("/passenger/{id}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable long id, @RequestBody Passenger passenger) {
        Passenger updated = passengerService.updatePassenger(id, passenger);
        return (updated != null)
            ? ResponseEntity.ok(updated)
            : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/passenger/{id}")
    public void deletePassenger(@PathVariable long id) {
        passengerService.deletePassengerById(id);
    }
}
