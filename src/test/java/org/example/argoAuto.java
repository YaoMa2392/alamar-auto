package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class argoAuto {
    private WebDriver driver;
    private final String BASE_URL = "https://alamarbio.com/products-and-services/argo-system/";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testHomePageTitle() {
        driver.get(BASE_URL);
        String expectedTitle = "ARGO™ HT System | Automated Proteomics System";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle), "Home page title does not match!");
    }

    @Test
    public void testImmBtn() {
        try {
            driver.get("https://alamarbio.com/contact-us/");

            // Select an option in "How can we help you" dropdown
            WebElement helpSelect = driver.findElement(By.id("1024761_268344pi_1024761_268344"));
            Select helpDropdown = new Select(helpSelect);
            helpDropdown.selectByVisibleText("Request quote");

            // Fill in First Name
            WebElement firstNameInput = driver.findElement(By.id("1024761_267339pi_1024761_267339"));
            firstNameInput.sendKeys("Yao");

            // Fill in Last Name
            WebElement lastNameInput = driver.findElement(By.id("1024761_267342pi_1024761_267342"));
            lastNameInput.sendKeys("Ma");

            // Fill in Company/Institute
            WebElement companyInput = driver.findElement(By.id("1024761_267345pi_1024761_267345"));
            companyInput.sendKeys("Alamar Biosciences");

            // Fill in Title
            WebElement titleInput = driver.findElement(By.id("1024761_267348pi_1024761_267348"));
            titleInput.sendKeys("Software Test Engineer");

            // Fill in Email
            WebElement emailInput = driver.findElement(By.id("1024761_267363pi_1024761_267363"));
            emailInput.sendKeys("yao.ngsde@gmail.com");

            // Fill in Phone
            WebElement phoneInput = driver.findElement(By.id("1024761_267366pi_1024761_267366"));
            phoneInput.sendKeys("5513315704");

            // Select Country
            WebElement countrySelect = driver.findElement(By.id("1024761_268347pi_1024761_268347"));
            Select countryDropdown = new Select(countrySelect);
            countryDropdown.selectByValue("4026411"); // United States

            // Select State
            WebElement stateSelect = driver.findElement(By.id("1024761_302634pi_1024761_302634"));
            Select stateDropdown = new Select(stateSelect);
            stateDropdown.selectByVisibleText("CA");

            // Fill in "How can we help?"
            WebElement howHelpInput = driver.findElement(By.id("1024761_267369pi_1024761_267369"));
            howHelpInput.sendKeys("Wish you a great day!");

            // Wait for 10 seconds to see the form filled
            Thread.sleep(10000);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred in testImmBtn: " + e.getMessage());
        }
    }

    @Test
    public void testFillForm() {
        try {
            driver.get(BASE_URL);

            // Locate the "Request a Quote" button and click
            WebElement button = driver.findElement(By.xpath("//a[@href='https://alamarbio.com/contact-us/']//span[contains(text(),'request a Quote')]"));
            Assert.assertNotNull(button, "Button Not Found");
            button.click();

            // Wait until the page's <h1> element with the text "Contact us" is visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(@class, 'elementor-heading-title') and contains(text(), 'Contact us')]")));

            // Verify page title
            String expectedTitle = "A Silicon Valley Proteomics Startup Company: Alamar Biosciences Inc.";
            String actualTitle = driver.getTitle();
            Assert.assertTrue(actualTitle.contains(expectedTitle), "Quote page title does not match!");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred in testForm: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
