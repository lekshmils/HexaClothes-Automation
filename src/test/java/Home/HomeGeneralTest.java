package Home;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeGeneralTest {

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

    By heroParagraph = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div/div/div/div[1]/p");

    By heroImage1 = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div/div/div/div[2]/img");

    By heroImage2 = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div[2]/div/div/div/div[3]/div/div/div/div[2]/img");

    By exploreNow = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[3]/div[2]/div[2]/div/div[2]/button");

    By productImage = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[3]/div[2]/div[1]/img");

    By productTitle = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[3]/div[2]/div[1]/div/h3");

    By addToCart = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[3]/div[2]/div[1]/div/div[2]/button");

    By productRating = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[3]/div[2]/div[1]/div/div[1]/span");

    By loginButton = By.xpath(
            "//*[@id=\"root\"]/div[1]/nav/div[2]/a[3]/button");

    By cartLink = By.xpath(
            "//*[@id=\"root\"]/div[1]/nav/div[2]/a[2]");

    By pageNotFound = By.xpath(
            "/html/body/div/div/h1");


    // =========================================================
    // BEFORE CLASS
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
        System.out.println("Same Chrome will be used for all tests");
        System.out.println("========================================");
    }


    // =========================================================
    // BEFORE METHOD
    // RETURN TO HOME
    //
    // IMPORTANT:
    // This DOES NOT open Chrome again.
    // =========================================================

    @BeforeMethod
    public void returnToHome() {

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        homeHeading));
    }


    // =========================================================
    // HOME_01
    // =========================================================

    @Test(priority = 1)
    public void verifyHomePageLoaded() {

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl,
                "Home page URL is incorrect");

        System.out.println("HOME_01: PASS");
    }


    // =========================================================
    // HOME_02
    // =========================================================

    @Test(priority = 2)
    public void verifyHomeHeading() {

        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        homeHeading));

        Assert.assertTrue(
                heading.isDisplayed(),
                "Home heading is not displayed");

        System.out.println("HOME_02: PASS");
    }


    // =========================================================
    // HOME_03
    // =========================================================

    @Test(priority = 3)
    public void verifyHeroParagraph() {

        WebElement paragraph = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        heroParagraph));

        Assert.assertTrue(
                paragraph.isDisplayed(),
                "Hero paragraph is not displayed");

        Assert.assertFalse(
                paragraph.getText().trim().isEmpty(),
                "Hero paragraph is empty");

        System.out.println("HOME_03: PASS");
    }


    // =========================================================
    // HOME_04
    // =========================================================

    @Test(priority = 4)
    public void verifyFirstHeroImage() {

        WebElement image = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        heroImage1));

        Assert.assertTrue(
                image.isDisplayed(),
                "First hero image is not displayed");

        System.out.println("HOME_04: PASS");
    }


    // =========================================================
    // HOME_05
    // =========================================================

    @Test(priority = 5)
    public void verifySecondHeroImage() {

        WebElement image = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        heroImage2));

        Assert.assertTrue(
                image.isDisplayed(),
                "Second hero image is not displayed");

        System.out.println("HOME_05: PASS");
    }


    // =========================================================
    // HOME_06
    // =========================================================

    @Test(priority = 6)
    public void verifyExploreNow() {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        exploreNow));

        Assert.assertTrue(
                button.isDisplayed(),
                "Explore Now button is not displayed");

        System.out.println("HOME_06: PASS");
    }


    // =========================================================
    // HOME_07
    // TOP RATED
    // =========================================================

    @Test(priority = 7)
    public void verifyTopRatedNavigation() {

        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(
                        topRated));

        link.click();

        wait.until(
                ExpectedConditions.urlContains("/tr"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/tr"),
                "Top Rated page did not open");

        System.out.println("HOME_07: PASS");
    }


    // =========================================================
    // HOME_08
    // KIDS WEAR
    // =========================================================

    @Test(priority = 8)
    public void verifyKidsWearNavigation() {

        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(
                        kidsWear));

        link.click();

        wait.until(
                ExpectedConditions.urlContains("/kids"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/kids"),
                "Kids Wear page did not open");

        System.out.println("HOME_08: PASS");
    }


    // =========================================================
    // HOME_09
    // MEN'S WEAR
    // =========================================================

    @Test(priority = 9)
    public void verifyMensWearNavigation() {

        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(
                        mensWear));

        link.click();

        wait.until(
                ExpectedConditions.urlContains("/mens"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/mens"),
                "Men's Wear page did not open");

        System.out.println("HOME_09: PASS");
    }


    // =========================================================
    // HOME_10
    // WOMEN'S WEAR
    // =========================================================

    @Test(priority = 10)
    public void verifyWomensWearNavigation() {

        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(
                        womensWear));

        link.click();

        wait.until(
                ExpectedConditions.urlContains("/women"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/women"),
                "Women's Wear page did not open");

        System.out.println("HOME_10: PASS");
    }


    // =========================================================
    // HOME_11
    // ALL PRODUCTS
    // =========================================================

    @Test(priority = 11)
    public void verifyAllProductsNavigation() {

        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(
                        allProducts));

        link.click();

        wait.until(
                ExpectedConditions.urlContains("/all"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/all"),
                "All Products page did not open");

        System.out.println("HOME_11: PASS");
    }


    // =========================================================
    // HOME_12
    // =========================================================

    @Test(priority = 12)
    public void verifyProductImage() {

        WebElement image = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productImage));

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        image);

        Assert.assertTrue(
                image.isDisplayed(),
                "Product image is not displayed");

        System.out.println("HOME_12: PASS");
    }


    // =========================================================
    // HOME_13
    // =========================================================

    @Test(priority = 13)
    public void verifyProductTitle() {

        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productTitle));

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        title);

        Assert.assertTrue(
                title.isDisplayed(),
                "Product title is not displayed");

        Assert.assertFalse(
                title.getText().trim().isEmpty(),
                "Product title is empty");

        System.out.println("HOME_13: PASS");
    }


    // =========================================================
    // HOME_14
    // =========================================================

    @Test(priority = 14)
    public void verifyAddToCartButton() {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        addToCart));

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        button);

        Assert.assertTrue(
                button.isDisplayed(),
                "Add to Cart button is not displayed");

        System.out.println("HOME_14: PASS");
    }


    // =========================================================
    // HOME_15
    // =========================================================

    @Test(priority = 15)
    public void verifyProductRating() {

        WebElement rating = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productRating));

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        rating);

        Assert.assertTrue(
                rating.isDisplayed(),
                "Product rating is not displayed");

        Assert.assertFalse(
                rating.getText().trim().isEmpty(),
                "Product rating is empty");

        System.out.println("HOME_15: PASS");
    }


    // =========================================================
    // HOME_16
    // =========================================================

    @Test(priority = 16)
    public void verifyLoginButton() {

        WebElement login = wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginButton));

        Assert.assertTrue(
                login.isDisplayed(),
                "Login button is not displayed");

        System.out.println("HOME_16: PASS");
    }


    // =========================================================
    // HOME_17
    // =========================================================

    @Test(priority = 17)
    public void verifyCartIcon() {

        WebElement cart = wait.until(
                ExpectedConditions.elementToBeClickable(
                        cartLink));

        Assert.assertTrue(
                cart.isDisplayed(),
                "Cart icon is not displayed");

        System.out.println("HOME_17: PASS");
    }


    // =========================================================
    // HOME_18
    // HOME ICON NAVIGATION
    // =========================================================

    @Test(priority = 18)
    public void verifyHomeIconNavigation() {

        WebElement home = wait.until(
                ExpectedConditions.elementToBeClickable(
                        homeLink));

        home.click();

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        homeHeading));

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl,
                "Home icon does not open Home page");

        System.out.println("HOME_18: PASS");
    }


    // =========================================================
    // HOME_19
    // HOME REFRESH
    // =========================================================

    @Test(priority = 19)
    public void verifyHomePageAfterRefresh() {

        driver.navigate().refresh();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        homeHeading));

        Assert.assertTrue(
                driver.findElement(homeHeading).isDisplayed(),
                "Home page is not displayed after refresh");

        System.out.println("HOME_19: PASS");
    }


    // =========================================================
    // HOME_20
    // TOP RATED REFRESH
    // =========================================================

    @Test(priority = 20)
    public void verifyTopRatedAfterRefresh() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        topRated)).click();

        wait.until(
                ExpectedConditions.urlContains("/tr"));

        driver.navigate().refresh();

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        pageNotFound));

        Assert.assertFalse(
                error.isDisplayed(),
                "Top Rated page shows Page Not Found after refresh");

        System.out.println("HOME_20: PASS");
    }


    // =========================================================
    // HOME_21
    // KIDS WEAR REFRESH
    // =========================================================

    @Test(priority = 21)
    public void verifyKidsWearAfterRefresh() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        kidsWear)).click();

        wait.until(
                ExpectedConditions.urlContains("/kids"));

        driver.navigate().refresh();

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        pageNotFound));

        Assert.assertFalse(
                error.isDisplayed(),
                "Kids Wear page shows Page Not Found after refresh");

        System.out.println("HOME_21: PASS");
    }


    // =========================================================
    // HOME_22
    // MEN'S WEAR REFRESH
    // =========================================================

    @Test(priority = 22)
    public void verifyMensWearAfterRefresh() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        mensWear)).click();

        wait.until(
                ExpectedConditions.urlContains("/mens"));

        driver.navigate().refresh();

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        pageNotFound));

        Assert.assertFalse(
                error.isDisplayed(),
                "Men's Wear page shows Page Not Found after refresh");

        System.out.println("HOME_22: PASS");
    }


    // =========================================================
    // HOME_23
    // WOMEN'S WEAR REFRESH
    // =========================================================

    @Test(priority = 23)
    public void verifyWomensWearAfterRefresh() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        womensWear)).click();

        wait.until(
                ExpectedConditions.urlContains("/women"));

        driver.navigate().refresh();

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        pageNotFound));

        Assert.assertFalse(
                error.isDisplayed(),
                "Women's Wear page shows Page Not Found after refresh");

        System.out.println("HOME_23: PASS");
    }


    // =========================================================
    // HOME_24
    // ALL PRODUCTS REFRESH
    // =========================================================

    @Test(priority = 24)
    public void verifyAllProductsAfterRefresh() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        allProducts)).click();

        wait.until(
                ExpectedConditions.urlContains("/all"));

        driver.navigate().refresh();

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        pageNotFound));

        Assert.assertFalse(
                error.isDisplayed(),
                "All Products page shows Page Not Found after refresh");

        System.out.println("HOME_24: PASS");
    }


    // =========================================================
    // AFTER CLASS
    // CHROME CLOSES ONLY ONCE
    // =========================================================

    @AfterClass
    public void tearDown() {

        if (driver != null) {

            driver.quit();
        }

        System.out.println("========================================");
        System.out.println("All 24 Home Functional tests completed");
        System.out.println("Same Chrome browser was used");
        System.out.println("Chrome browser closed successfully");
        System.out.println("========================================");
    }
}