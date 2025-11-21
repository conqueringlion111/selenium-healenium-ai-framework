package com.pullapart.helper;

import org.openqa.selenium.WebDriver;

public class WindowHandle {

    private WebDriver driver;

    public WindowHandle(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToNewWindow() {
        String current = driver.getWindowHandle();
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(current)) {
                driver.switchTo().window(window);
                return;
            }
        }
        throw new RuntimeException("No new window found.");
    }

    public void switchToMainWindow() {
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }
}
