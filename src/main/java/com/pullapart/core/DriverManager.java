package com.pullapart.core;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private static WebDriver driver;

    private DriverManager() {}

    public static void setDriver(WebDriver drv) {
        driver = drv;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver has not been initialized");
        }
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }
}
