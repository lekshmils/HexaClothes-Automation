package Home;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeValidationTest {

    WebDriver driver;
    WebDriverWait wait;

    String baseUrl = "https://hexaclothes.netlify.app/";

    // =========================================================
    // LOCATORS
    // =========================================================

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

    By productRating = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[3]/div[2]/div[1]/div/div[1]/span");

    By addToCart = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[3]/div[2]/div[1]/div/div[2]/button");

    By loginButton = By.xpath(
            "//*[@id=\"root\"]/div[1]/nav/div[2]/a[3]/button");

    By cartLink = By.xpath(
            "//*[@id=\"root\"]/div[1]/nav/div[2]/a[2]");


    // =========================================================
    // SETUP
    // =========================================================

    @BeforeMethod
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
    }


    // =========================================================
    // HOME_VALIDATION_01
    // Verify Home Page URL
    // =========================================================

    @Test(priority = 1)
    public void validateHomePageURL() {

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl,
                "Incorrect Home page URL");

        System.out.println(
                "HOME_VALIDATION_01: URL verified");
    }


    // =========================================================
    // HOME_VALIDATION_02
    // Verify Home Page Heading
    // =========================================================

    @Test(priority = 2)
    public void validateHomeHeading() {

        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        homeHeading));

        Assert.assertTrue(
                heading.isDisplayed(),
                "Home heading is not displayed");

        Assert.assertFalse(
                heading.getText().trim().isEmpty(),
                "Home heading is empty");

        System.out.println(
                "HOME_VALIDATION_02: Heading verified");
    }


    // =========================================================
    // HOME_VALIDATION_03
    // Verify Hero Content
    // =========================================================

    @Test(priority = 3)
    public void validateHeroContent() {

        WebElement paragraph = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        heroParagraph));

        Assert.assertTrue(
                paragraph.isDisplayed(),
                "Hero content is not displayed");

        Assert.assertFalse(
                paragraph.getText().trim().isEmpty(),
                "Hero content is empty");

        System.out.println(
                "HOME_VALIDATION_03: Hero content verified");
    }


    // =========================================================
    // HOME_VALIDATION_04
    // Verify Hero Images
    // =========================================================

    @Test(priority = 4)
    public void validateHeroImages() {

        WebElement image1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        heroImage1));

        WebElement image2 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        heroImage2));

        Assert.assertTrue(
                image1.isDisplayed(),
                "First hero image is not displayed");

        Assert.assertTrue(
                image2.isDisplayed(),
                "Second hero image is not displayed");

        System.out.println(
                "HOME_VALIDATION_04: Hero images verified");
    }


    // =========================================================
    // HOME_VALIDATION_05
    // Verify Explore Now
    // =========================================================

    @Test(priority = 5)
    public void validateExploreNow() {

        WebElement button = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        exploreNow));

        Assert.assertTrue(
                button.isDisplayed(),
                "Explore Now button is not displayed");

        Assert.assertTrue(
                button.isEnabled(),
                "Explore Now button is disabled");

        System.out.println(
                "HOME_VALIDATION_05: Explore Now verified");
    }


    // =========================================================
    // HOME_VALIDATION_06
    // Verify Product Image
    // =========================================================

    @Test(priority = 6)
    public void validateProductImage() {

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

        System.out.println(
                "HOME_VALIDATION_06: Product image verified");
    }


    // =========================================================
    // HOME_VALIDATION_07
    // Verify Product Title
    // =========================================================

    @Test(priority = 7)
    public void validateProductTitle() {

        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productTitle));

        Assert.assertTrue(
                title.isDisplayed(),
                "Product title is not displayed");

        Assert.assertFalse(
                title.getText().trim().isEmpty(),
                "Product title is empty");

        System.out.println(
                "HOME_VALIDATION_07: Product title verified");
    }


    // =========================================================
    // HOME_VALIDATION_08
    // Verify Product Rating
    // =========================================================

    @Test(priority = 8)
    public void validateProductRating() {

        WebElement rating = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productRating));

        Assert.assertTrue(
                rating.isDisplayed(),
                "Product rating is not displayed");

        System.out.println(
                "HOME_VALIDATION_08: Product rating verified");
    }


    // =========================================================
    // HOME_VALIDATION_09
    // Verify Add To Cart Button
    // =========================================================

    @Test(priority = 9)
    public void validateAddToCartButton() {

        WebElement button = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        addToCart));

        Assert.assertTrue(
                button.isDisplayed(),
                "Add to Cart button is not displayed");

        Assert.assertTrue(
                button.isEnabled(),
                "Add to Cart button is disabled");

        System.out.println(
                "HOME_VALIDATION_09: Add to Cart verified");
    }


    // =========================================================
    // HOME_VALIDATION_10
    // Verify Login Button
    // =========================================================

    @Test(priority = 10)
    public void validateLoginButton() {

        WebElement login = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        loginButton));

        Assert.assertTrue(
                login.isDisplayed(),
                "Login button is not displayed");

        Assert.assertTrue(
                login.isEnabled(),
                "Login button is disabled");

        System.out.println(
                "HOME_VALIDATION_10: Login verified");
    }


    // =========================================================
    // HOME_VALIDATION_11
    // Verify Cart
    // =========================================================

    @Test(priority = 11)
    public void validateCart() {

        WebElement cart = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        cartLink));

        Assert.assertTrue(
                cart.isDisplayed(),
                "Cart icon is not displayed");

        System.out.println(
                "HOME_VALIDATION_11: Cart verified");
    }


    // =========================================================
    // HOME_VALIDATION_12
    // Verify Home Page After Refresh
    // =========================================================

    @Test(priority = 12)
    public void validateHomeAfterRefresh() {

        driver.navigate().refresh();

        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        homeHeading));

        Assert.assertTrue(
                heading.isDisplayed(),
                "Home page is not displayed after refresh");

        System.out.println(
                "HOME_VALIDATION_12: Refresh verified");
    }


    // =========================================================
    // TEARDOWN
    // =========================================================

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}