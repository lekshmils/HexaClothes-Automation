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

public class WishlistTest {

    WebDriver driver;
    WebDriverWait wait;


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
    // =====================================================

    By kidsWear = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[1]/ul/li[3]/a");


    // =====================================================
    // HEART SYMBOL
    // =====================================================

    By heartSymbol = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[1]/div/div/button[2]");


    // =====================================================
    // WISHLIST ICON
    // =====================================================

    By wishlistIcon = By.xpath(
            "//*[@id=\"root\"]/div[1]/nav/div[2]/a[1]");


    // =====================================================
    // WISHLIST HEADING
    // =====================================================

    By wishlistHeading = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div/div/h2");


    // =====================================================
    // WISHLIST PRODUCT
    // =====================================================

    By wishlistProduct = By.xpath(
            "//h2[normalize-space()='Kids T-Shirt']");


    // =====================================================
    // ADD TO CART
    // =====================================================

    By addToCart = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div/div/div/button[1]");


    // =====================================================
    // REMOVE
    // =====================================================

    By remove = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div/div/div/button[2]");


    // =====================================================
    // SETUP
    // Chrome opens ONLY ONCE
    // =====================================================

    @BeforeClass
    public void setUp() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("        STARTING WISHLIST TESTS");
        System.out.println("======================================");

