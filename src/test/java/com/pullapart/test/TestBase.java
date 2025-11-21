package com.pullapart.test;

import com.pullapart.core.ConfigManager;
import com.pullapart.core.DriverManager;
import com.pullapart.core.PageManager;
import com.pullapart.pages.HomePage;
import com.pullapart.utils.JsonReader;
import com.pullapart.utils.ScreenshotUtil;
import com.pullapart.utils.WebDriverSetUp;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Properties;

public class TestBase {

    protected WebDriver driver;
    protected HomePage homepage;
    protected PageManager pages;

    protected String baseUrl;
    protected String email;
    protected String password;
    protected String myPassword;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws IOException {
        ConfigManager.load();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browserRemote"})
    public void beforeMethod(@Optional String browserRemote) {

        String browser = ConfigManager.get("browser");
        String runFromSuite = ConfigManager.get("runFromSuite");

        driver = WebDriverSetUp.createDriver(browser, runFromSuite, browserRemote);
        DriverManager.setDriver(driver);

        baseUrl = resolve("test.env", "baseUrl");
        email = resolve("email", "email");
        password = resolve("password", "password");
        myPassword = resolve("myPassword", "myPassword");

        driver.get(baseUrl);

        pages = new PageManager(driver);
        homepage = pages.home();
    }

    private String resolve(String sysKey, String configKeyFallback) {
        String sys = System.getProperty(sysKey);
        if (sys != null) return sys;
        return ConfigManager.get(configKeyFallback);
    }

    @DataProvider(name = "dataProvider")
    public Object[][] passData(Method method) throws IOException, ParseException {
        String name = getClass().getName();
        String fileName = name.substring(name.lastIndexOf(".") + 1).trim();
        return JsonReader.getdata(
                "src/main/java/com/pullapart/dataprovider/" + fileName + ".json",
                method.getName()
        );
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.captureAndReturnPath(driver, result.getMethod().getMethodName());
            // TODO: hook into TestRail integration
        } else {
            // TODO: TestRail passed status
        }
        DriverManager.quit();
    }
}
