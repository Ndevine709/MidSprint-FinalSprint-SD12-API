package entities;

import com.airportAPI.rest.passenger.Passenger;
import com.airportAPI.rest.aircraft.Aircraft;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerTest {

    private Passenger p() {
        return new Passenger();
    }

    @Test
    @DisplayName("defaults and collections initialized")
    void testDefaults() {
        Passenger p = p();
        assertNotNull(p.getFlights());
        assertTrue(p.getFlights().isEmpty());
        // Other fields default to null/0
        assertEquals(0L, p.getId());
        assertNull(p.getFirstName());
        assertNull(p.getLastName());
        assertNull(p.getPhoneNumber());
        assertNull(p.getBirthday());
    }

    @Test
    @DisplayName("getters and setters assign values")
    void testSettersAndGetters() {
        Passenger p = p();

        long id = 42L;
        Calendar bday = Calendar.getInstance();
        bday.set(1995, Calendar.MARCH, 15);
        String first = "Chris";
        String last = "Meadus";
        String phone = "555-123-4567";

        p.setId(id);
        p.setBirthday(bday);
        p.setFirstName(first);
        p.setLastName(last);
        p.setPhoneNumber(phone);

        assertEquals(id, p.getId());
        assertEquals(bday, p.getBirthday());
        assertEquals(first, p.getFirstName());
        assertEquals(last, p.getLastName());
        assertEquals(phone, p.getPhoneNumber());
    }

    @Test
    @DisplayName("flights collection is mutable")
    void testFlightsCollectionMutation() {
        Passenger p = p();
        Aircraft a1 = new Aircraft("C-FABC", "B737-800", 180);
        Aircraft a2 = new Aircraft("C-GXYZ", "A320", 160);

        assertTrue(p.getFlights().isEmpty());

        p.getFlights().add(a1);
        p.getFlights().add(a2);
        assertTrue(p.getFlights().contains(a1));
        assertTrue(p.getFlights().contains(a2));
        assertEquals(2, p.getFlights().size());

        p.getFlights().remove(a1);
        assertFalse(p.getFlights().contains(a1));
        assertTrue(p.getFlights().contains(a2));
        assertEquals(1, p.getFlights().size());
    }
}
