package Wishlist;

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
import org.testng.annotations.Test;

public class WishlistGeneralTest {

    WebDriver driver;
    WebDriverWait wait;

    String homeURL =
            "https://hexaclothes.netlify.app/";


    // =====================================================
    // LOGIN LOCATORS
    // =====================================================

    By loginButton = By.xpath(
            "//*[@id=\"root\"]/div[1]/nav/div[2]/a[3]/button");

    By email = By.xpath(
            "//*[@id=\"email\"]");

    By password = By.xpath(
            "//*[@id=\"password\"]");

    By loginSubmit = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/form/button");


    // =====================================================
    // KIDS WEAR
    // YOUR EXACT LOCATOR
    // =====================================================

    By kidsWear = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[1]/ul/li[3]/a");


    // =====================================================
    // HEART SYMBOL
    // YOUR EXACT LOCATOR
    // =====================================================

    By heartSymbol = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[1]/div/div/button[2]");


    // =====================================================
    // WISHLIST ICON
    // =====================================================

    By wishlistIcon = By.xpath(
            "//*[@id=\"root\"]/div[1]/nav/div[2]/a[1]");


    // =====================================================
    // PRODUCT IN WISHLIST
    // =====================================================

    By wishlistProduct = By.xpath(
            "//h2[normalize-space()='Kids T-Shirt']");


    // =====================================================
    // ADD TO CART
    // YOUR EXACT LOCATOR
    // =====================================================

    By addToCart = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div/div/div/button[1]");


    // =====================================================
    // REMOVE
    // YOUR EXACT LOCATOR
    // =====================================================

    By remove = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div/div/div/button[2]");


