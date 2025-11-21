package com.pullapart.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public final class ScreenshotUtil {

    private ScreenshotUtil() {}

    public static String captureAndReturnPath(WebDriver driver, String methodName) {
        if (driver == null) return null;

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String dir = "reports/screenshots/";
            File folder = new File(dir);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String filePath = dir + methodName + ".png";
            File dest = new File(filePath);
            FileUtils.copyFile(src, dest);

            return filePath;

        } catch (WebDriverException | IOException e) {
            System.out.println("Screenshot error: " + e.getMessage());
            return null;
        }
    }
}
