package services;

import com.airportAPI.rest.passenger.PassengerService;
import com.airportAPI.rest.passenger.PassengerRepository;
import com.airportAPI.rest.passenger.Passenger;
import com.airportAPI.rest.aircraft.AircraftRepository;
import com.airportAPI.rest.aircraft.Aircraft;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PassengerServiceTest {

    @Mock private PassengerRepository passengerRepository;
    @Mock private AircraftRepository aircraftRepository;

    @InjectMocks
    private PassengerService passengerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Passenger passengerWithId(long id, String first, String last) {
        Passenger p = new Passenger();
        p.setId(id);
        p.setFirstName(first);
        p.setLastName(last);
        return p;
    }

    @Test
    void testGetAllPassengers() {
        List<Passenger> stub = Arrays.asList(new Passenger(), new Passenger());
        when(passengerRepository.findAll()).thenReturn(stub);

        List<Passenger> result = passengerService.getAllPassengers();

        assertEquals(stub, result);
        verify(passengerRepository).findAll();
    }

    @Test
    void testGetPassengerByLastName() {
        Passenger p = passengerWithId(1L, "John", "Doe");
        when(passengerRepository.findByLastName("Doe")).thenReturn(p);

        Passenger result = passengerService.getPassengerByLastName("Doe");

        assertEquals(p, result);
        verify(passengerRepository).findByLastName("Doe");
    }

    @Test
    void testGetPassengerByIdFound() {
        Passenger p = passengerWithId(1L, "John", "Doe");
        when(passengerRepository.findById(1L)).thenReturn(Optional.of(p));

        Passenger result = passengerService.getPassengerById(1L);

        assertEquals(p, result);
        verify(passengerRepository).findById(1L);
    }

    @Test
    void testGetPassengerByIdNotFound() {
        when(passengerRepository.findById(99L)).thenReturn(Optional.empty());

        Passenger result = passengerService.getPassengerById(99L);

        assertNull(result);
        verify(passengerRepository).findById(99L);
    }

    @Test
    void testCreatePassenger() {
        Passenger p = passengerWithId(1L, "Alice", "Smith");
        when(passengerRepository.save(p)).thenReturn(p);

        Passenger saved = passengerService.createPassenger(p);

        assertEquals(p, saved);
        verify(passengerRepository).save(p);
    }

    @Test
    void testUpdatePassengerFound() {
        Passenger existing = passengerWithId(1L, "Old", "Name");
        Passenger update = passengerWithId(1L, "New", "Name");

        Calendar bday = Calendar.getInstance();
        bday.set(1990, Calendar.MARCH, 15);
        update.setBirthday(bday);
        update.setPhoneNumber("123-456");

        when(passengerRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(passengerRepository.save(any(Passenger.class))).thenAnswer(i -> i.getArgument(0));

        Passenger result = passengerService.updatePassenger(1L, update);

        assertNotNull(result);
        assertEquals("New", result.getFirstName());
        assertEquals("Name", result.getLastName());
        assertEquals(bday, result.getBirthday());
        assertEquals("123-456", result.getPhoneNumber());
        verify(passengerRepository).findById(1L);
        verify(passengerRepository).save(existing);
    }

    @Test
    void testUpdatePassengerNotFound() {
        Passenger update = passengerWithId(1L, "New", "Name");
        when(passengerRepository.findById(1L)).thenReturn(Optional.empty());

        Passenger result = passengerService.updatePassenger(1L, update);

        assertNull(result);
        verify(passengerRepository).findById(1L);
        verify(passengerRepository, never()).save(any());
    }

    @Test
    void testDeletePassengerById() {
        passengerService.deletePassengerById(5L);
        verify(passengerRepository).deleteById(5L);
    }

    @Test
    void testAssignAircraft_success() {
        Passenger p = passengerWithId(1L, "John", "Doe");
        p.setFlights(new HashSet<>());
        Aircraft ac = new Aircraft("C-FABC", "B737-800", 180);

        when(passengerRepository.findById(1L)).thenReturn(Optional.of(p));
        when(aircraftRepository.findById(2L)).thenReturn(Optional.of(ac));
        when(passengerRepository.save(any(Passenger.class))).thenAnswer(i -> i.getArgument(0));

        Passenger saved = passengerService.assignAircraft(1L, 2L);

        assertTrue(saved.getFlights().contains(ac));
        verify(passengerRepository).findById(1L);
        verify(aircraftRepository).findById(2L);
        verify(passengerRepository).save(p);
    }

    @Test
    void testAssignAircraft_passengerNotFound() {
        when(passengerRepository.findById(1L)).thenReturn(Optional.empty());
        when(aircraftRepository.findById(2L)).thenReturn(Optional.of(new Aircraft()));

        Passenger result = passengerService.assignAircraft(1L, 2L);

        assertNull(result);
        verify(passengerRepository).findById(1L);
        verify(aircraftRepository).findById(2L);
        verify(passengerRepository, never()).save(any());
    }

    @Test
    void testAssignAircraft_aircraftNotFound() {
        when(passengerRepository.findById(1L)).thenReturn(Optional.of(new Passenger()));
        when(aircraftRepository.findById(2L)).thenReturn(Optional.empty());

        Passenger result = passengerService.assignAircraft(1L, 2L);

        assertNull(result);
        verify(passengerRepository).findById(1L);
        verify(aircraftRepository).findById(2L);
        verify(passengerRepository, never()).save(any());
    }
}