        driver = new ChromeDriver();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20));

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get(
                "https://hexaclothes.netlify.app/");

        System.out.println("Chrome opened");
        System.out.println("Website opened");
    }


    // =====================================================
    // LOGIN
    // =====================================================

    public void login() {

        System.out.println("Opening Login page...");

        WebElement login = wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginButton));

        login.click();

        System.out.println("Login page opened");

        WebElement emailBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        email));

        emailBox.clear();

        emailBox.sendKeys("test@gmail.com");


        WebElement passwordBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        password));

        passwordBox.clear();

        passwordBox.sendKeys("password");


        WebElement submit = wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginSubmit));

        submit.click();

        System.out.println("Login button clicked");

        // Wait for login navigation to complete
        wait.until(
                ExpectedConditions.not(
                        ExpectedConditions.urlContains("/login")));

        System.out.println("Login completed");
    }


    // =====================================================
    // RETURN TO HOME PAGE
    // Used because all tests share one browser
    // =====================================================

    public void openHomePage() {

        driver.get(
                "https://hexaclothes.netlify.app/");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        loginButton));

        System.out.println("Home page opened");
    }


    // =====================================================
    // OPEN KIDS WEAR
    // =====================================================

    public void openKidsWear() {

        System.out.println(
                "Opening Kids Wear...");

        WebElement kids = wait.until(
                ExpectedConditions.elementToBeClickable(
                        kidsWear));

        kids.click();

        System.out.println(
                "Kids Wear page opened");
    }


    // =====================================================
    // ADD PRODUCT TO WISHLIST
    // =====================================================

    public void addToWishlist() {

        System.out.println(
                "Searching Heart symbol...");

        WebElement heart = wait.until(
                ExpectedConditions.elementToBeClickable(
                        heartSymbol));

        heart.click();

        System.out.println(
                "Heart symbol clicked");

        System.out.println(
                "Product added to Wishlist");
    }


    // =====================================================
    // OPEN WISHLIST
    // =====================================================

    public void openWishlist() {

        System.out.println(
                "Searching Wishlist icon...");

        WebElement wishlist = wait.until(
                ExpectedConditions.elementToBeClickable(
                        wishlistIcon));

        wishlist.click();

        System.out.println(
                "Wishlist page opened");
    }


    // =====================================================
    // VERIFY WISHLIST PAGE
    // =====================================================

    public void verifyWishlistPage() {

        System.out.println(
                "Checking Wishlist page...");

        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        wishlistHeading));

        Assert.assertTrue(
                heading.isDisplayed(),
                "Wishlist heading is not displayed");

        System.out.println(
                "Wishlist page verified");
    }


    // =====================================================
    // VERIFY PRODUCT
    // =====================================================

    public void verifyWishlistProduct() {

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
    // WISH_01
    // VERIFY WISHLIST OPTION IS AVAILABLE
    // =====================================================

    @Test(priority = 1)
    public void verifyWishlistOptionAvailable() {

        System.out.println();
        System.out.println(
                "========== WISH_01 ==========");

        openHomePage();

        WebElement wishlist = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        wishlistIcon));

        Assert.assertTrue(
                wishlist.isDisplayed(),
                "Wishlist option is not displayed");

        System.out.println(
                "Wishlist option displayed");

        System.out.println(
                "WISH_01 PASSED");
    }


    // =====================================================
    // WISH_02
    // VERIFY WISHLIST OPTION IS CLICKABLE
    // =====================================================

    @Test(priority = 2)
    public void verifyWishlistOptionClickable() {

        System.out.println();
        System.out.println(
                "========== WISH_02 ==========");

        openHomePage();

        login();

        WebElement wishlist = wait.until(
                ExpectedConditions.elementToBeClickable(
                        wishlistIcon));

        wishlist.click();

        verifyWishlistPage();

        System.out.println(
                "Wishlist option is clickable");

        System.out.println(
                "WISH_02 PASSED");
    }


    // =====================================================
    // WISH_16
    // VERIFY PRODUCT APPEARS AFTER ADDING
    // =====================================================

    @Test(priority = 3)
    public void verifyProductAfterAdding() {

        System.out.println();
        System.out.println(
                "========== WISH_16 ==========");

        openHomePage();

        login();

        openKidsWear();

        addToWishlist();

        openWishlist();

        verifyWishlistPage();

        verifyWishlistProduct();

        System.out.println(
                "Product appears in Wishlist");

        System.out.println(
                "WISH_16 PASSED");
    }


    // =====================================================
    // WISH_40
    // VERIFY PRODUCT IMAGE
    // =====================================================

    @Test(priority = 4)
    public void verifyProductImage() {

        System.out.println();
        System.out.println(
                "========== WISH_40 ==========");

        openHomePage();

        login();

        openKidsWear();

        addToWishlist();

        openWishlist();

        verifyWishlistPage();

        verifyWishlistProduct();

        WebElement productImage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//h2[normalize-space()='Kids T-Shirt']/preceding::img[1]")));

        Assert.assertTrue(
                productImage.isDisplayed(),
                "Product image is not displayed");

        System.out.println(
                "Product image displayed correctly");

        System.out.println(
                "WISH_40 PASSED");
    }


    // =====================================================
    // WISH_41
    // VERIFY PRODUCT NAME
    // =====================================================

    @Test(priority = 5)
    public void verifyProductName() {

        System.out.println();
        System.out.println(
                "========== WISH_41 ==========");

        openHomePage();

        login();

        openKidsWear();

        addToWishlist();

        openWishlist();

        verifyWishlistPage();

        WebElement product = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        wishlistProduct));

        Assert.assertEquals(
                product.getText(),
                "Kids T-Shirt",
                "Incorrect product name");

        System.out.println(
                "Correct product name displayed");

        System.out.println(
                "WISH_41 PASSED");
    }


    // =====================================================
    // WISH_43
    // VERIFY ADD TO CART BUTTON
    // =====================================================

    @Test(priority = 6)
    public void verifyAddToCartButton() {

        System.out.println();
        System.out.println(
                "========== WISH_43 ==========");

        openHomePage();

        login();

        openKidsWear();

        addToWishlist();

        openWishlist();

        verifyWishlistPage();

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
                "Add to Cart button displayed");

        System.out.println(
                "WISH_43 PASSED");
    }


    // =====================================================
    // WISH_44
    // VERIFY REMOVE BUTTON
    // =====================================================

    @Test(priority = 7)
    public void verifyRemoveButton() {

        System.out.println();
        System.out.println(
                "========== WISH_44 ==========");

        openHomePage();

        login();

        openKidsWear();

        addToWishlist();

        openWishlist();

        verifyWishlistPage();

        verifyWishlistProduct();

        System.out.println(
                "Searching Remove button...");

        WebElement removeButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        remove));

        Assert.assertTrue(
                removeButton.isDisplayed(),
                "Remove button is not displayed");

        System.out.println(
                "Remove button displayed");

        System.out.println(
                "WISH_44 PASSED");
    }


    // =====================================================
    // WISH_47
    // VERIFY PRODUCT DISAPPEARS AFTER REMOVE
    // =====================================================

    @Test(priority = 8)
    public void verifyRemovedProduct() {

        System.out.println();
        System.out.println(
                "========== WISH_47 ==========");

        openHomePage();

        login();

        openKidsWear();

        addToWishlist();

        openWishlist();

        verifyWishlistPage();

        verifyWishlistProduct();

        System.out.println(
                "Searching Remove button...");

        WebElement removeButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        remove));

        removeButton.click();

        System.out.println(
                "Remove button clicked");

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        wishlistProduct));

        boolean productPresent =
                driver.findElements(
                        wishlistProduct).size() > 0;

        Assert.assertFalse(
                productPresent,
                "Removed product is still displayed");

        System.out.println(
                "Removed product no longer appears");

        System.out.println(
                "WISH_47 PASSED");
    }


    // =====================================================
    // TEARDOWN
    // Chrome closes ONLY ONCE
    // =====================================================

    @AfterClass
    public void tearDown() {

        System.out.println();
        System.out.println(
                "======================================");
        System.out.println(
                "        WISHLIST TESTS COMPLETED");
        System.out.println(
                "======================================");

        if (driver != null) {

            driver.quit();

            System.out.println(
                    "Chrome closed");
        }
    }
}