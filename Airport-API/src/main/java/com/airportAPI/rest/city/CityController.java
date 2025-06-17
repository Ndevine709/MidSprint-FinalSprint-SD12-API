package com.airportAPI.rest.city;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/city")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/city/{id}")
    public City getCityById(@PathVariable long id) {
        return cityService.getCityById(id);
    }

    @GetMapping("/city_search")
    
    public List<City> searchCities(@RequestParam(value = "name", required = false) String name,
    @RequestParam(value = "state", required = false) String state) {
        List<City> results = new ArrayList<>();
        if (name != null) {
            City city = cityService.findByName(name);
         if (city != null) results.add(city);
        } else if (state != null) {
            City city = cityService.findByState(state);
            if (city != null) results.add(city);
        }
        return results;
    }

    @PostMapping("/city")
    public City createCity(@RequestBody City city) {
        return cityService.createCity(city);
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<City> updateCity(@PathVariable long id, @RequestBody City city) {
        return ResponseEntity.ok(cityService.updateCity(id, city));
    }

    @DeleteMapping("/city/{id}")
    public void deleteCityById(@PathVariable long id) {
        cityService.deleteCityById(id);
    }

}
