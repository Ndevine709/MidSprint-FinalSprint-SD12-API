package services;

import com.airportAPI.rest.city.City;
import com.airportAPI.rest.city.CityRepository;
import com.airportAPI.rest.city.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCities() {
        List<City> cities = Arrays.asList(
                new City("Toronto", "Ontario", 3000000),
                new City("Vancouver", "British Columbia", 2500000)
        );
        when(cityRepository.findAll()).thenReturn(cities);

        List<City> result = cityService.getAllCities();

        assertEquals(cities, result);
        verify(cityRepository).findAll();
    }

    @Test
    public void testGetCityByIdFound() {
        City city = new City("Toronto", "Ontario", 3000000);
        city.setId(1L);
        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));

        City result = cityService.getCityById(1L);

        assertEquals(city, result);
        verify(cityRepository).findById(1L);
    }

    @Test
    public void testGetCityByIdNotFound() {
        when(cityRepository.findById(99L)).thenReturn(Optional.empty());

        City result = cityService.getCityById(99L);

        assertNull(result);
        verify(cityRepository).findById(99L);
    }

    @Test
    public void testFindByName() {
        City city = new City("Toronto", "Ontario", 3000000);
        when(cityRepository.findByName("Toronto")).thenReturn(city);

        City result = cityService.findByName("Toronto");

        assertEquals(city, result);
        verify(cityRepository).findByName("Toronto");
    }

    @Test
    public void testFindByState() {
        City city = new City("Toronto", "Ontario", 3000000);
        when(cityRepository.findByState("Ontario")).thenReturn(city);

        City result = cityService.findByState("Ontario");

        assertEquals(city, result);
        verify(cityRepository).findByState("Ontario");
    }

    @Test
    public void testCreateCity() {
        City city = new City("Toronto", "Ontario", 3000000);
        when(cityRepository.save(any(City.class))).thenReturn(city);

        City result = cityService.createCity(city);

        assertEquals(city, result);
        verify(cityRepository).save(city);
    }

    @Test
    public void testUpdateCityFound() {
        City existingCity = new City("Toronto", "Ontario", 3000000);
        existingCity.setId(1L);
        City updatedCity = new City("Toronto", "Ontario", 3500000);
        
        when(cityRepository.findById(1L)).thenReturn(Optional.of(existingCity));
        when(cityRepository.save(any(City.class))).thenReturn(existingCity);

        City result = cityService.updateCity(1L, updatedCity);

        assertNotNull(result);
        assertEquals(updatedCity.getPopulation(), result.getPopulation());
        verify(cityRepository).findById(1L);
        verify(cityRepository).save(any(City.class));
    }

    @Test
    public void testUpdateCityNotFound() {
        City updatedCity = new City("Toronto", "Ontario", 3500000);
        when(cityRepository.findById(99L)).thenReturn(Optional.empty());

        City result = cityService.updateCity(99L, updatedCity);

        assertNull(result);
        verify(cityRepository).findById(99L);
        verify(cityRepository, never()).save(any(City.class));
    }

    @Test
    public void testDeleteCityById() {
        cityService.deleteCityById(1L);
        verify(cityRepository).deleteById(1L);
    }
}
