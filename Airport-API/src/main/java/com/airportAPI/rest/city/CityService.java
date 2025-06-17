package com.airportAPI.rest.city;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        cityRepository.findAll().forEach(cities::add);
        return cities;
    }

    public City getCityById(long id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        return cityOptional.orElse(null);
    }

    public City findByName(String name) {
        return cityRepository.findByName(name);
    }

    public City findByState(String state) {
        return cityRepository.findByState(state);
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(long id, City updatedCity) {
        Optional<City> cityToUpdateOptional = cityRepository.findById(id);

        if (cityToUpdateOptional.isPresent()) {
            City cityToUpdate = cityToUpdateOptional.get();
            cityToUpdate.setName(updatedCity.getName());
            cityToUpdate.setState(updatedCity.getState());
            cityToUpdate.setPopulation(updatedCity.getPopulation());
            return cityRepository.save(cityToUpdate);
        }
        return null;
    }

    public void deleteCityById(long id) {
        cityRepository.deleteById(id);
    }
}
