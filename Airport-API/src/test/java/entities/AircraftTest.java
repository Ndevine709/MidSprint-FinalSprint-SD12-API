package entities;

import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.gates.Gates;
import com.airportAPI.rest.flights.Flight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AircraftTest {

    private Aircraft ac() {
        return new Aircraft("C-FABC", "B737-800", 180);
    }

    @Test
    @DisplayName("constructor sets fields")
    void testConstructor() {
        Aircraft a = ac();
        assertEquals("C-FABC", a.getTailNumber());
        assertEquals("B737-800", a.getModel());
        assertEquals(180, a.getCapacity());
    }

    void testAddRemoveAirport() {
        Aircraft a = ac();
        Airport yyz = new Airport("Toronto Pearson", "YYZ", null);

        a.addAirport(yyz);
        assertTrue(a.getAirports().contains(yyz));
        assertTrue(yyz.getAircraft().contains(a));

        a.removeAirport(yyz);
        assertFalse(a.getAirports().contains(yyz));
        assertFalse(yyz.getAircraft().contains(a));
    }

    @Test
    void testAddRemoveGate() {
        Aircraft a = ac();
        Gates g = new Gates();
        a.addAssignedGate(g);
        assertTrue(a.getAssignedGates().contains(g));
        assertEquals(a, g.getAircraft());
        a.removeAssignedGate(g);
        assertFalse(a.getAssignedGates().contains(g));
        assertNull(g.getAircraft());
    }

    @Test
    void testAddRemoveFlight() {
        Aircraft a = ac();
        Flight f = new Flight();
        a.addFlight(f);
        assertTrue(a.getFlights().contains(f));
        assertEquals(a, f.getAircraft());
        a.removeFlight(f);
        assertFalse(a.getFlights().contains(f));
        assertNull(f.getAircraft());
    }
}
