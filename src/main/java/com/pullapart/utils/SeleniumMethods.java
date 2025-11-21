package com.pullapart.utils;

import com.pullapart.helper.Waits;
import com.pullapart.pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SeleniumMethods {

    private WebDriver driver;
    private Waits wait;

    public SeleniumMethods(WebDriver driver, Waits wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void click(By locator) {
        wait.waitForClickable(locator).click();
    }

    public void type(By locator, String text) {
        WebElement element = wait.waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return wait.waitForVisible(locator).getText();
    }

    public void jsClick(By locator) {
        WebElement element = wait.waitForVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void hover(By locator) {
        WebElement element = wait.waitForVisible(locator);
        new Actions(driver).moveToElement(element).perform();
    }

    public int count(By locator) {
        return driver.findElements(locator).size();
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public int findTotalElementCount(By locator) {
        return count(locator);
    }
}
