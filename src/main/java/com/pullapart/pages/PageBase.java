package com.pullapart.pages;

import com.pullapart.helper.Waits;
import com.pullapart.utils.SeleniumMethods;
import org.openqa.selenium.WebDriver;

public class PageBase {

    protected WebDriver driver;
    protected Waits wait;
    protected SeleniumMethods sel;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new Waits(driver);
        this.sel = new SeleniumMethods(driver, wait);   // NEW: pass Waits to SeleniumMethods
    }

    // Reusable browser actions
    public String getPageTitle() {
        return driver.getTitle();
    }

    public void openUrl(String url) {
        driver.get(url);
    }

}