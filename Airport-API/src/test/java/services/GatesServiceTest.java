package services;

import com.airportAPI.rest.gates.GatesService;
import com.airportAPI.rest.gates.GatesRepository;
import com.airportAPI.rest.gates.Gates;
import com.airportAPI.rest.airport.AirportRepository;
import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.aircraft.AircraftRepository;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.flights.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GatesServiceTest {

    @Mock private GatesRepository gatesRepository;
    @Mock private AirportRepository airportRepository;
    @Mock private AircraftRepository aircraftRepository;

    @InjectMocks
    private GatesService gatesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Airport airportWithId(long id) {
        Airport a = new Airport("Name", "XXX", null);
        a.setId(id);
        return a;
    }

    private Gates gate(String no, String term, Airport ap, boolean dep) {
        return new Gates(no, term, ap, dep);
    }

    @Test
    public void testGetAllGates() {
        List<Gates> stub = Arrays.asList(new Gates(), new Gates());
        when(gatesRepository.findAll()).thenReturn(stub);
        List<Gates> result = gatesService.getAllGates();
        assertEquals(stub, result);
        verify(gatesRepository).findAll();
    }

    @Test
    public void testGetGatesByIdFound() {
        Gates g = new Gates();
        when(gatesRepository.findById(1L)).thenReturn(Optional.of(g));
        Gates result = gatesService.getGatesById(1L);
        assertEquals(g, result);
        verify(gatesRepository).findById(1L);
    }

    @Test
    public void testGetGatesByIdNotFound() {
        when(gatesRepository.findById(99L)).thenReturn(Optional.empty());
        Gates result = gatesService.getGatesById(99L);
        assertNull(result);
        verify(gatesRepository).findById(99L);
    }

    @Test
    public void testCreateGates_success() {
        Airport apRef = airportWithId(7L);
        Airport apFull = airportWithId(7L);
        Gates incoming = gate("A12", "T1", apRef, true);
        when(airportRepository.findById(7L)).thenReturn(Optional.of(apFull));
        when(gatesRepository.save(incoming)).thenReturn(incoming);
        Gates saved = gatesService.createGates(incoming);
        assertEquals(apFull, saved.getAirport());
        verify(airportRepository).findById(7L);
        verify(gatesRepository).save(incoming);
    }

    @Test
    public void testCreateGates_airportMissing() {
        Airport apRef = airportWithId(8L);
        Gates incoming = gate("B3", "T2", apRef, false);
        when(airportRepository.findById(8L)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> gatesService.createGates(incoming));
        assertTrue(ex.getMessage().contains("Airport not found"));
        verify(airportRepository).findById(8L);
        verify(gatesRepository, never()).save(any());
    }

    @Test
    public void testUpdateGates_found() {
        Long id = 1L;
        Airport ap = airportWithId(5L);
        Gates existing = gate("A1", "T0", ap, true);
        Gates update = gate("C9", "T3", ap, false);
        when(gatesRepository.findById(id)).thenReturn(Optional.of(existing));
        when(gatesRepository.save(any(Gates.class))).thenAnswer(i -> i.getArgument(0));
        Gates result = gatesService.updateGates(id, update);
        assertNotNull(result);
        assertEquals("C9", result.getGateNumber());
        assertEquals("T3", result.getTerminal());
        assertEquals(ap, result.getAirport());
        assertFalse(result.isDepartureGate());
        verify(gatesRepository).findById(id);
        verify(gatesRepository).save(existing);
    }

    @Test
    public void testUpdateGates_notFound() {
        when(gatesRepository.findById(2L)).thenReturn(Optional.empty());
        Gates result = gatesService.updateGates(2L, new Gates());
        assertNull(result);
        verify(gatesRepository).findById(2L);
        verify(gatesRepository, never()).save(any());
    }

    @Test
    public void testDeleteGateById() {
        gatesService.deleteGateById(3L);
        verify(gatesRepository).deleteById(3L);
    }

    @Test
    public void testAssignAircraftToGate_success() {
        Long gateId = 1L, aircraftId = 2L;
        Gates g = new Gates();
        Aircraft ac = new Aircraft("C-FABC", "B737-800", 180);
        when(gatesRepository.findById(gateId)).thenReturn(Optional.of(g));
        when(aircraftRepository.findById(aircraftId)).thenReturn(Optional.of(ac));
        when(gatesRepository.save(any(Gates.class))).thenAnswer(i -> i.getArgument(0));
        Gates saved = gatesService.assignAircraftToGate(gateId, aircraftId);
        assertEquals(ac, saved.getAircraft());
        verify(gatesRepository).findById(gateId);
        verify(aircraftRepository).findById(aircraftId);
        verify(gatesRepository).save(g);
    }

    @Test
    public void testRemoveAircraftFromGate_success() {
        Long gateId = 5L;
        Gates g = new Gates();
        g.setAircraft(new Aircraft("C-GXYZ", "A320", 160));
        when(gatesRepository.findById(gateId)).thenReturn(Optional.of(g));
        when(gatesRepository.save(any(Gates.class))).thenAnswer(i -> i.getArgument(0));
        Gates saved = gatesService.removeAircraftFromGate(gateId);
        assertNull(saved.getAircraft());
        verify(gatesRepository).findById(gateId);
        verify(gatesRepository).save(g);
    }

    @Test
    public void testGetFlightsByGate_success() {
        Long gateId = 11L;
        Gates g = new Gates();
        Flight f1 = new Flight();
        Flight f2 = new Flight();
        Flight f3 = new Flight();
        g.getDepartureFlights().add(f1);
        g.getDepartureFlights().add(f2);
        g.getArrivalFlights().add(f3);
        when(gatesRepository.findById(gateId)).thenReturn(Optional.of(g));
        List<Flight> flights = gatesService.getFlightsByGate(gateId);
        assertEquals(3, flights.size());
        assertTrue(flights.containsAll(Arrays.asList(f1, f2, f3)));
        verify(gatesRepository).findById(gateId);
    }
}
