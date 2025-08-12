package entities;

import com.airportAPI.rest.gates.Gates;
import com.airportAPI.rest.airport.Airport;
import com.airportAPI.rest.aircraft.Aircraft;
import com.airportAPI.rest.flights.Flight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GatesTest {

    private Gates gate() {
        return new Gates("A12", "T1", null, true);
    }

    @Test
    @DisplayName("constructor sets fields")
    void testConstructor() {
        Gates g = gate();
        assertEquals("A12", g.getGateNumber());
        assertEquals("T1", g.getTerminal());
        assertTrue(g.isDepartureGate());
        assertNull(g.getAirport());
        assertNull(g.getAircraft());
        assertNotNull(g.getDepartureFlights());
        assertNotNull(g.getArrivalFlights());
        assertTrue(g.getDepartureFlights().isEmpty());
        assertTrue(g.getArrivalFlights().isEmpty());
    }

    @Test
    void testSetAirportDirect() {
        Gates g = gate();
        Airport a = new Airport("Toronto Pearson", "YYZ", null);

        assertNull(g.getAirport());
        g.setAirport(a);
        assertEquals(a, g.getAirport());

        g.setAirport(null);
        assertNull(g.getAirport());
    }

    @Test
    void testWireAirportViaAirportHelpers() {
        Gates g = gate();
        Airport a = new Airport("Toronto Pearson", "YYZ", null);

        a.addGate(g);
        assertEquals(a, g.getAirport());
        assertTrue(a.getGates().contains(g));

        a.removeGate(g);
        assertNull(g.getAirport());
        assertFalse(a.getGates().contains(g));
    }

    @Test
    void testSetAircraftDirect() {
        Gates g = gate();
        Aircraft ac = new Aircraft("C-FABC", "B737-800", 180);

        assertNull(g.getAircraft());
        g.setAircraft(ac);
        assertEquals(ac, g.getAircraft());

        g.setAircraft(null);
        assertNull(g.getAircraft());
    }

    @Test
    void testWireAircraftViaAircraftHelpers() {
        Gates g = gate();
        Aircraft ac = new Aircraft("C-FABC", "B737-800", 180);

        ac.addAssignedGate(g);
        assertEquals(ac, g.getAircraft());
        assertTrue(ac.getAssignedGates().contains(g));

        ac.removeAssignedGate(g);
        assertNull(g.getAircraft());
        assertFalse(ac.getAssignedGates().contains(g));
    }

    @Test
    void testDepartureFlagToggle() {
        Gates g = gate();
        assertTrue(g.isDepartureGate());

        g.setDepartureGate(false);
        assertFalse(g.isDepartureGate());

        g.setDepartureGate(true);
        assertTrue(g.isDepartureGate());
    }

    @Test
    void testFlightsCollectionsAreMutable() {
        Gates g = gate();
        Flight fDep = new Flight();
        Flight fArr = new Flight();

        g.getDepartureFlights().add(fDep);
        g.getArrivalFlights().add(fArr);

        assertTrue(g.getDepartureFlights().contains(fDep));
        assertTrue(g.getArrivalFlights().contains(fArr));

        g.getDepartureFlights().remove(fDep);
        g.getArrivalFlights().remove(fArr);

        assertFalse(g.getDepartureFlights().contains(fDep));
        assertFalse(g.getArrivalFlights().contains(fArr));
    }
}
