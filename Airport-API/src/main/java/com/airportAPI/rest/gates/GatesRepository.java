package com.airportAPI.rest.gates;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatesRepository extends CrudRepository<Gates, Long> {

} 