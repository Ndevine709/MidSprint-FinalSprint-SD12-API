package services;

import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.airport.AirportRepository;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.aircraft.AircraftRepository;
import com.airportAPI.rest.aircraft.AircraftService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AircraftServiceTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AircraftService aircraftService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getAllAircraft returns list")
    public void testGetAllAircraft() {
        List<Aircraft> stub = Arrays.asList(
                new Aircraft("C-FABC", "B737-800", 180),
                new Aircraft("C-GXYZ", "A320-200", 150));
        when(aircraftRepository.findAll()).thenReturn(stub);

        List<Aircraft> result = aircraftService.getAllAircraft();

        assertEquals(stub, result);
        verify(aircraftRepository).findAll();
    }

    @Test
    public void testGetAircraftByIdFound() {
        Aircraft ac = new Aircraft("C-FABC", "B737-800", 180);
        when(aircraftRepository.findById(1L)).thenReturn(Optional.of(ac));

        Aircraft result = aircraftService.getAircraftById(1L);

        assertEquals(ac, result);
        verify(aircraftRepository).findById(1L);
    }

    @Test
    public void testGetAircraftByIdNotFound() {
        when(aircraftRepository.findById(99L)).thenReturn(Optional.empty());

        Aircraft result = aircraftService.getAircraftById(99L);

        assertNull(result);
        verify(aircraftRepository).findById(99L);
    }

    @Test
    public void testCreateAircraft() {
        Aircraft ac = new Aircraft("C-FABC", "B737-800", 180);
        when(aircraftRepository.save(ac)).thenReturn(ac);

        Aircraft result = aircraftService.createAircraft(ac);

        assertEquals(ac, result);
        verify(aircraftRepository).save(ac);
    }

    @Test
    public void testUpdateAircraft() {
        Long id = 1L;
        Aircraft existing = new Aircraft("OLD", "OLD", 100);
        Aircraft update = new Aircraft("C-FABC", "B737-800", 180);

        when(aircraftRepository.findById(id)).thenReturn(Optional.of(existing));
        when(aircraftRepository.save(any(Aircraft.class))).thenAnswer(i -> i.getArgument(0));

        Aircraft result = aircraftService.updateAircraft(id, update);

        assertNotNull(result);
        assertEquals("C-FABC", result.getTailNumber());
        assertEquals("B737-800", result.getModel());
        assertEquals(180, result.getCapacity());
        verify(aircraftRepository).findById(id);
        verify(aircraftRepository).save(existing);
    }

    @Test
    public void testDeleteAircraftById() {
        aircraftService.deleteAircraftById(1L);
        verify(aircraftRepository).deleteById(1L);
    }

    @Test
    public void testAddAirportToAircraft() {
        Long aircraftId = 1L;
        Long airportId = 10L;

        Aircraft ac = new Aircraft("C-FABC", "B737-800", 180);
        Airport ap = new Airport("Test Airport", "TST", null);

        when(aircraftRepository.findById(aircraftId)).thenReturn(Optional.of(ac));
        when(airportRepository.findById(airportId)).thenReturn(Optional.of(ap));
        when(aircraftRepository.save(any(Aircraft.class))).thenAnswer(i -> i.getArgument(0));

        Aircraft result = aircraftService.addAirportToAircraft(aircraftId, airportId);

        assertTrue(result.getAirports().contains(ap));
        verify(aircraftRepository).findById(aircraftId);
        verify(airportRepository).findById(airportId);
        verify(aircraftRepository).save(ac);
    }

}
