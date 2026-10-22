package com.parabank.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * OOP Concept: Abstraction & Interface Polymorphism
 * Casts WebDriver to the TakesScreenshot interface to extract PNG captures on demand.
 */
public class ScreenshotUtil {

    private ScreenshotUtil() {
    }

    public static String captureScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            return null;
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String directory = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator;
        String fullPath = directory + fileName;

        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(fullPath);

        try {
            FileUtils.copyFile(sourceFile, destinationFile);
            return fullPath;
        } catch (IOException e) {
            System.err.println("Could not save failure screenshot: " + e.getMessage());
            return null;
        }
    }
}