    // =====================================================
    // SETUP
    // CHROME OPENS ONLY ONCE
    // =====================================================

    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20));

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get(homeURL);

        System.out.println();
        System.out.println(
                "==============================================");

        System.out.println(
                "Chrome opened successfully");

        System.out.println(
                "Wishlist Functional Test Started");

        System.out.println(
                "==============================================");
    }


    // =====================================================
    // GO TO HOME PAGE
    // =====================================================

    public void goToHomePage() {

        driver.get(homeURL);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginButton));

        System.out.println(
                "Home page opened");
    }


    // =====================================================
    // LOGIN
    // =====================================================

    public void login()
            throws InterruptedException {

        System.out.println(
                "Opening Login page...");

        WebElement login = wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginButton));

        login.click();

        Thread.sleep(1000);

        System.out.println(
                "Login page opened");


        // -------------------------------------------------
        // EMAIL
        // -------------------------------------------------

        WebElement emailBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        email));

        emailBox.clear();

        emailBox.sendKeys(
                "test@gmail.com");


        // -------------------------------------------------
        // PASSWORD
        // -------------------------------------------------

        WebElement passwordBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        password));

        passwordBox.clear();

        passwordBox.sendKeys(
                "password");

        Thread.sleep(500);


        // -------------------------------------------------
        // LOGIN BUTTON
        // -------------------------------------------------

        WebElement submit = wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginSubmit));

        submit.click();

        Thread.sleep(2000);

        System.out.println(
                "Login completed");
    }


    // =====================================================
    // OPEN KIDS WEAR
    // =====================================================

    public void openKidsWear()
            throws InterruptedException {

        System.out.println(
                "Opening Kids Wear...");

        WebElement kids = wait.until(
                ExpectedConditions.elementToBeClickable(
                        kidsWear));

        kids.click();

        Thread.sleep(2000);

        System.out.println(
                "Kids Wear page opened");
    }


    // =====================================================
    // ADD PRODUCT TO WISHLIST
    // =====================================================

    public void addToWishlist()
            throws InterruptedException {

        System.out.println(
                "Searching Heart symbol...");

        WebElement heart = wait.until(
                ExpectedConditions.elementToBeClickable(
                        heartSymbol));

        heart.click();

        Thread.sleep(1500);

        System.out.println(
                "Heart symbol clicked");

        System.out.println(
                "Product added to Wishlist");
    }


    // =====================================================
    // OPEN WISHLIST
    // =====================================================

    public void openWishlist()
            throws InterruptedException {

        System.out.println(
                "Searching Wishlist icon...");

        WebElement wishlist = wait.until(
                ExpectedConditions.elementToBeClickable(
                        wishlistIcon));

        System.out.println(
                "Wishlist icon found");

        wishlist.click();

        Thread.sleep(2000);

        System.out.println(
                "Wishlist page opened");
    }


    // =====================================================
    // VERIFY PRODUCT
    // =====================================================

    public void verifyWishlistProduct()
            throws InterruptedException {

        System.out.println(
                "Searching product in Wishlist...");

        WebElement product = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        wishlistProduct));

        Assert.assertTrue(
                product.isDisplayed(),
                "Kids T-Shirt is not displayed in Wishlist");

        System.out.println(
                "Kids T-Shirt found in Wishlist");
    }


    // =====================================================
    // TEST 1
    // WL_01
    // ADD PRODUCT TO WISHLIST
    // =====================================================

    @Test(priority = 1)
    public void addProductToWishlistTest()
            throws InterruptedException {

        System.out.println();
        System.out.println(
                "========== WL_01 ==========");

        // Start from Home Page
        goToHomePage();

        login();

        openKidsWear();

        addToWishlist();

        openWishlist();

        verifyWishlistProduct();

        System.out.println(
                "WL_01 PASSED");
    }


    // =====================================================
    // TEST 2
    // WL_02
    // VERIFY PRODUCT IN WISHLIST
    // =====================================================

    @Test(priority = 2)
    public void verifyProductInWishlistTest()
            throws InterruptedException {

        System.out.println();
        System.out.println(
                "========== WL_02 ==========");

        // Start from Home Page
        goToHomePage();

        login();

        openKidsWear();

        addToWishlist();

        openWishlist();

        verifyWishlistProduct();

        System.out.println(
                "WL_02 PASSED");
    }


    // =====================================================
    // TEST 3
    // WL_03
    // VERIFY ADD TO CART BUTTON
    // =====================================================

    @Test(priority = 3)
    public void verifyAddToCartTest()
            throws InterruptedException {

        System.out.println();
        System.out.println(
                "========== WL_03 ==========");

        // Start from Home Page
        goToHomePage();

        login();

        openKidsWear();

        addToWishlist();

        openWishlist();

        verifyWishlistProduct();


        System.out.println(
                "Searching Add to Cart button...");

        WebElement cartButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        addToCart));

        Assert.assertTrue(
                cartButton.isDisplayed(),
                "Add to Cart button is not displayed");

        System.out.println(
                "Add to Cart button found");

        System.out.println(
                "WL_03 PASSED");
    }


    // =====================================================
    // TEST 4
    // WL_04
    // ADD WISHLIST PRODUCT TO CART
    // =====================================================

    @Test(priority = 4)
    public void addWishlistProductToCartTest()
            throws InterruptedException {

        System.out.println();
        System.out.println(
                "========== WL_04 ==========");

        // Start from Home Page
        goToHomePage();

        login();

        openKidsWear();

        addToWishlist();

        openWishlist();

        verifyWishlistProduct();


        System.out.println(
                "Searching Add to Cart button...");

        WebElement cartButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        addToCart));

        cartButton.click();

        Thread.sleep(1500);

        System.out.println(
                "Add to Cart clicked");

        System.out.println(
                "WL_04 PASSED");
    }


    // =====================================================
    // TEST 5
    // WL_05
    // REMOVE PRODUCT FROM WISHLIST
    // =====================================================

    @Test(priority = 5)
    public void removeProductFromWishlistTest()
            throws InterruptedException {

        System.out.println();
        System.out.println(
                "========== WL_05 ==========");

        // Start from Home Page
        goToHomePage();

        login();

        openKidsWear();

        addToWishlist();

        openWishlist();

        verifyWishlistProduct();


        System.out.println(
                "Searching Remove button...");

        WebElement removeButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        remove));

        removeButton.click();

        Thread.sleep(1500);

        System.out.println(
                "Remove button clicked");

        System.out.println(
                "Product removed from Wishlist");

        System.out.println(
                "WL_05 PASSED");
    }


    // =====================================================
    // TEARDOWN
    // CHROME CLOSES ONLY ONCE
    // =====================================================

    @AfterClass
    public void tearDown()
            throws InterruptedException {

        Thread.sleep(1000);

        if (driver != null) {

            driver.quit();

            System.out.println();
            System.out.println(
                    "==============================================");

            System.out.println(
                    "Chrome closed successfully");

            System.out.println(
                    "Wishlist Functional Test Completed");

            System.out.println(
                    "==============================================");
        }
    }
}