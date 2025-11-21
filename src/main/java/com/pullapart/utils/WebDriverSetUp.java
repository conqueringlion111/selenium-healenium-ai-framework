package com.pullapart.utils;

import com.epam.healenium.SelfHealingDriver;
import com.pullapart.core.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.security.InvalidParameterException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebDriverSetUp {
    private static final Duration IMPLICIT_WAIT = Duration.ofSeconds(8);

    public static WebDriver createDriver(String browser,
                                         String runFromSuite,
                                         String browserRemote) {

        WebDriver rawDriver;

        boolean fromSuite = "true".equalsIgnoreCase(runFromSuite);

        if (fromSuite) {
            rawDriver = createRemoteStyleDriver(browserRemote);
        } else {
            rawDriver = createLocalDriver(browser);
        }

        rawDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT);

        // Optional: maximize all non-mobile drivers
        try {
            rawDriver.manage().window().maximize();
        } catch (UnsupportedOperationException ignored) {
        }

        boolean healeniumEnabled = ConfigManager.getBoolean("healenium.enabled", true);
        if (healeniumEnabled) {
            return SelfHealingDriver.create(rawDriver);
        } else {
            return rawDriver;
        }
    }

    private static WebDriver createRemoteStyleDriver(String browserRemote) {
        if (browserRemote == null) {
            throw new InvalidParameterException("browserRemote cannot be null when runFromSuite=true");
        }

        if (browserRemote.equalsIgnoreCase("chromeRemote")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = baseChromeOptions(false);
            return new ChromeDriver(options);

        } else if (browserRemote.equalsIgnoreCase("chromeRemoteHeadless")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = baseChromeOptions(true);
            options.addArguments("enable-automation");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-extensions");
            options.addArguments("--dns-prefetch-disable");
            options.addArguments("--disable-gpu");
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            return new ChromeDriver(options);

        } else if (browserRemote.equalsIgnoreCase("firefoxRemote")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();

        } else if (browserRemote.equalsIgnoreCase("firefoxRemoteHeadless")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            return new FirefoxDriver(options);

        } else if (browserRemote.equalsIgnoreCase("edgeRemote")) {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();

        } else if (browserRemote.equalsIgnoreCase("chromeGalaxyS5Headless")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = mobileEmulationOptions("Galaxy S5", true);
            return new ChromeDriver(options);
        }

        throw new InvalidParameterException(browserRemote + " is not a valid remote browser setting.");
    }

    private static WebDriver createLocalDriver(String browser) {
        if (browser == null) {
            throw new InvalidParameterException("browser cannot be null");
        }

        if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("FireFoxHeadless")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            return new FirefoxDriver(options);

        } else if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = baseChromeOptions(false);
            return new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("ChromeHeadless")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = baseChromeOptions(true);
            return new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("chromeGalaxyS5")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = mobileEmulationOptions("Galaxy S5", false);
            return new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("IE")) {
            WebDriverManager.iedriver().setup();
            return new InternetExplorerDriver();

        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        }

        throw new InvalidParameterException(browser + " is not a valid browser.");
    }

    private static ChromeOptions baseChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        if (headless) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    private static ChromeOptions mobileEmulationOptions(String deviceName, boolean headless) {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        if (headless) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
            }
        }
    }
}
