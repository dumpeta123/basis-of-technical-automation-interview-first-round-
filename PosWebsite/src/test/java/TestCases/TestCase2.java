package TestCases;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCase2 {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Downloads\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Open the webpage
        driver.get("https://www.pos.com.my/");

         driver.findElement(By.xpath("//img[@alt=\"Pos Close Alert Icon\"]")).click();

        // Locate the element to mouseover

       // WebElement elementToHover = driver.findElement(By.xpath("//*[@_ngcontent-aqp-c23=\"\" and @_nghost-aqp-c19=\"\"]//following::span[text()=\"Send \" and @class=\"floating-option-text\"]"));
        WebElement elementToHover = driver.findElement(By.xpath("(//span[text()=\"Send \"])[2]"));
        Actions actions = new Actions(driver);

        // Perform mouseover action
        actions.moveToElement(elementToHover).perform();

        WebElement dropdownOption = driver.findElement(By.xpath("//span[normalize-space()='Parcel']"));

        // Click on the dropdown option
        actions.moveToElement(dropdownOption).click().perform();

        WebElement element = driver.findElement(By.xpath("//*[@class=\"col-md-4\"]"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        WebElement create_Shipment = driver.findElement(By.xpath("//*[text()=\"Create shipment now\"]"));
       create_Shipment.click();

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("send.pos.com.my/home/e-connote?lg=en")) {

            System.out.println("Test Passed: URL contains 'https://send.pos.com.my/home/e-connote?lg=en'");
        } else {
            System.out.println("Test Failed: URL does not contain 'https://send.pos.com.my/home/e-connote?lg=en'");
        }


        // Verify Sender Info section
        WebElement senderInfoSection = driver.findElement(By.id("stepSender"));
        if (senderInfoSection.isDisplayed()) {
            System.out.println("Sender Info section is displayed.");
        } else {
            System.out.println("Sender Info section is NOT displayed.");
        }

        // Verify Receiver Info section
        WebElement receiverInfoSection = driver.findElement(By.id("stepReceiver"));
        if (receiverInfoSection.isDisplayed()) {
            System.out.println("Receiver Info section is displayed.");
        } else {
            System.out.println("Receiver Info section is NOT displayed.");
        }

        // Verify Parcel Info section
        WebElement parcelInfoSection = driver.findElement(By.id("stepParcel"));
        if (parcelInfoSection.isDisplayed()) {
            System.out.println("Parcel Info section is displayed.");
        } else {
            System.out.println("Parcel Info section is NOT displayed.");
        }

        // Verify Summary section
        WebElement summarySection = driver.findElement(By.id("stepSummary"));
        if (summarySection.isDisplayed()) {
            System.out.println("Summary section is displayed.");
        } else {
            System.out.println("Summary section is NOT displayed.");
        }

        driver.quit();
    }
}
