package com.justlife.stepDefinations;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;


public class Hooks {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @Before
    public void setup() throws IOException {
        config.loadProperties();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        String url = config.getProperty("url");
        driver.get(url);
    }


    @AfterStep
    public void afterStep(Scenario scenario) {
        // Check if the scenario is failed or not
            try {
                // Call the screenshot utility after each step
                screenshotsUtils.takeScreenshot(driver, scenario.getName());
                System.out.println("Screenshot taken for step: " + scenario.getName());
            } catch (Exception e) {
                System.out.println("Error capturing screenshot: " + e.getMessage());
            }

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
