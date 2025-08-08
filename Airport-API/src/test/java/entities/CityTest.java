package entities;

import com.airportAPI.rest.city.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class CityTest {
    
    @Test
    @DisplayName("Test city constructor")
    public void testParameterizedConstructor() {
        City city = new City("Toronto", "Ontario", 3000000);
        Assertions.assertEquals("Toronto", city.getName());
        Assertions.assertEquals("Ontario", city.getState());
        Assertions.assertEquals(3000000, city.getPopulation());
    }

    @Test
    @DisplayName("Test ID getter and setter")
    public void testId() {
        City city = new City();
        city.setId(1L);
        Assertions.assertEquals(1L, city.getId());
    }

    @Test
    @DisplayName("Test name getter and setter")
    public void testName() {
        City city = new City();
        city.setName("Toronto");
        Assertions.assertEquals("Toronto", city.getName());
    }


    @Test
    @DisplayName("Test state getter and setter")
    public void testState() {
        City city = new City();
        city.setState("Ontario");
        Assertions.assertEquals("Ontario", city.getState());
    }

    @Test
    @DisplayName("Test population getter and setter")
    public void testPopulation() {
        City city = new City();
        city.setPopulation(3000000);
        Assertions.assertEquals(3000000, city.getPopulation());
    }

    @Test
    @DisplayName("Test Null Values")
    public void testNullValues() {
        City city = new City();
        city.setName(null);
        city.setState(null);
        
        Assertions.assertNull(city.getName());
        Assertions.assertNull(city.getState());
    }
}
