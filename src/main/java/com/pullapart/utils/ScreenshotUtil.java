package com.pullapart.utils;

import com.epam.healenium.SelfHealingDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public final class ScreenshotUtil {

    private ScreenshotUtil() {}

    public static String captureAndReturnPath(WebDriver driver, String methodName) {
        try {
            // Unwrap Healenium proxy if needed
            if (driver instanceof SelfHealingDriver) {
                driver = ((SelfHealingDriver) driver).getDelegate();
            }

            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            String path = "printscreen/" + methodName + ".png";
            File dest = new File(path);
            FileUtils.copyFile(src, dest);
            return path;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
