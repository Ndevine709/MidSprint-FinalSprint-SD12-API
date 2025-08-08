package services;

import com.airportAPI.rest.flights.Flight;
import com.airportAPI.rest.flights.FlightRepository;
import com.airportAPI.rest.flights.FlightService;
import com.airportAPI.rest.airline.Airline;
import com.airportAPI.rest.airline.AirlineRepository;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.aircraft.AircraftRepository;
import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.airport.AirportRepository;
import com.airportAPI.rest.gates.GatesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @Mock
    private AircraftRepository aircraftRepository;

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private GatesRepository gatesRepository;

    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getAllFlights returns repository list")
    public void testGetAllFlights() {
        List<Flight> stub = Arrays.asList(
                new Flight("AC123", new Airline("Air Canada", "AC"), 
                         new Aircraft("C-GABC", "Boeing 737", 180),
                         new Airport("Toronto", "YYZ", null),
                         new Airport("Vancouver", "YVR", null),
                         LocalDateTime.now(), LocalDateTime.now().plusHours(4))
        );
        when(flightRepository.findAll()).thenReturn(stub);

        List<Flight> result = flightService.getAllFlights();

        assertEquals(stub, result);
        verify(flightRepository).findAll();
    }

    @Test
    public void testGetFlightByIdFound() {
        Flight flight = new Flight("AC123", new Airline("Air Canada", "AC"),
                                 new Aircraft("C-GABC", "Boeing 737", 180),
                                 new Airport("Toronto", "YYZ", null),
                                 new Airport("Vancouver", "YVR", null),
                                 LocalDateTime.now(), LocalDateTime.now().plusHours(4));
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        Flight result = flightService.getFlightById(1L);

        assertEquals(flight, result);
        verify(flightRepository).findById(1L);
    }

    @Test
    public void testGetFlightByIdNotFound() {
        when(flightRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            flightService.getFlightById(99L);
        });
    }

    @Test
    public void testDeleteFlight() {
        flightService.deleteFlight(1L);
        verify(flightRepository).deleteById(1L);
    }

    @Test
    public void testGetDeparturesByAirport() {
        List<Flight> departures = Arrays.asList(
                new Flight("AC123", new Airline("Air Canada", "AC"),
                         new Aircraft("C-GABC", "Boeing 737", 180),
                         new Airport("Toronto", "YYZ", null),
                         new Airport("Vancouver", "YVR", null),
                         LocalDateTime.now(), LocalDateTime.now().plusHours(4))
        );
        when(flightRepository.findByDepartureAirportId(1L)).thenReturn(departures);

        List<Flight> result = flightService.getDeparturesByAirport(1L);

        assertEquals(departures, result);
        verify(flightRepository).findByDepartureAirportId(1L);
    }

    @Test
    public void testGetArrivalsByAirport() {
        List<Flight> arrivals = Arrays.asList(
                new Flight("WS456", new Airline("WestJet", "WS"),
                         new Aircraft("C-FGHI", "Airbus A320", 150),
                         new Airport("Vancouver", "YVR", null),
                         new Airport("Toronto", "YYZ", null),
                         LocalDateTime.now(), LocalDateTime.now().plusHours(2))
        );
        when(flightRepository.findByArrivalAirportId(1L)).thenReturn(arrivals);

        List<Flight> result = flightService.getArrivalsByAirport(1L);

        assertEquals(arrivals, result);
        verify(flightRepository).findByArrivalAirportId(1L);
    }
}
