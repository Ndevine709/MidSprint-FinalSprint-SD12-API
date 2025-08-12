package entities;

import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.gates.Gates;
import com.airportAPI.rest.flights.Flight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {

    private Airport ap() {
        return new Airport("Toronto Pearson", "YYZ", null);
    }

    @Test
    @DisplayName("constructor sets fields")
    void testConstructor() {
        Airport a = ap();
        assertEquals("Toronto Pearson", a.getName());
        assertEquals("YYZ", a.getCode());
        assertNull(a.getCity());
    }

    @Test
    void testAddRemoveAircraft() {
        Airport a = ap();
        Aircraft ac = new Aircraft("C-FABC", "B737-800", 180);

        a.addAircraft(ac);
        assertTrue(a.getAircraft().contains(ac));
        assertTrue(ac.getAirports().contains(a));

        a.removeAircraft(ac);
        assertFalse(a.getAircraft().contains(ac));
        assertFalse(ac.getAirports().contains(a));
    }

    @Test
    void testAddRemoveGate() {
        Airport a = ap();
        Gates g = new Gates();

        a.addGate(g);
        assertTrue(a.getGates().contains(g));
        assertEquals(a, g.getAirport());

        a.removeGate(g);
        assertFalse(a.getGates().contains(g));
        assertNull(g.getAirport());
    }

    @Test
    void testAddDeparture() {
        Airport a = ap();
        Flight f = new Flight();

        a.addDeparture(f);
        assertTrue(a.getDepartures().contains(f));
        assertEquals(a, f.getDepartureAirport());
    }

    @Test
    void testAddArrival() {
        Airport a = ap();
        Flight f = new Flight();

        a.addArrival(f);
        assertTrue(a.getArrivals().contains(f));
        assertEquals(a, f.getArrivalAirport());
    }
}
