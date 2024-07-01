package TestCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCase1 {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Downloads\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Open the webpage
        driver.get("https://www.pos.com.my/");


        //close Website image
        driver.findElement(By.xpath("//img[@alt=\"Pos Close Alert Icon\"]")).click();

        WebElement element = driver.findElement(By.xpath("//h1[@class=\"home-title ng-tns-c47-0\"]"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        WebElement buy_Insurance = driver.findElement(By.xpath("//a[@href=\"https://insurance.pos.com.my\"]"));
        buy_Insurance.click();

        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(windowHandles.get(1));

        String newTabUrl = driver.getCurrentUrl();

        // Verify that the URL contains "insurance.p"
        if (newTabUrl.contains("insurance.pos.com.my")) {
            System.out.println("Test Passed: URL contains 'https://insurance.pos.com.my'");
        } else {
            System.out.println("Test Failed: URL does not contain 'https://insurance.pos.com.my'");
        }

        // Locate the "I drive a car" button and click it
        WebElement driveCarButton = driver.findElement(By.xpath("//*[text()=\"I drive a car\"]"));
        if (driveCarButton.isDisplayed() && driveCarButton.isEnabled()) {
            driveCarButton.click();
            System.out.println("'I drive a car' button clicked.");
        } else {
            System.out.println("'I drive a car' button is not clickable.");
        }

        // Verify the new section appears for clicking "I drive a car"
        verifyNewSection(driver);

        // Refresh the page or navigate back to reset the state
        driver.navigate().refresh();

        // Locate the "I ride a motorcycle" button and click it
        WebElement rideMotorcycleButton = driver.findElement(By.xpath("//*[@class=\"content d-block ms-5 medium\"]"));
        if (rideMotorcycleButton.isDisplayed() && rideMotorcycleButton.isEnabled()) {
            rideMotorcycleButton.click();
            System.out.println("'I ride a motorcycle' button clicked.");
        } else {
            System.out.println("'I ride a motorcycle' button is not clickable.");
        }

        // Verify the new section appears ""I ride a motorcycle"
        verifyNewSection(driver);

        driver.quit();
    }

    private static void verifyNewSection(WebDriver driver) {

        WebElement newSection = driver.findElement(By.xpath("//*[contains(text(), \"Ok, let's get to know you\")]"));
        if (newSection.isDisplayed()) {
            System.out.println("New section 'Ok, let's get to know you' is displayed.");

            // Locate and verify the five fields
            for (int i = 1; i <= 5; i++) {
                WebElement field = driver.findElement(By.xpath("//*[@class=\"mb-3 row\"]//child::div[" + i + "]"));
                if (field.isDisplayed()) {
                    System.out.println("Field " + i + " is displayed.");
                } else {
                    System.out.println("Field " + i + " is not displayed.");
                }
            }
        } else {
            System.out.println("New section 'Ok, let's get to know you' is not displayed.");
        }

    }
}
