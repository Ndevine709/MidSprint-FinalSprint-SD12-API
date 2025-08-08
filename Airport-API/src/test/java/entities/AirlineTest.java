package entities;

import com.airportAPI.rest.airline.Airline;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.flights.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AirlineTest {

    Airline airline = new Airline("Air Canada", "AC");

    @Test
    @DisplayName("Constructor sets name & code")
    public void testConstructor() {
        Assertions.assertEquals("Air Canada", airline.getName());
        Assertions.assertEquals("AC", airline.getCode());
    }

    @Test
    public void testAddAircraft() {
        Aircraft aircraft = new Aircraft();
        airline.addAircraft(aircraft);

        Assertions.assertTrue(airline.getAircraft().contains(aircraft));
        Assertions.assertEquals(airline, aircraft.getAirline());
    }

    @Test
    public void testAddFlight() {
        Flight flight = new Flight();
        airline.addFlight(flight);

        Assertions.assertTrue(airline.getFlights().contains(flight));
        Assertions.assertEquals(airline, flight.getAirline());
    }
}
