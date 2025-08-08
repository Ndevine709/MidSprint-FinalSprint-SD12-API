package services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.airportAPI.rest.airline.Airline;
import com.airportAPI.rest.airline.AirlineRepository;
import com.airportAPI.rest.airline.AirlineService;

public class AirlineServiceTest {

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private AirlineService airlineService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getAllAirlines returns repository list")
    public void testGetAllAirlines() {
        List<Airline> stub = Arrays.asList(
                new Airline("Air Canada", "AC"),
                new Airline("WestJet", "WS")
        );
        when(airlineRepository.findAll()).thenReturn(stub);

        List<Airline> result = airlineService.getAllAirlines();

        assertEquals(stub, result);
        verify(airlineRepository).findAll();
    }

    @Test
    public void testGetAirlineByIdFound() {
        Airline ac = new Airline("Air Canada", "AC");
        ac.setId(1L);
        when(airlineRepository.findById(1L)).thenReturn(Optional.of(ac));

        Airline result = airlineService.getAirlineById(1L);

        assertEquals(ac, result);
        verify(airlineRepository).findById(1L);
    }

    @Test
    public void testGetAirlineByIdNotFound() {
        when(airlineRepository.findById(99L)).thenReturn(Optional.empty());

        Airline result = airlineService.getAirlineById(99L);

        assertNull(result);
        verify(airlineRepository).findById(99L);
    }

    @Test
    public void testCreateAirline() {
        Airline ac = new Airline("Air Canada", "AC");
        when(airlineRepository.save(ac)).thenReturn(ac);

        Airline result = airlineService.createAirline(ac);

        assertEquals(ac, result);
        verify(airlineRepository).save(ac);
    }

    @Test
    public void testUpdateAirline() {
        Long id = 1L;
        Airline existing = new Airline("Far", "FR");
        existing.setId(id);
        Airline update = new Airline("Air Canada", "AC");

        when(airlineRepository.findById(id)).thenReturn(Optional.of(existing));
        when(airlineRepository.save(any(Airline.class))).thenAnswer(i -> i.getArgument(0));

        Airline result = airlineService.updateAirline(id, update);

        assertNotNull(result);
        assertEquals("Air Canada", result.getName());
        assertEquals("AC", result.getCode());
        verify(airlineRepository).findById(id);
        verify(airlineRepository).save(existing);
    }

    @Test
    public void testDeleteAirlineById() {
        airlineService.deleteAirlineById(1L);
        verify(airlineRepository).deleteById(1L);
    }
}
