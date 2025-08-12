package services;

import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.airport.AirportRepository;
import com.airportAPI.rest.airport.AirportService;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.aircraft.AircraftRepository;
import com.airportAPI.rest.city.City;
import com.airportAPI.rest.city.CityRepository;
import com.airportAPI.rest.flights.Flight;
import com.airportAPI.rest.gates.Gates;
import com.airportAPI.rest.gates.GatesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AirportServiceTest {

    @Mock private AirportRepository airportRepository;
    @Mock private CityRepository cityRepository;
    @Mock private GatesRepository gatesRepository;
    @Mock private AircraftRepository aircraftRepository;

    @InjectMocks
    private AirportService airportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private City cityWithId(long id) {
        City c = new City("Name", "State", 123);
        c.setId(id);
        return c;
    }

    @Test
    @DisplayName("getAllAirports returns list")
    public void testGetAllAirports() {
        List<Airport> stub = Arrays.asList(
                new Airport("Toronto Pearson", "YYZ", null),
                new Airport("Vancouver Intl", "YVR", null)
        );
        when(airportRepository.findAll()).thenReturn(stub);

        List<Airport> result = airportService.getAllAirports();

        assertEquals(stub, result);
        verify(airportRepository).findAll();
    }

    @Test
    public void testGetAirportByIdFound() {
        Airport ap = new Airport("Toronto Pearson", "YYZ", null);
        when(airportRepository.findById(1L)).thenReturn(Optional.of(ap));

        Airport result = airportService.getAirportById(1L);

        assertEquals(ap, result);
        verify(airportRepository).findById(1L);
    }

    @Test
    public void testGetAirportByIdNotFound() {
        when(airportRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> airportService.getAirportById(2L));
        assertTrue(ex.getMessage().contains("Airport not found"));
        verify(airportRepository).findById(2L);
    }

    @Test
    public void testCreateAirport_success() {
        City ref = cityWithId(7L);
        City full = cityWithId(7L);
        Airport incoming = new Airport("Moncton", "YQM", ref);

        when(cityRepository.findById(7L)).thenReturn(Optional.of(full));
        when(airportRepository.save(incoming)).thenReturn(incoming);

        Airport saved = airportService.createAirport(incoming);

        assertEquals(full, saved.getCity());
        verify(cityRepository).findById(7L);
        verify(airportRepository).save(incoming);
    }

    @Test
    public void testCreateAirport_cityMissing() {
        City refMissing = cityWithId(8L);
        Airport bad = new Airport("X", "XXX", refMissing);
        when(cityRepository.findById(8L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> airportService.createAirport(bad));
        assertTrue(ex.getMessage().contains("City not found"));
        verify(cityRepository).findById(8L);
        verify(airportRepository, never()).save(any());
    }

    @Test
    public void testUpdateAirport_success() {
        Long id = 1L;
        Airport existing = new Airport("Old", "OLD", cityWithId(2L));

        City newRef = cityWithId(9L);
        City newFull = cityWithId(9L);
        Airport details = new Airport("New", "NEW", newRef);

        when(airportRepository.findById(id)).thenReturn(Optional.of(existing));
        when(cityRepository.findById(9L)).thenReturn(Optional.of(newFull));
        when(airportRepository.save(any(Airport.class))).thenAnswer(i -> i.getArgument(0));

        Airport updated = airportService.updateAirport(id, details);

        assertEquals("New", updated.getName());
        assertEquals("NEW", updated.getCode());
        assertEquals(newFull, updated.getCity());
        verify(airportRepository).findById(id);
        verify(cityRepository).findById(9L);
        verify(airportRepository).save(existing);
    }

    @Test
    public void testUpdateAirport_notFound() {
        when(airportRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> airportService.updateAirport(99L, new Airport("N", "C", cityWithId(1L))));
        assertTrue(ex.getMessage().contains("Airport not found"));
        verify(airportRepository).findById(99L);
        verify(airportRepository, never()).save(any());
    }

    @Test
    public void testUpdateAirport_cityMissing() {
        Long id = 1L;
        Airport existing = new Airport("A", "AAA", cityWithId(2L));
        Airport details = new Airport("B", "BBB", cityWithId(55L));

        when(airportRepository.findById(id)).thenReturn(Optional.of(existing));
        when(cityRepository.findById(55L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> airportService.updateAirport(id, details));
        assertTrue(ex.getMessage().contains("City not found"));
        verify(airportRepository).findById(id);
        verify(cityRepository).findById(55L);
        verify(airportRepository, never()).save(any());
    }

    @Test
    public void testDeleteAirport() {
        airportService.deleteAirport(3L);
        verify(airportRepository).deleteById(3L);
    }

    @Test
    public void testAddGateToAirport() {
        Long airportId = 10L;
        Airport ap = new Airport("Toronto Pearson", "YYZ", null);
        Gates g = new Gates();

        when(airportRepository.findById(airportId)).thenReturn(Optional.of(ap));
        when(gatesRepository.save(any(Gates.class))).thenAnswer(i -> i.getArgument(0));

        Gates saved = airportService.addGateToAirport(airportId, g);

        assertEquals(ap, saved.getAirport());
        verify(airportRepository).findById(airportId);
        verify(gatesRepository).save(g);
    }

    @Test
    public void testGetAircraftByAirport_success() {
        Long airportId = 1L;
        Airport ap = new Airport("Halifax", "YHZ", null);
        Aircraft a1 = new Aircraft("C-FABC", "B737-800", 180);
        Aircraft a2 = new Aircraft("C-GXYZ", "A320", 160);
        ap.addAircraft(a1);
        ap.addAircraft(a2);
        when(airportRepository.findById(airportId)).thenReturn(Optional.of(ap));

        List<Aircraft> result = airportService.getAircraftByAirport(airportId);

        assertEquals(2, result.size());
        assertTrue(result.contains(a1));
        assertTrue(result.contains(a2));
        verify(airportRepository).findById(airportId);
    }

    @Test
    public void testGetAircraftByAirport_missing() {
        when(airportRepository.findById(77L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> airportService.getAircraftByAirport(77L));
        assertTrue(ex.getMessage().contains("Airport not found"));
        verify(airportRepository).findById(77L);
    }

    @Test
    public void testGetAircraftById_success() {
        Aircraft ac = new Aircraft("C-FABC", "B737-800", 180);
        when(aircraftRepository.findById(5L)).thenReturn(Optional.of(ac));

        Aircraft result = airportService.getAircraftById(5L);

        assertEquals(ac, result);
        verify(aircraftRepository).findById(5L);
    }

    @Test
    public void testGetAircraftById_missing() {
        when(aircraftRepository.findById(404L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> airportService.getAircraftById(404L));
        assertTrue(ex.getMessage().contains("Aircraft not found"));
        verify(aircraftRepository).findById(404L);
    }

    @Test
    public void testGetFlightsByAirport_success() {
        Long airportId = 1L;
        Airport ap = new Airport("Toronto Pearson", "YYZ", null);
        Flight f1 = new Flight();
        Flight f2 = new Flight();
        ap.addDeparture(f1);
        ap.addDeparture(f2);
        when(airportRepository.findById(airportId)).thenReturn(Optional.of(ap));

        List<Flight> result = airportService.getFlightsByAirport(airportId);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(Arrays.asList(f1, f2)));
        verify(airportRepository).findById(airportId);
    }

    @Test
    public void testGetFlightsByAirport_missing() {
        when(airportRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> airportService.getFlightsByAirport(2L));
        assertTrue(ex.getMessage().contains("Airport not found"));
        verify(airportRepository).findById(2L);
    }
}
