package com.justlife.pages;


import com.justlife.dataloader.DataLoader;
import com.justlife.dataloader.JsonParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.testng.Assert;



public class BookingPage {
    protected WebDriver driver;
    private DataLoader dataLoader;
    protected WebDriverWait wait;
    public BookingPage(WebDriver driver , WebDriverWait wait) throws IOException {

        this.driver= driver;
        this.wait = wait;
        dataLoader= JsonParser.loadTestData();
    }
    public static By phoneNumberInputField = By.id("phone-number-input");
    public static By otpSendButton = By.id("create-otp-button");
    public  static By otpInnerFields = By.xpath("//*[@class='digit-inputs-wrapper']/descendant::input");
    public static By weeklyOption = By.xpath("//*[@id='weekly']/following-sibling::*/*[contains(@class,'radio-circle')]");
    public static By frequencyButton = By.id("frequency-button");
    public static By professionalSelector = By.xpath("//*[contains(@class,'attribute-section')]/descendant::span[contains(@id,'cleaner')]");
    public static By hrsNumber = By.xpath("//*[contains(@class,'attribute-section')]/descendant::span[contains(@id,'duration')]");
    public static By cleaningMaterial = By.xpath("//*[text()='Yes, please']");
    public static By funnelNextButton=By.id("funnel-next-button");

    public static By locationInput = By.id("map-location-input");
    public static By locationSelection = By.xpath("//*[@id='search-location-items']/descendant::span[text()='Dubai Marina']");
    public static By mapLocationButton = By.id("map-modal-map-confirm-button");
    public static By professionalHistoryClose = By.xpath("//*[@id='cleaner-modal']//*[contains(@class,'modal-card-head')]//img[@alt='cancel-icon']");
    public static By firstAvailableDate = By.xpath("(//*[@id='single-date-picker-inside']//*[contains(@id,'enabled-day-')]/ancestor::*[contains(@class,'c-selectable-wrapper')])[1]");
    public static By firsAvailableTime = By.xpath("//*[contains(@id,'time-0-text')]/ancestor::div[contains(@class,'c-selectable-wrapper')]");
    public static By completeBookingButton = By.id("funnel-complete-button");
    public static By thankYouTitle = By.id("thank-you-title");
    public static By dateSection = By.xpath("//*[contains(@class,'dates-and-time')]/..");
    public static By timeSection = By.xpath("//*[contains(@class,'times-wrapper')]");

    public void loginUsingPhoneNumberAndOtp(String phoneNumber , String otp) throws InterruptedException {
        inputDataMethod(phoneNumberInputField,phoneNumber);
        clickMethod(otpSendButton);
        enterOtp(otpInnerFields , otp);
    }
    public  void clickMethod(By locator)
    {
        this.wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.driver.findElement(locator).click();
    }

    public void inputDataMethod(By locator , String value)
    {
        this.driver.findElement(locator).sendKeys(value);
    }

   public void enterOtp(By locator , String otp) throws InterruptedException {
       List<WebElement> inputFields = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
       for (int i = 0; i < otp.length(); i++) {
           WebElement inputField = inputFields.get(i);
//           wait.until(ExpectedConditions.elementToBeClickable(inputField));
           Thread.sleep(2000);
           inputField.sendKeys(String.valueOf(otp.charAt(i)));
       }
   }

   public void selectBookingType(String type)
   {
       if(! type.equalsIgnoreCase("one time"))
       {
           if(type.equalsIgnoreCase("weekly"))
           {
               this.driver.findElement(weeklyOption).click();
           }
       }
       this.driver.findElement(frequencyButton).click();
   }

   public void selectLocation(String location) throws InterruptedException {
       this.wait.until(ExpectedConditions.elementToBeClickable(locationInput));
       this.driver.findElement(locationInput).sendKeys(location);
       this.wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(locationSelection)));
       this.driver.findElement(locationSelection).click();
       this.wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(mapLocationButton)));
       this.driver.findElement(mapLocationButton).click();
       Thread.sleep(2000);
//       this.wait.until(ExpectedConditions.elementToBeClickable(funnelNextButton));
       WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='default-button-text' and text()='Next']")));
       button.click();
   }

    public void numberOfCleaner(String numberOfCleaner )  {
        clickOnParticularElementBasedOnText(professionalSelector , numberOfCleaner);
    }

    public void numberOfHrs(String numberOfHrs)
    {
        clickOnParticularElementBasedOnText(hrsNumber , numberOfHrs);
    }

    public void clickOnParticularElementBasedOnText(By locator , String text)
    {
        List<WebElement> elements = driver.findElements(locator);
        String requiredText = text;
        for (WebElement element : elements) {
            String elementText = element.getText();  // Get the text of the element
            if (elementText.equals(requiredText)) {
                element.click();
                break;
            }
        }
    }

    public void scrollToParticularLocation(By locator)
    {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void suggestedCleaner(int suggestedCleaner) throws InterruptedException {
        try {
            if(this.driver.findElement(By.xpath("//*[@id='professionals']/descendant::*[contains(@class,'professional-card')]")).isDisplayed())
            {
                this.driver.findElement(By.xpath("//*[@id='professionals']/descendant::*[contains(@class,'professional-card')]["+(suggestedCleaner+1)+"]/img[@alt='Professional']")).click();
                try {
                    if(this.driver.findElement(professionalHistoryClose).isDisplayed())
                    {
                        this.driver.findElement(professionalHistoryClose).click();
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Professional div is not present");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Section is not visible");
        }
    }

    public void paymentMethod()
    {
        this.wait.until(ExpectedConditions.elementToBeClickable(completeBookingButton));
        this.driver.findElement(completeBookingButton).click();
        this.wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(thankYouTitle)));
        String getText = this.driver.findElement(thankYouTitle).getText();
        Assert.assertTrue(getText.contains("Order Placed"));
    }

    public void selectMaterialSection(String materialSection) throws InterruptedException {
        if(materialSection.equalsIgnoreCase("Yes"))
        {
            this.driver.findElement(cleaningMaterial).click();
        }
        this.driver.findElement(funnelNextButton).click();
    }

    public void dateTimeSelection(String date , String time) throws InterruptedException {
        if(date.equalsIgnoreCase("first available") && time.equalsIgnoreCase("first available"))
        {
            scrollToParticularLocation(dateSection);
            this.driver.findElement(firstAvailableDate).click();
            scrollToParticularLocation(timeSection);
            this.driver.findElement(firsAvailableTime).click();
        }
        else {
            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(1);
            int tomorrowDay = tomorrow.getDayOfMonth();
            scrollToParticularLocation(dateSection);
            this.driver.findElement(By.xpath("//*[@id='single-date-picker-inside']//*[contains(@id,'enabled-day') and text()='"+tomorrowDay+"']")).click();
            scrollToParticularLocation(timeSection);
            this.driver.findElement(By.xpath("//span[contains(@id,'time') and contains(text(),'"+time+"')]")).click();
        }
        this.driver.findElement(funnelNextButton).click();
        loginUsingPhoneNumberAndOtp(dataLoader.getPhoneNumber() , dataLoader.getOtp());
    }

}
