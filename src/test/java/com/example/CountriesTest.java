package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CountriesTest {

    @Test
    public void testName1() {
        String expected = "Беларусь";
        Assertions.assertEquals(expected, Countries.BELARUS.countryNameRus());
    }

    @Test
    public void testName2() {
        String expected = "Франция";
        Assertions.assertEquals(expected, Countries.FRANCE.countryNameRus());
    }

    @Test
    public void testListOfCountriesRus() {
        List<String> expected = Arrays.asList("Австрия", "Беларусь", "Греция", "Италия", "Россия", "Франция");
        Assertions.assertEquals(expected, Countries.listOfCountriesRus());
    }
}
