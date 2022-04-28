package ru.netology.sender;


import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;
import static ru.netology.geo.GeoServiceImpl.NEW_YORK_IP;
import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

public class MessageSenderTest {
    MessageSender messageSender = createMock();

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
    void testSendRussia() {
        //arrange
        String expectedCountry = "Добро пожаловать";
        Map<String, String> headers = createHeaders(MOSCOW_IP);
        //act
        String actualCountry = messageSender.send(headers);
        //assert
        assertEquals(expectedCountry, actualCountry);
    }

    @Test
    void testSendOtherCountry() {
        //arrange
        String expectedCountry = "Welcome";
        Map<String, String> headers = createHeaders(NEW_YORK_IP);
        //act
        String actualCountry = messageSender.send(headers);
        //assert
        assertEquals(expectedCountry, actualCountry);
    }

    @Test
    void testSendEmptyAddress() {
        //arrange
        String expectedCountry = "Welcome";
        Map<String, String> headers = createHeaders("");
        //act
        String actualCountry = messageSender.send(headers);
        //assert
        assertEquals(expectedCountry, actualCountry);
    }

    private MessageSender createMock() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Location locationRussia = new Location("Moscow", RUSSIA, "Lenina", 15);
        Location locationUSA = new Location("New York", USA, "10th Avenue", 32);
        Mockito.when(geoService.byIp(MOSCOW_IP)).thenReturn(locationRussia);
        Mockito.when(geoService.byIp(NEW_YORK_IP)).thenReturn(locationUSA);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(USA)).thenReturn("Welcome");
        return new MessageSenderImpl(geoService, localizationService);
    }

    private Map<String, String> createHeaders(String ipAddress) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(IP_ADDRESS_HEADER, ipAddress);
        return headers;
    }
}
