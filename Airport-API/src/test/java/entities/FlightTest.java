package entities;

import com.airportAPI.rest.flights.Flight;
import com.airportAPI.rest.airline.Airline;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.airport.Airport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class FlightTest {

    @Test
    @DisplayName("Test flight constructor")
    public void testParameterizedConstructor() {
        Airline airline = new Airline("Air Canada", "AC");
        Aircraft aircraft = new Aircraft("C-GABC", "Boeing 737", 180);
        Airport departureAirport = new Airport("Toronto Pearson International", "YYZ", null);
        Airport arrivalAirport = new Airport("Vancouver International", "YVR", null);
        
        Flight flight = new Flight("AC123", airline, aircraft, departureAirport, arrivalAirport, 
                                 LocalDateTime.of(2024, 1, 15, 10, 30), 
                                 LocalDateTime.of(2024, 1, 15, 14, 15));
        
        Assertions.assertEquals("AC123", flight.getFlightNumber());
        Assertions.assertEquals(airline, flight.getAirline());
        Assertions.assertEquals("SCHEDULED", flight.getStatus());
    }

    @Test
    @DisplayName("Test flight number getter and setter")
    public void testFlightNumber() {
        Flight flight = new Flight();
        flight.setFlightNumber("WS456");
        Assertions.assertEquals("WS456", flight.getFlightNumber());
    }

    @Test
    @DisplayName("Test status getter and setter")
    public void testStatus() {
        Flight flight = new Flight();
        flight.setStatus("BOARDING");
        Assertions.assertEquals("BOARDING", flight.getStatus());
        
        flight.setStatus("DELAYED");
        Assertions.assertEquals("DELAYED", flight.getStatus());
    }

    @Test
    @DisplayName("Test delay reason getter and setter")
    public void testDelayReason() {
        Flight flight = new Flight();
        flight.setDelayReason("Weather");
        Assertions.assertEquals("Weather", flight.getDelayReason());
    }

    @Test
    @DisplayName("Test scheduled times")
    public void testScheduledTimes() {
        Flight flight = new Flight();
        LocalDateTime departure = LocalDateTime.of(2024, 1, 15, 11, 0);
        LocalDateTime arrival = LocalDateTime.of(2024, 1, 15, 15, 0);
        
        flight.setScheduledDeparture(departure);
        flight.setScheduledArrival(arrival);
        
        Assertions.assertEquals(departure, flight.getScheduledDeparture());
        Assertions.assertEquals(arrival, flight.getScheduledArrival());
    }

    @Test
    @DisplayName("Test airline getter and setter")
    public void testAirline() {
        Flight flight = new Flight();
        Airline airline = new Airline("Air Canada", "AC");
        flight.setAirline(airline);
        Assertions.assertEquals(airline, flight.getAirline());
    }

    @Test
    @DisplayName("Test aircraft getter and setter")
    public void testAircraft() {
        Flight flight = new Flight();
        Aircraft aircraft = new Aircraft("C-GABC", "Boeing 737", 180);
        flight.setAircraft(aircraft);
        Assertions.assertEquals(aircraft, flight.getAircraft());
    }
    
}
