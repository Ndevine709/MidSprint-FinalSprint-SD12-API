package com.airportAPI.rest.airport;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AirportRepository extends CrudRepository<Airport, Long> {
    List<Airport> findByCityId(Long cityId);
}