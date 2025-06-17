package com.airportAPI.rest.aircraft;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends CrudRepository<Aircraft, Long> {
    Aircraft findByTailNumber(String tailNumber);
}
