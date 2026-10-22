package com.parabank.utils;

import org.openqa.selenium.WebDriver;

/**
 * OOP Concept: Encapsulation
 * Encapsulates the WebDriver instance inside a ThreadLocal container,
 * ensuring thread-safety during parallel test execution.
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // Private constructor to prevent direct instantiation (Singleton-style utility)
    private DriverManager() {
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
