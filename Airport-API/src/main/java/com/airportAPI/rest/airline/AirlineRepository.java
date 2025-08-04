package com.airportAPI.rest.airline;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends CrudRepository<Airline, Long> {
   Airline findByCode(String code);
}
