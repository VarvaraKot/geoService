package ru.netology.i18n;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.GERMANY;
import static ru.netology.entity.Country.RUSSIA;

public class LocalizationServiceTest {
    LocalizationService localizationService = new LocalizationServiceImpl();

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
    void testLocaleRussia() {
        //arrange
        String expectedText = "Добро пожаловать";
        //act
        String actualText = localizationService.locale(RUSSIA);
        //assert
        assertEquals(expectedText, actualText);
    }

    @Test
    void testLocaleOtherCountry() {
        //arrange
        String expectedText = "Welcome";
        //act
        String actualText = localizationService.locale(GERMANY);
        //assert
        assertEquals(expectedText, actualText);
    }
}
