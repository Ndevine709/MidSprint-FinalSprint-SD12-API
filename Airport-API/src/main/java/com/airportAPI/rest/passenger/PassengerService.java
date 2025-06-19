package com.airportAPI.rest.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.aircraft.AircraftRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    public List<Passenger> getAllPassengers() {
        return (List<Passenger>) passengerRepository.findAll();
    }

    public Passenger getPassengerById(long id) {
        Optional<Passenger> opt = passengerRepository.findById(id);
        return opt.orElse(null);
    }

    public Passenger getPassengerByLastName(String lastName) {
        return passengerRepository.findByLastName(lastName);
    }

    public Passenger createPassenger(Passenger newPassenger) {
        return passengerRepository.save(newPassenger);
    }

    public Passenger updatePassenger(long id, Passenger updatedPassenger) {
        Optional<Passenger> opt = passengerRepository.findById(id);
        if (opt.isPresent()) {
            Passenger p = opt.get();

            p.setFirstName(updatedPassenger.getFirstName());
            p.setLastName(updatedPassenger.getLastName());
            p.setBirthday(updatedPassenger.getBirthday());

            p.setPhoneNumber(updatedPassenger.getPhoneNumber());

            p.setFlights(updatedPassenger.getFlights());

            return passengerRepository.save(p);
        }
        return null;
    }

    public void deletePassengerById(long id) {
        passengerRepository.deleteById(id);
    }

    public Passenger assignAircraft(long passengerId, long aircraftId) {
        Passenger passenger = passengerRepository.findById(passengerId).orElse(null);
        Aircraft aircraft = aircraftRepository.findById(aircraftId).orElse(null);

        if (passenger != null && aircraft != null) {
            passenger.getFlights().add(aircraft);
            return passengerRepository.save(passenger);
        }
        return null;
    }
}
