package com.airportAPI.rest.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    List<Airport> findByCityId(Long cityId);
}
