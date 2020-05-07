package com.controller;

import com.example.Building;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HomeServletTest {
    HomeServlet homeServlet = new HomeServlet();
    ChromeOptions options = new ChromeOptions();
    final static String stringWebDriver = "webdriver.chrome.driver";
    final static String pathToWebDriver = "D:\\chromedriver\\chromedriver (2).exe";

    @BeforeEach
    void initAll() {
        homeServlet.init();
        System.setProperty(stringWebDriver, pathToWebDriver);
        options.addArguments("--headless", "--disable-gpu", "window-size=1920,1200", "--ignore-certificate-errors");
    }

    @ParameterizedTest
    @ValueSource(strings = {"93.84.186.156", "195.208.131.1"})
    public void clientCountryTest(String argument) {
        String resultName = homeServlet.clientCountry(argument);
        Assertions.assertNotEquals(null, resultName);
    }

    @Test
    public void isAvailableTest() {
        boolean result = homeServlet.isAvailable("Belarus", "Беларусь");
        Assertions.assertEquals(true, result);
    }

    @Test
    public void showAllRoomsTest() {
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.get("http://localhost:8888/show");
        int numberRoom = 2;
        String expected = Building.getInstance().listRoom().get(numberRoom).getName();
        String actual = webDriver.findElements(By.tagName("a")).get(numberRoom).getText();
        Assertions.assertEquals(expected, actual);
        webDriver.quit();
    }

    @Test
    public void showAllRoomsClickTest() {
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.get("http://localhost:8888/show");
        int numberRoom = 4;
        webDriver.findElements(By.tagName("a")).get(numberRoom).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Alert alert;
        try {
            alert = webDriver.switchTo().alert();
            String expected = "Вы не можете войти в эту комнату, т.к. Вы из другой страны";
            Assertions.assertEquals(expected, alert.getText());
        } catch (NoAlertPresentException e) {

            String expected = "Комната " + Building.getInstance().listRoom().get(numberRoom).getName();
            String text = webDriver.findElement(By.tagName("h2")).getText();
            Assertions.assertEquals(expected, text);
        }
        webDriver.quit();
    }
}
