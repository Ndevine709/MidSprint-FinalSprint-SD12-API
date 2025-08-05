package com.airportAPI.rest.flights;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
    List<Flight> findByDepartureAirportId(Long airportId);
    List<Flight> findByArrivalAirportId(Long airportId);
    List<Flight> findByAirlineId(Long airlineId);
    List<Flight> findByAircraftId(Long aircraftId);
    List<Flight> findByStatus(String status);
    List<Flight> findByDepartureAirportIdAndStatus(Long airportId, String status);
    List<Flight> findByArrivalAirportIdAndStatus(Long airportId, String status);
    List<Flight> findByDepartureGateId(Long gateId);
    List<Flight> findByArrivalGateId(Long gateId);
}
