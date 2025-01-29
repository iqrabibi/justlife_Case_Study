package com.justlife.stepDefinations;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class screenshotsUtils {
    // Method to capture screenshot
    public static void takeScreenshot(WebDriver driver, String stepName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        // Take screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Define the destination file path
        File destFile = new File("screenshots/" + stepName + "_" + timestamp + ".png");
        // Copy the screenshot to the destination file
        FileUtils.copyFile(srcFile, destFile);
    }
}
