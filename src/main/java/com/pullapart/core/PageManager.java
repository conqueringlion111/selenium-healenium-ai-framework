package com.pullapart.core;

import com.pullapart.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class PageManager {
    private final WebDriver driver;
    private HomePage homePage;

    public PageManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage home() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

}
