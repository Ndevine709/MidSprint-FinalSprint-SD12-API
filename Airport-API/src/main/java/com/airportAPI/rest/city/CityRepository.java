package com.airportAPI.rest.city;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    City findByName(String name);
    City findByState(String state);
}
