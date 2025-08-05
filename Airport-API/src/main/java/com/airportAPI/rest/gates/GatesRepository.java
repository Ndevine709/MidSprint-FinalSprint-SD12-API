package com.airportAPI.rest.gates;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GatesRepository extends CrudRepository<Gates, Long> {
    List<Gates> findByAirportId(Long airportId);
    List<Gates> findByAirportIdAndIsDepartureGate(Long airportId, boolean isDepartureGate);
} 