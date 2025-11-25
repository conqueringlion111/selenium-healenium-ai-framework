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
        WebDriver driver = WebDriverSetUp.createDriver(
                ConfigManager.get("browser"),
                ConfigManager.get("runFromSuite"),
                browserRemote
        );

        DriverManager.setDriver(driver);

        baseUrl = System.getProperty("test.env", ConfigManager.get("baseUrl"));

        driver.get(baseUrl);

        // FIX: Initialize PageManager + homepage
        PageManager.init();
        homepage = PageManager.getInstance().home();
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
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtil.captureAndReturnPath(DriverManager.getDriver(), result.getMethod().getMethodName());
        }

        DriverManager.quit();
    }
}
