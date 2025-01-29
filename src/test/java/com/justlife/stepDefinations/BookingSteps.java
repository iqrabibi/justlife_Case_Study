package com.justlife.stepDefinations;

import com.justlife.pages.BookingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;


public class BookingSteps {
   private WebDriver driver;
   private WebDriverWait wait;
   private BookingPage bookingPage;

    public BookingSteps() throws IOException {
        driver = Hooks.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(70));
        bookingPage = new BookingPage(driver, wait);
    }

    @When("Booking type is {string}")
    public void booking_type(String type)
    {
        bookingPage.selectBookingType(type);
    }

    @And("Location is {string}")
    public void location_type(String location) throws InterruptedException {
        bookingPage.selectLocation(location);
    }

    @And("Material section is {string}")
    public void material_section(String materialType) throws InterruptedException {
        bookingPage.selectMaterialSection(materialType);
    }

    @And("Number of cleaner is {string}")
    public void number_of_cleaner(String numberOfCleaner)  {
        bookingPage.numberOfCleaner(numberOfCleaner);
    }
    @When("Number of hrs is {string}")
    public void number_of_hrs(String numberOfHrs)
    {
        bookingPage.numberOfHrs(numberOfHrs);
    }
    @And("Suggest cleaner {int}")
    public void suggested_cleaner(int suggestedCleaner) throws InterruptedException {
        bookingPage.suggestedCleaner(suggestedCleaner);
    }
    @And("Date is {string} {string}")
    public void date_selection(String date , String  time) throws InterruptedException {
        bookingPage.dateTimeSelection(date , time);
    }
    @And("Cash payment")
    public void cash_payment()
    {
        bookingPage.paymentMethod();
    }



}
