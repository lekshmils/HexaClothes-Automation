package Checkout;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckoutVerificationTest {

    WebDriver driver;
    WebDriverWait wait;

    String baseUrl = "https://hexaclothes.netlify.app/";


    // =========================================================
    // HOME / CART LOCATORS
    // =========================================================

    By addToCartButton =
            By.xpath("//button[contains(normalize-space(),'Add to Cart')]");

    By cartNav =
            By.cssSelector("nav a[href*='cart']");

    By checkoutButton =
            By.xpath("//button[contains(normalize-space(),'Proceed to Checkout')]");


    // =========================================================
    // CHECKOUT LOCATORS
    // =========================================================

    By orderSummary = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[2]/h2");

    By productName = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[2]/div[1]/p[1]");

    By productPrice = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[2]/div[2]/p[2]");

    By shippingInformation = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[3]/h2");

    By firstName = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[3]/form/input[1]");

    By lastName = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[3]/form/input[2]");

    By email = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[3]/form/input[3]");

    By address = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[3]/form/input[4]");

    By city = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[3]/form/input[5]");

    By postalCode = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[3]/form/input[6]");

    By country = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[3]/form/select");

    By paymentInformation = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[4]/h2");

    By cardNumber = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[4]/form/input[1]");

    By cardHolderName = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[4]/form/input[2]");

    By date = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[4]/form/input[3]");

    By cvc = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[4]/form/input[4]");

    By placeOrder = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/div/div[5]/button");


    // =========================================================
    // BEFORE CLASS
    // CHROME OPENS ONLY ONCE
    // =========================================================

    @BeforeClass
    public void setUp() throws InterruptedException {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        // Open Home Page

        driver.get(baseUrl);

        Thread.sleep(1000);


        // =====================================================
        // ADD PRODUCT TO CART
        // =====================================================

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        addToCartButton
                )
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        addToCartButton
                )
        ).click();

        Thread.sleep(700);


        // =====================================================
        // OPEN CART
        // =====================================================

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        cartNav
                )
        ).click();

        Thread.sleep(1000);


        // =====================================================
        // OPEN CHECKOUT
        // =====================================================

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        checkoutButton
                )
        ).click();

        Thread.sleep(1000);


        // =====================================================
        // WAIT FOR CHECKOUT PAGE
        // =====================================================

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        orderSummary
                )
        );

        System.out.println("========================================");
        System.out.println("Chrome browser opened");
        System.out.println("Checkout page opened successfully");
        System.out.println("Same browser will be used for all tests");
        System.out.println("========================================");
    }


    // =========================================================
    // VERIFICATION 01
    // Checkout page
    // =========================================================

    @Test(priority = 1)
    public void verifyCheckoutPageOpened() {

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .toLowerCase()
                        .contains("checkout"),
                "Checkout page is not opened"
        );

        System.out.println(
                "VERIFICATION_01 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 02
    // Order Summary
    // =========================================================

    @Test(priority = 2)
    public void verifyOrderSummary() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                orderSummary
                        )
                ).isDisplayed(),
                "Order Summary is not displayed"
        );

        System.out.println(
                "VERIFICATION_02 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 03
    // Product Name
    // =========================================================

    @Test(priority = 3)
    public void verifyProductName() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                productName
                        )
                ).isDisplayed(),
                "Product name is not displayed"
        );

        System.out.println(
                "VERIFICATION_03 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 04
    // Product Price
    // =========================================================

    @Test(priority = 4)
    public void verifyProductPrice() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                productPrice
                        )
                ).isDisplayed(),
                "Product price is not displayed"
        );

        System.out.println(
                "VERIFICATION_04 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 05
    // Shipping Information
    // =========================================================

    @Test(priority = 5)
    public void verifyShippingInformation() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                shippingInformation
                        )
                ).isDisplayed(),
                "Shipping Information is not displayed"
        );

        System.out.println(
                "VERIFICATION_05 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 06
    // First Name
    // =========================================================

    @Test(priority = 6)
    public void verifyFirstNameField() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                firstName
                        )
                ).isDisplayed(),
                "First Name field is not displayed"
        );

        System.out.println(
                "VERIFICATION_06 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 07
    // Last Name
    // =========================================================

    @Test(priority = 7)
    public void verifyLastNameField() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                lastName
                        )
                ).isDisplayed(),
                "Last Name field is not displayed"
        );

        System.out.println(
                "VERIFICATION_07 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 08
    // Email
    // =========================================================

    @Test(priority = 8)
    public void verifyEmailField() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                email
                        )
                ).isDisplayed(),
                "Email field is not displayed"
        );

        System.out.println(
                "VERIFICATION_08 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 09
    // Address
    // =========================================================

    @Test(priority = 9)
    public void verifyAddressField() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                address
                        )
                ).isDisplayed(),
                "Address field is not displayed"
        );

        System.out.println(
                "VERIFICATION_09 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 10
    // City
    // =========================================================

    @Test(priority = 10)
    public void verifyCityField() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                city
                        )
                ).isDisplayed(),
                "City field is not displayed"
        );

        System.out.println(
                "VERIFICATION_10 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 11
    // Postal Code
    // =========================================================

    @Test(priority = 11)
    public void verifyPostalCodeField() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                postalCode
                        )
                ).isDisplayed(),
                "Postal Code field is not displayed"
        );

        System.out.println(
                "VERIFICATION_11 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 12
    // Country Dropdown
    // =========================================================

    @Test(priority = 12)
    public void verifyCountryDropdown() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                country
                        )
                ).isDisplayed(),
                "Country dropdown is not displayed"
        );

        System.out.println(
                "VERIFICATION_12 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 13
    // United States option
    // =========================================================

    @Test(priority = 13)
    public void verifyUnitedStatesOption() {

        Select select = new Select(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                country
                        )
                )
        );

        select.selectByVisibleText("United States");

        Assert.assertEquals(
                select.getFirstSelectedOption()
                        .getText()
                        .trim(),
                "United States",
                "United States is not selected"
        );

        System.out.println(
                "VERIFICATION_13 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 14
    // Payment Information
    // =========================================================

    @Test(priority = 14)
    public void verifyPaymentInformation() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                paymentInformation
                        )
                ).isDisplayed(),
                "Payment Information is not displayed"
        );

        System.out.println(
                "VERIFICATION_14 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 15
    // Card Number
    // =========================================================

    @Test(priority = 15)
    public void verifyCardNumberField() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                cardNumber
                        )
                ).isDisplayed(),
                "Card Number field is not displayed"
        );

        System.out.println(
                "VERIFICATION_15 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 16
    // Card Holder Name
    // =========================================================

    @Test(priority = 16)
    public void verifyCardHolderNameField() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                cardHolderName
                        )
                ).isDisplayed(),
                "Card Holder Name field is not displayed"
        );

        System.out.println(
                "VERIFICATION_16 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 17
    // Date
    // =========================================================

    @Test(priority = 17)
    public void verifyDateField() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                date
                        )
                ).isDisplayed(),
                "Date field is not displayed"
        );

        System.out.println(
                "VERIFICATION_17 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 18
    // CVC
    // =========================================================

    @Test(priority = 18)
    public void verifyCVCField() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                cvc
                        )
                ).isDisplayed(),
                "CVC field is not displayed"
        );

        System.out.println(
                "VERIFICATION_18 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 19
    // Place Order button
    // =========================================================

    @Test(priority = 19)
    public void verifyPlaceOrderButton() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                placeOrder
                        )
                ).isDisplayed(),
                "Place Order button is not displayed"
        );

        System.out.println(
                "VERIFICATION_19 - PASS"
        );
    }


    // =========================================================
    // VERIFICATION 20
    // Place Order button enabled
    // =========================================================

    @Test(priority = 20)
    public void verifyPlaceOrderButtonEnabled() {

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                placeOrder
                        )
                ).isEnabled(),
                "Place Order button is disabled"
        );

        System.out.println(
                "VERIFICATION_20 - PASS"
        );
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
        System.out.println("All 20 Checkout Verification tests completed");
        System.out.println("Same Chrome browser was used for all tests");
        System.out.println("Chrome browser closed successfully");
        System.out.println("========================================");
    }
}