package Home;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomeVerificationTest {

    WebDriver driver;
    WebDriverWait wait;

    String baseUrl = "https://hexaclothes.netlify.app/";

    // =========================================================
    // LOCATORS
    // =========================================================

    By homeLink = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[1]/ul/li[1]/a");

    By topRated = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[1]/ul/li[2]/a");

    By kidsWear = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[1]/ul/li[3]/a");

    By mensWear = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[1]/ul/li[4]/a");

    By womensWear = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[1]/ul/li[5]/a");

    By allProducts = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[1]/ul/li[6]/a");

    By homeHeading = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div/div/div/div[1]/h1");

    By pageNotFound = By.xpath(
            "/html/body/div/div/h1");


    // =========================================================
    // SETUP
    // CHROME OPENS ONLY ONCE
    // =========================================================

    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20));

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        homeHeading));

        System.out.println("========================================");
        System.out.println("Chrome browser opened");
        System.out.println("Home page opened");
        System.out.println("All tests will use SAME Chrome");
        System.out.println("========================================");
    }


    // =========================================================
    // HOME_VERIFICATION_01
    // Verify Top Rated Navigation
    // =========================================================

    @Test(priority = 1)
    public void verifyTopRatedNavigation() {

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        topRated))
                .click();

        wait.until(
                ExpectedConditions.urlContains("/tr"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/tr"),
                "Top Rated page was not opened");

        System.out.println(
                "HOME_VERIFICATION_01: Top Rated verified");
    }


    // =========================================================
    // HOME_VERIFICATION_02
    // Verify Kids Wear Navigation
    // =========================================================

    @Test(priority = 2)
    public void verifyKidsWearNavigation() {

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        kidsWear))
                .click();

        wait.until(
                ExpectedConditions.urlContains("/kids"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/kids"),
                "Kids Wear page was not opened");

        System.out.println(
                "HOME_VERIFICATION_02: Kids Wear verified");
    }


    // =========================================================
    // HOME_VERIFICATION_03
    // Verify Men's Wear Navigation
    // =========================================================

    @Test(priority = 3)
    public void verifyMensWearNavigation() {

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        mensWear))
                .click();

        wait.until(
                ExpectedConditions.urlContains("/mens"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/mens"),
                "Men's Wear page was not opened");

        System.out.println(
                "HOME_VERIFICATION_03: Men's Wear verified");
    }


    // =========================================================
    // HOME_VERIFICATION_04
    // Verify Women's Wear Navigation
    // =========================================================

    @Test(priority = 4)
    public void verifyWomensWearNavigation() {

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        womensWear))
                .click();

        wait.until(
                ExpectedConditions.urlContains("/women"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/women"),
                "Women's Wear page was not opened");

        System.out.println(
                "HOME_VERIFICATION_04: Women's Wear verified");
    }


    // =========================================================
    // HOME_VERIFICATION_05
    // Verify All Products Navigation
    // =========================================================

    @Test(priority = 5)
    public void verifyAllProductsNavigation() {

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        allProducts))
                .click();

        wait.until(
                ExpectedConditions.urlContains("/all"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/all"),
                "All Products page was not opened");

        System.out.println(
                "HOME_VERIFICATION_05: All Products verified");
    }


    // =========================================================
    // HOME_VERIFICATION_06
    // Verify Home Icon
    // =========================================================

    @Test(priority = 6)
    public void verifyHomeIconNavigation() {

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        homeLink))
                .click();

        wait.until(
                ExpectedConditions.urlContains(
                        "hexaclothes.netlify.app"));

        /*
         * Expected:
         * Home icon should open Home page.
         *
         * Actual:
         * According to manual testing, Shopping Cart opens.
         *
         * Therefore this test will FAIL
         * while the bug exists.
         */

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl,
                "Home icon opens Shopping Cart instead of Home");

        System.out.println(
                "HOME_VERIFICATION_06: Home icon verified");
    }


    // =========================================================
    // HOME_VERIFICATION_07
    // Verify Home Page Refresh
    // =========================================================

    @Test(priority = 7)
    public void verifyHomeRefresh() {

        driver.get(baseUrl);

        driver.navigate().refresh();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        homeHeading));

        Assert.assertTrue(
                driver.findElement(
                        homeHeading)
                        .isDisplayed(),
                "Home page not displayed after refresh");

        System.out.println(
                "HOME_VERIFICATION_07: Home refresh verified");
    }


    // =========================================================
    // HOME_VERIFICATION_08
    // Verify Top Rated Refresh
    // =========================================================

    @Test(priority = 8)
    public void verifyTopRatedRefresh() {

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        topRated))
                .click();

        wait.until(
                ExpectedConditions.urlContains("/tr"));

        driver.navigate().refresh();

        /*
         * Expected:
         * Top Rated page should remain available.
         *
         * Actual:
         * Page Not Found appears.
         *
         * Therefore this test will FAIL.
         */

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        pageNotFound));

        Assert.assertFalse(
                driver.findElement(
                        pageNotFound)
                        .isDisplayed(),
                "Page Not Found displayed after Top Rated refresh");

        System.out.println(
                "HOME_VERIFICATION_08: Top Rated refresh verified");
    }


    // =========================================================
    // HOME_VERIFICATION_09
    // Verify Kids Wear Refresh
    // =========================================================

    @Test(priority = 9)
    public void verifyKidsWearRefresh() {

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        kidsWear))
                .click();

        wait.until(
                ExpectedConditions.urlContains("/kids"));

        driver.navigate().refresh();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        pageNotFound));

        Assert.assertFalse(
                driver.findElement(
                        pageNotFound)
                        .isDisplayed(),
                "Page Not Found displayed after Kids Wear refresh");

        System.out.println(
                "HOME_VERIFICATION_09: Kids Wear refresh verified");
    }


    // =========================================================
    // HOME_VERIFICATION_10
    // Verify Men's Wear Refresh
    // =========================================================

    @Test(priority = 10)
    public void verifyMensWearRefresh() {

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        mensWear))
                .click();

        wait.until(
                ExpectedConditions.urlContains("/mens"));

        driver.navigate().refresh();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        pageNotFound));

        Assert.assertFalse(
                driver.findElement(
                        pageNotFound)
                        .isDisplayed(),
                "Page Not Found displayed after Men's Wear refresh");

        System.out.println(
                "HOME_VERIFICATION_10: Men's Wear refresh verified");
    }


    // =========================================================
    // HOME_VERIFICATION_11
    // Verify Women's Wear Refresh
    // =========================================================

    @Test(priority = 11)
    public void verifyWomensWearRefresh() {

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        womensWear))
                .click();

        wait.until(
                ExpectedConditions.urlContains("/women"));

        driver.navigate().refresh();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        pageNotFound));

        Assert.assertFalse(
                driver.findElement(
                        pageNotFound)
                        .isDisplayed(),
                "Page Not Found displayed after Women's Wear refresh");

        System.out.println(
                "HOME_VERIFICATION_11: Women's Wear refresh verified");
    }


    // =========================================================
    // HOME_VERIFICATION_12
    // Verify All Products Refresh
    // =========================================================

    @Test(priority = 12)
    public void verifyAllProductsRefresh() {

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        allProducts))
                .click();

        wait.until(
                ExpectedConditions.urlContains("/all"));

        driver.navigate().refresh();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        pageNotFound));

        Assert.assertFalse(
                driver.findElement(
                        pageNotFound)
                        .isDisplayed(),
                "Page Not Found displayed after All Products refresh");

        System.out.println(
                "HOME_VERIFICATION_12: All Products refresh verified");
    }


    // =========================================================
    // TEARDOWN
    // CHROME CLOSES ONLY ONCE
    // =========================================================

    @AfterClass
    public void tearDown() {

        if (driver != null) {

            driver.quit();
        }

        System.out.println("========================================");
        System.out.println("All 12 Home Verification tests completed");
        System.out.println("Same Chrome browser was used");
        System.out.println("Chrome browser closed successfully");
        System.out.println("========================================");
    }
}