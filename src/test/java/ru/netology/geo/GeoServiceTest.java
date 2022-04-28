package ru.netology.geo;

import org.junit.jupiter.api.*;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

public class GeoServiceTest {
    GeoService geoService = new GeoServiceImpl();

    @BeforeEach
    public void init() {
        System.out.println("test started");
    }

    @BeforeAll
    static void started() {
        System.out.println("tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("test completed");
    }

    @AfterAll
    static void finishedAll() {
        System.out.println("tests completed");
    }

    @Test
    void testByIpLocalhost() {
        //arrange
        Location expectedLocation = new Location(null, null, null, 0);
        //act
        Location actualLocation = geoService.byIp(GeoServiceImpl.LOCALHOST);
        //assert
        assertNotNull(actualLocation);
        Locations(expectedLocation, actualLocation);
    }

    @Test
    void testByIpMoscowIP() {
        //arrange
        Location expectedLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        //act
        Location actualLocation = geoService.byIp(GeoServiceImpl.MOSCOW_IP);
        //assert
        assertNotNull(actualLocation);
        Locations(expectedLocation, actualLocation);
    }

    @Test
    void testByIpNewYorkIP() {
        //arrange
        Location expectedLocation = new Location("New York", Country.USA, "10th Avenue", 32);
        //act
        Location actualLocation = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);
        //assert
        assertNotNull(actualLocation);
        Locations(expectedLocation, actualLocation);
    }

    @Test
    void testByIpMoscow() {
        //arrange
        Location expectedLocation = new Location("Moscow", Country.RUSSIA, null, 0);
        //act
        Location actualLocation = geoService.byIp("172.001.001.001");
        //assert
        assertNotNull(expectedLocation);
        Locations(expectedLocation, actualLocation);
    }

    @Test
    void testByIpNewYork() {
        //arrange
        Location expectedLocation = new Location("New York", Country.USA, null, 0);
        //act
        Location actualLocation = geoService.byIp("96.001.001.001");
        //assert
        assertNotNull(actualLocation);
        Locations(expectedLocation, actualLocation);
    }

    @Test
    void testByIpNull() {
        //act
        Location actualLocation = geoService.byIp("110.110.110.110");
        //assert
        assertNull(actualLocation);
    }

    public void Locations(Location expectedLocation, Location actualLocation) {
        assertEquals(expectedLocation.getCountry(), actualLocation.getCountry());
        assertEquals(expectedLocation.getCity(), actualLocation.getCity());
        assertEquals(expectedLocation.getStreet(), actualLocation.getStreet());
        assertEquals(expectedLocation.getBuiling(), actualLocation.getBuiling());
    }
}