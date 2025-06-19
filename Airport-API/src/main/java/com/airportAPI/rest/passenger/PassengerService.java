package com.airportAPI.rest.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.aircraft.AircraftRepository;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassengerByLastName(String lastName) {
        return passengerRepository.findByLastName(lastName);
    }

    public Passenger getPassengerById(long id) {
        return passengerRepository.findById(id).orElse(null);
    }

    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public Passenger updatePassenger(long id, Passenger passenger) {
        Passenger existing = passengerRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setFirstName(passenger.getFirstName());
            existing.setLastName(passenger.getLastName());
            existing.setBirthday(passenger.getBirthday());
            existing.setPhoneNumber(passenger.getPhoneNumber());
            return passengerRepository.save(existing);
        }
        return null;
    }

    public void deletePassengerById(long id) {
        passengerRepository.deleteById(id);
    }

    public Passenger assignAircraft(long passengerId, long aircraftId) {
        Passenger passenger = passengerRepository.findById(passengerId).orElse(null);
        Aircraft  aircraft  = aircraftRepository.findById(aircraftId).orElse(null);

        if (passenger != null && aircraft != null) {
            passenger.getFlights().add(aircraft);
            return passengerRepository.save(passenger);
        }
        return null;
    }
}
