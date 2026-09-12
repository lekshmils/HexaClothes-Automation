package Checkout;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutGeneralTest {

    WebDriver driver;
    WebDriverWait wait;

    String baseUrl = "https://hexaclothes.netlify.app/";

    // ============================================================
    // HOME PAGE LOCATORS
    // ============================================================

    By addToCartButton = By.xpath(
            "//button[contains(normalize-space(),'Add to Cart')]"
    );

    By cartNav = By.cssSelector(
            "nav a[href*='cart']"
    );


    // ============================================================
    // CART LOCATORS
    // ============================================================

    By checkoutButton = By.xpath(
            "//button[contains(normalize-space(),'Proceed to Checkout')]"
    );


    // ============================================================
    // CHECKOUT LOCATORS
    // ============================================================

    By orderSummary = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[2]/h2"
    );

    By productName = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[2]/div[1]/p[1]"
    );

    By productPrice = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[2]/div[2]/p[2]"
    );

    By shippingInformation = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[3]/h2"
    );

    By firstName = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[3]/form/input[1]"
    );

    By lastName = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[3]/form/input[2]"
    );

    By email = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[3]/form/input[3]"
    );

    By address = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[3]/form/input[4]"
    );

    By city = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[3]/form/input[5]"
    );

    By postalCode = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[3]/form/input[6]"
    );

    By country = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[3]/form/select"
    );

    By paymentInformation = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[4]/h2"
    );

    By cardNumber = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[4]/form/input[1]"
    );

    By cardHolderName = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[4]/form/input[2]"
    );

    By date = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[4]/form/input[3]"
    );

    By cvc = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[4]/form/input[4]"
    );

    By placeOrder = By.xpath(
            "//*[@id='root']/div[1]/div[2]/div/div[5]/button"
    );


    // ============================================================
    // BEFORE CLASS
    // CHROME OPENS ONLY ONCE
    // ============================================================

    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        addToCartButton
                )
        );

        System.out.println("========================================");
        System.out.println("Chrome browser opened");
        System.out.println("Home page opened");
        System.out.println("Same Chrome will be used for all 22 tests");
        System.out.println("========================================");
    }


    // ============================================================
    // BEFORE METHOD
    // RESET STATE BEFORE EACH TEST
    //
    // IMPORTANT:
    // Chrome is NOT opened again.
    // ============================================================

    @BeforeMethod
    public void resetTestState() {

        /*
         * Go back to Home page.
         */

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        addToCartButton
                )
        );

        /*
         * Clear Local Storage.
         *
         * This removes the previous test's cart data.
         */

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "window.localStorage.clear();"
                );

        /*
         * Refresh the page so the application
         * loads the cleared cart state.
         */

        driver.navigate().refresh();

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        addToCartButton
                )
        );

        System.out.println("----------------------------------------");
        System.out.println("Test state reset");
        System.out.println("Same Chrome browser reused");
        System.out.println("----------------------------------------");
    }


    // ============================================================
    // COMMON METHOD
    // HOME → CART → CHECKOUT
    // ============================================================

    private void openCheckout()
            throws InterruptedException {

        // --------------------------------------------------------
        // STEP 1
        // Add product to cart
        // --------------------------------------------------------

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        addToCartButton
                )
        ).click();

        Thread.sleep(700);

        System.out.println(
                "Product added to Cart"
        );


        // --------------------------------------------------------
        // STEP 2
        // Open Cart
        // --------------------------------------------------------

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        cartNav
                )
        ).click();

        Thread.sleep(700);

        System.out.println(
                "Cart opened"
        );


        // --------------------------------------------------------
        // STEP 3
        // Proceed to Checkout
        // --------------------------------------------------------

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        checkoutButton
                )
        ).click();

        Thread.sleep(1000);

        System.out.println(
                "Proceed to Checkout clicked"
        );


        // --------------------------------------------------------
        // STEP 4
        // Wait for Checkout page
        // --------------------------------------------------------

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        orderSummary
                )
        );

        System.out.println(
                "Checkout page opened"
        );
    }


    // ============================================================
    // COMMON METHOD
    // ENTER VALID CHECKOUT DATA
    // ============================================================

    private void enterValidData()
            throws InterruptedException {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        firstName
                )
        ).sendKeys("Lekshmi");


        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        lastName
                )
        ).sendKeys("LS");


        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        email
                )
        ).sendKeys("test@gmail.com");


        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        address
                )
        ).sendKeys("Trivandrum");


        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        city
                )
        ).sendKeys("Thiruvananthapuram");


        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        postalCode
                )
        ).sendKeys("695001");


        // ========================================================
        // COUNTRY DROPDOWN
        // ========================================================

        WebElement countryElement =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                country
                        )
                );

        Select countrySelect =
                new Select(countryElement);

        countrySelect.selectByIndex(1);


        // ========================================================
        // CARD NUMBER
        // ========================================================

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        cardNumber
                )
        ).sendKeys("4111111111111111");


        // ========================================================
        // CARD HOLDER NAME
        // ========================================================

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        cardHolderName
                )
        ).sendKeys("Lekshmi LS");


        // ========================================================
        // DATE
        // ========================================================

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        date
                )
        ).sendKeys("12/30");


        // ========================================================
        // CVC
        // ========================================================

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        cvc
                )
        ).sendKeys("123");


        Thread.sleep(500);

        System.out.println(
                "Valid checkout data entered"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_01
    // Verify Order Summary is displayed
    // ============================================================

    @Test(priority = 1)
    public void verifyOrderSummaryDisplayed()
            throws InterruptedException {

        openCheckout();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                orderSummary
                        )
                ).isDisplayed(),
                "Order Summary is not displayed"
        );

        System.out.println(
                "CHECKOUT_GENERAL_01 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_02
    // Verify Product Name is displayed
    // ============================================================

    @Test(priority = 2)
    public void verifyProductNameDisplayed()
            throws InterruptedException {

        openCheckout();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                productName
                        )
                ).isDisplayed(),
                "Product Name is not displayed"
        );

        System.out.println(
                "CHECKOUT_GENERAL_02 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_03
    // Verify Product Price is displayed
    // ============================================================

    @Test(priority = 3)
    public void verifyProductPriceDisplayed()
            throws InterruptedException {

        openCheckout();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                productPrice
                        )
                ).isDisplayed(),
                "Product Price is not displayed"
        );

        System.out.println(
                "CHECKOUT_GENERAL_03 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_04
    // Verify Shipping Information is displayed
    // ============================================================

    @Test(priority = 4)
    public void verifyShippingInformationDisplayed()
            throws InterruptedException {

        openCheckout();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                shippingInformation
                        )
                ).isDisplayed(),
                "Shipping Information is not displayed"
        );

        System.out.println(
                "CHECKOUT_GENERAL_04 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_05
    // Verify First Name field
    // ============================================================

    @Test(priority = 5)
    public void verifyFirstNameField()
            throws InterruptedException {

        openCheckout();

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                firstName
                        )
                );

        Assert.assertTrue(
                element.isDisplayed(),
                "First Name field is not displayed"
        );

        Assert.assertTrue(
                element.isEnabled(),
                "First Name field is not enabled"
        );

        System.out.println(
                "CHECKOUT_GENERAL_05 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_06
    // Verify Last Name field
    // ============================================================

    @Test(priority = 6)
    public void verifyLastNameField()
            throws InterruptedException {

        openCheckout();

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                lastName
                        )
                );

        Assert.assertTrue(
                element.isDisplayed(),
                "Last Name field is not displayed"
        );

        Assert.assertTrue(
                element.isEnabled(),
                "Last Name field is not enabled"
        );

        System.out.println(
                "CHECKOUT_GENERAL_06 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_07
    // Verify Email field
    // ============================================================

    @Test(priority = 7)
    public void verifyEmailField()
            throws InterruptedException {

        openCheckout();

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                email
                        )
                );

        Assert.assertTrue(
                element.isDisplayed(),
                "Email field is not displayed"
        );

        Assert.assertTrue(
                element.isEnabled(),
                "Email field is not enabled"
        );

        System.out.println(
                "CHECKOUT_GENERAL_07 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_08
    // Verify Address field
    // ============================================================

    @Test(priority = 8)
    public void verifyAddressField()
            throws InterruptedException {

        openCheckout();

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                address
                        )
                );

        Assert.assertTrue(
                element.isDisplayed(),
                "Address field is not displayed"
        );

        Assert.assertTrue(
                element.isEnabled(),
                "Address field is not enabled"
        );

        System.out.println(
                "CHECKOUT_GENERAL_08 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_09
    // Verify City field
    // ============================================================

    @Test(priority = 9)
    public void verifyCityField()
            throws InterruptedException {

        openCheckout();

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                city
                        )
                );

        Assert.assertTrue(
                element.isDisplayed(),
                "City field is not displayed"
        );

        Assert.assertTrue(
                element.isEnabled(),
                "City field is not enabled"
        );

        System.out.println(
                "CHECKOUT_GENERAL_09 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_10
    // Verify Postal Code field
    // ============================================================

    @Test(priority = 10)
    public void verifyPostalCodeField()
            throws InterruptedException {

        openCheckout();

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                postalCode
                        )
                );

        Assert.assertTrue(
                element.isDisplayed(),
                "Postal Code field is not displayed"
        );

        Assert.assertTrue(
                element.isEnabled(),
                "Postal Code field is not enabled"
        );

        System.out.println(
                "CHECKOUT_GENERAL_10 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_11
    // Verify Country dropdown
    // ============================================================

    @Test(priority = 11)
    public void verifyCountryDropdown()
            throws InterruptedException {

        openCheckout();

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                country
                        )
                );

        Assert.assertTrue(
                element.isDisplayed(),
                "Country dropdown is not displayed"
        );

        Assert.assertTrue(
                element.isEnabled(),
                "Country dropdown is not enabled"
        );

        System.out.println(
                "CHECKOUT_GENERAL_11 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_12
    // Verify Payment Information
    // ============================================================

    @Test(priority = 12)
    public void verifyPaymentInformation()
            throws InterruptedException {

        openCheckout();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                paymentInformation
                        )
                ).isDisplayed(),
                "Payment Information is not displayed"
        );

        System.out.println(
                "CHECKOUT_GENERAL_12 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_13
    // Verify Card Number field
    // ============================================================

    @Test(priority = 13)
    public void verifyCardNumberField()
            throws InterruptedException {

        openCheckout();

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                cardNumber
                        )
                );

        Assert.assertTrue(
                element.isDisplayed(),
                "Card Number field is not displayed"
        );

        Assert.assertTrue(
                element.isEnabled(),
                "Card Number field is not enabled"
        );

        System.out.println(
                "CHECKOUT_GENERAL_13 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_14
    // Verify Card Holder Name field
    // ============================================================

    @Test(priority = 14)
    public void verifyCardHolderNameField()
            throws InterruptedException {

        openCheckout();

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                cardHolderName
                        )
                );

        Assert.assertTrue(
                element.isDisplayed(),
                "Card Holder Name field is not displayed"
        );

        Assert.assertTrue(
                element.isEnabled(),
                "Card Holder Name field is not enabled"
        );

        System.out.println(
                "CHECKOUT_GENERAL_14 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_15
    // Verify Date field
    // ============================================================

    @Test(priority = 15)
    public void verifyDateField()
            throws InterruptedException {

        openCheckout();

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                date
                        )
                );

        Assert.assertTrue(
                element.isDisplayed(),
                "Date field is not displayed"
        );

        Assert.assertTrue(
                element.isEnabled(),
                "Date field is not enabled"
        );

        System.out.println(
                "CHECKOUT_GENERAL_15 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_16
    // Verify CVC field
    // ============================================================

    @Test(priority = 16)
    public void verifyCVCField()
            throws InterruptedException {

        openCheckout();

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                cvc
                        )
                );

        Assert.assertTrue(
                element.isDisplayed(),
                "CVC field is not displayed"
        );

        Assert.assertTrue(
                element.isEnabled(),
                "CVC field is not enabled"
        );

        System.out.println(
                "CHECKOUT_GENERAL_16 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_17
    // Verify Place Order button is displayed
    // ============================================================

    @Test(priority = 17)
    public void verifyPlaceOrderDisplayed()
            throws InterruptedException {

        openCheckout();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                placeOrder
                        )
                ).isDisplayed(),
                "Place Order button is not displayed"
        );

        System.out.println(
                "CHECKOUT_GENERAL_17 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_18
    // Verify Place Order button is enabled
    // ============================================================

    @Test(priority = 18)
    public void verifyPlaceOrderEnabled()
            throws InterruptedException {

        openCheckout();

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                placeOrder
                        )
                );

        Assert.assertTrue(
                element.isEnabled(),
                "Place Order button is not enabled"
        );

        System.out.println(
                "CHECKOUT_GENERAL_18 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_19
    // Enter valid shipping details
    // ============================================================

    @Test(priority = 19)
    public void enterValidShippingDetails()
            throws InterruptedException {

        openCheckout();

        driver.findElement(firstName)
                .sendKeys("Lekshmi");

        driver.findElement(lastName)
                .sendKeys("LS");

        driver.findElement(email)
                .sendKeys("test@gmail.com");

        driver.findElement(address)
                .sendKeys("Trivandrum");

        driver.findElement(city)
                .sendKeys("Thiruvananthapuram");

        driver.findElement(postalCode)
                .sendKeys("695001");


        Select select =
                new Select(
                        driver.findElement(country)
                );

        select.selectByIndex(1);


        Assert.assertEquals(
                driver.findElement(firstName)
                        .getAttribute("value"),
                "Lekshmi"
        );

        Assert.assertEquals(
                driver.findElement(lastName)
                        .getAttribute("value"),
                "LS"
        );

        Assert.assertEquals(
                driver.findElement(email)
                        .getAttribute("value"),
                "test@gmail.com"
        );

        Assert.assertEquals(
                driver.findElement(address)
                        .getAttribute("value"),
                "Trivandrum"
        );

        Assert.assertEquals(
                driver.findElement(city)
                        .getAttribute("value"),
                "Thiruvananthapuram"
        );

        Assert.assertEquals(
                driver.findElement(postalCode)
                        .getAttribute("value"),
                "695001"
        );

        System.out.println(
                "CHECKOUT_GENERAL_19 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_20
    // Enter valid payment details
    // ============================================================

    @Test(priority = 20)
    public void enterValidPaymentDetails()
            throws InterruptedException {

        openCheckout();

        driver.findElement(cardNumber)
                .sendKeys("4111111111111111");

        driver.findElement(cardHolderName)
                .sendKeys("Lekshmi LS");

        driver.findElement(date)
                .sendKeys("12/30");

        driver.findElement(cvc)
                .sendKeys("123");


        Assert.assertEquals(
                driver.findElement(cardNumber)
                        .getAttribute("value"),
                "4111111111111111"
        );

        Assert.assertEquals(
                driver.findElement(cardHolderName)
                        .getAttribute("value"),
                "Lekshmi LS"
        );

        Assert.assertEquals(
                driver.findElement(date)
                        .getAttribute("value"),
                "12/30"
        );

        Assert.assertEquals(
                driver.findElement(cvc)
                        .getAttribute("value"),
                "123"
        );

        System.out.println(
                "CHECKOUT_GENERAL_20 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_21
    // Enter all valid checkout details
    // ============================================================

    @Test(priority = 21)
    public void enterAllValidCheckoutDetails()
            throws InterruptedException {

        openCheckout();

        enterValidData();


        Assert.assertEquals(
                driver.findElement(firstName)
                        .getAttribute("value"),
                "Lekshmi"
        );

        Assert.assertEquals(
                driver.findElement(lastName)
                        .getAttribute("value"),
                "LS"
        );

        Assert.assertEquals(
                driver.findElement(email)
                        .getAttribute("value"),
                "test@gmail.com"
        );

        Assert.assertEquals(
                driver.findElement(address)
                        .getAttribute("value"),
                "Trivandrum"
        );

        Assert.assertEquals(
                driver.findElement(city)
                        .getAttribute("value"),
                "Thiruvananthapuram"
        );

        Assert.assertEquals(
                driver.findElement(postalCode)
                        .getAttribute("value"),
                "695001"
        );

        Assert.assertEquals(
                driver.findElement(cardNumber)
                        .getAttribute("value"),
                "4111111111111111"
        );

        Assert.assertEquals(
                driver.findElement(cardHolderName)
                        .getAttribute("value"),
                "Lekshmi LS"
        );

        Assert.assertEquals(
                driver.findElement(date)
                        .getAttribute("value"),
                "12/30"
        );

        Assert.assertEquals(
                driver.findElement(cvc)
                        .getAttribute("value"),
                "123"
        );

        System.out.println(
                "CHECKOUT_GENERAL_21 - PASS"
        );
    }


    // ============================================================
    // CHECKOUT_GENERAL_22
    // Verify Place Order can be clicked
    // ============================================================

    @Test(priority = 22)
    public void verifyPlaceOrderCanBeClicked()
            throws InterruptedException {

        openCheckout();

        enterValidData();

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                placeOrder
                        )
                );

        Assert.assertTrue(
                button.isEnabled(),
                "Place Order button is not enabled"
        );

        button.click();

        Thread.sleep(1000);

        System.out.println(
                "CHECKOUT_GENERAL_22 - Place Order clicked"
        );
    }


    // ============================================================
    // AFTER CLASS
    // CHROME CLOSES ONLY ONCE
    // ============================================================

    @AfterClass
    public void tearDown() {

        if (driver != null) {

            driver.quit();
        }

        System.out.println("========================================");
        System.out.println("All 22 Checkout General tests completed");
        System.out.println("Same Chrome browser was used");
        System.out.println("Chrome browser closed successfully");
        System.out.println("========================================");
    }
}