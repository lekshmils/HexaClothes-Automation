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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutValidationTest {

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
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );

        System.out.println("================================================");
        System.out.println("Chrome opened successfully");
        System.out.println("Checkout Test Suite Started");
        System.out.println("Same Chrome will be used for all 20 tests");
        System.out.println("================================================");
    }


    // =========================================================
    // BEFORE METHOD
    // RESET TEST STATE
    //
    // DOES NOT OPEN NEW CHROME
    // =========================================================

    @BeforeMethod
    public void resetTestState()
            throws InterruptedException {

        /*
         * Every test starts from Home.
         *
         * SAME Chrome browser is reused.
         */

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        addToCartButton
                )
        );

        /*
         * Clear Local Storage so that cart data
         * from previous test does not affect next test.
         */

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "window.localStorage.clear();"
                );

        driver.navigate().refresh();

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        addToCartButton
                )
        );

        // =====================================================
        // ADD PRODUCT TO CART
        // =====================================================

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        addToCartButton
                )
        ).click();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        cartNav
                )
        );

        // =====================================================
        // OPEN CART
        // =====================================================

        driver.findElement(cartNav).click();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        checkoutButton
                )
        );

        // =====================================================
        // OPEN CHECKOUT
        // =====================================================

        driver.findElement(checkoutButton).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        firstName
                )
        );

        System.out.println("-----------------------------------------------");
        System.out.println("Test state reset");
        System.out.println("Home → Cart → Checkout");
        System.out.println("Same Chrome browser reused");
        System.out.println("-----------------------------------------------");
    }


    // =========================================================
    // COMMON METHOD
    // ENTER ALL VALID DATA
    // =========================================================

    private void enterAllValidData()
            throws InterruptedException {

        // First Name

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        firstName
                )
        ).clear();

        driver.findElement(firstName)
                .sendKeys("Lekshmi");


        // Last Name

        driver.findElement(lastName)
                .clear();

        driver.findElement(lastName)
                .sendKeys("LS");


        // Email

        driver.findElement(email)
                .clear();

        driver.findElement(email)
                .sendKeys("test@gmail.com");


        // Address

        driver.findElement(address)
                .clear();

        driver.findElement(address)
                .sendKeys("Trivandrum");


        // City

        driver.findElement(city)
                .clear();

        driver.findElement(city)
                .sendKeys("Thiruvananthapuram");


        // Postal Code

        driver.findElement(postalCode)
                .clear();

        driver.findElement(postalCode)
                .sendKeys("695001");


        // =====================================================
        // COUNTRY
        // =====================================================

        Select countrySelect =
                new Select(
                        wait.until(
                                ExpectedConditions
                                        .visibilityOfElementLocated(
                                                country
                                        )
                        )
                );

        countrySelect.selectByVisibleText(
                "United States"
        );


        // =====================================================
        // CARD NUMBER
        // =====================================================

        driver.findElement(cardNumber)
                .clear();

        driver.findElement(cardNumber)
                .sendKeys(
                        "4111111111111111"
                );


        // =====================================================
        // CARD HOLDER NAME
        // =====================================================

        driver.findElement(cardHolderName)
                .clear();

        driver.findElement(cardHolderName)
                .sendKeys(
                        "Lekshmi LS"
                );


        // =====================================================
        // DATE
        // =====================================================

        driver.findElement(date)
                .clear();

        driver.findElement(date)
                .sendKeys(
                        "12/30"
                );


        // =====================================================
        // CVC
        // =====================================================

        driver.findElement(cvc)
                .clear();

        driver.findElement(cvc)
                .sendKeys(
                        "123"
                );

        Thread.sleep(300);
    }


    // =========================================================
    // COMMON METHOD
    // CLEAR FIELD
    // =========================================================

    private void clearField(By field)
            throws InterruptedException {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        field
                )
        ).clear();

        Thread.sleep(200);
    }


    // =========================================================
    // COMMON METHOD
    // CLICK PLACE ORDER
    // =========================================================

    private void clickPlaceOrder()
            throws InterruptedException {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        placeOrder
                )
        ).click();

        Thread.sleep(800);
    }


    // =========================================================
    // COMMON METHOD
    // VERIFY VALIDATION
    // =========================================================

    private void verifyValidationRejected(
            String testCaseName) {

        String currentUrl =
                driver.getCurrentUrl().toLowerCase();

        boolean stillOnCheckout =
                currentUrl.contains("checkout");

        Assert.assertTrue(
                stillOnCheckout,
                "DEFECT in " + testCaseName
                + ": Invalid/blank data was accepted."
        );

        System.out.println(
                testCaseName + " - PASSED"
        );
    }


    // =========================================================
    // TEST 01
    // BLANK FIRST NAME
    // =========================================================

    @Test(priority = 1)
    public void validateBlankFirstName()
            throws InterruptedException {

        enterAllValidData();

        clearField(firstName);

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_01 - Blank First Name"
        );
    }


    // =========================================================
    // TEST 02
    // BLANK LAST NAME
    // =========================================================

    @Test(priority = 2)
    public void validateBlankLastName()
            throws InterruptedException {

        enterAllValidData();

        clearField(lastName);

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_02 - Blank Last Name"
        );
    }


    // =========================================================
    // TEST 03
    // BLANK EMAIL
    // =========================================================

    @Test(priority = 3)
    public void validateBlankEmail()
            throws InterruptedException {

        enterAllValidData();

        clearField(email);

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_03 - Blank Email"
        );
    }


    // =========================================================
    // TEST 04
    // INVALID EMAIL
    // =========================================================

    @Test(priority = 4)
    public void validateInvalidEmail()
            throws InterruptedException {

        enterAllValidData();

        clearField(email);

        driver.findElement(email)
                .sendKeys("invalidemail");

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_04 - Invalid Email"
        );
    }


    // =========================================================
    // TEST 05
    // BLANK ADDRESS
    // =========================================================

    @Test(priority = 5)
    public void validateBlankAddress()
            throws InterruptedException {

        enterAllValidData();

        clearField(address);

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_05 - Blank Address"
        );
    }


    // =========================================================
    // TEST 06
    // BLANK CITY
    // =========================================================

    @Test(priority = 6)
    public void validateBlankCity()
            throws InterruptedException {

        enterAllValidData();

        clearField(city);

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_06 - Blank City"
        );
    }


    // =========================================================
    // TEST 07
    // BLANK POSTAL CODE
    // =========================================================

    @Test(priority = 7)
    public void validateBlankPostalCode()
            throws InterruptedException {

        enterAllValidData();

        clearField(postalCode);

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_07 - Blank Postal Code"
        );
    }


    // =========================================================
    // TEST 08
    // BLANK COUNTRY
    // =========================================================

    @Test(priority = 8)
    public void validateBlankCountry()
            throws InterruptedException {

        enterAllValidData();

        Select countrySelect =
                new Select(
                        wait.until(
                                ExpectedConditions
                                        .visibilityOfElementLocated(
                                                country
                                        )
                        )
                );

        boolean blankOptionFound = false;

        for (int i = 0;
             i < countrySelect.getOptions().size();
             i++) {

            String optionText =
                    countrySelect.getOptions()
                            .get(i)
                            .getText()
                            .trim();

            if (optionText.isEmpty()) {

                countrySelect.selectByIndex(i);

                blankOptionFound = true;

                break;
            }
        }

        if (!blankOptionFound) {

            System.out.println(
                    "No blank country option available."
            );
        }

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_08 - Blank Country"
        );
    }


    // =========================================================
    // TEST 09
    // BLANK CARD NUMBER
    // =========================================================

    @Test(priority = 9)
    public void validateBlankCardNumber()
            throws InterruptedException {

        enterAllValidData();

        clearField(cardNumber);

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_09 - Blank Card Number"
        );
    }


    // =========================================================
    // TEST 10
    // INVALID CARD NUMBER
    // =========================================================

    @Test(priority = 10)
    public void validateInvalidCardNumber()
            throws InterruptedException {

        enterAllValidData();

        clearField(cardNumber);

        driver.findElement(cardNumber)
                .sendKeys("1234");

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_10 - Invalid Card Number"
        );
    }


    // =========================================================
    // TEST 11
    // BLANK CARD HOLDER NAME
    // =========================================================

    @Test(priority = 11)
    public void validateBlankCardHolderName()
            throws InterruptedException {

        enterAllValidData();

        clearField(cardHolderName);

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_11 - Blank Card Holder Name"
        );
    }


    // =========================================================
    // TEST 12
    // ALPHABETIC CARD NUMBER
    // =========================================================

    @Test(priority = 12)
    public void validateAlphabeticCardNumber()
            throws InterruptedException {

        enterAllValidData();

        clearField(cardNumber);

        driver.findElement(cardNumber)
                .sendKeys("ABCDEFGHIJK");

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_12 - Alphabetic Card Number"
        );
    }


    // =========================================================
    // TEST 13
    // BLANK DATE
    // =========================================================

    @Test(priority = 13)
    public void validateBlankDate()
            throws InterruptedException {

        enterAllValidData();

        clearField(date);

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_13 - Blank Date"
        );
    }


    // =========================================================
    // TEST 14
    // INVALID DATE
    // =========================================================

    @Test(priority = 14)
    public void validateInvalidDate()
            throws InterruptedException {

        enterAllValidData();

        clearField(date);

        driver.findElement(date)
                .sendKeys("00/00");

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_14 - Invalid Date"
        );
    }


    // =========================================================
    // TEST 15
    // BLANK CVC
    // =========================================================

    @Test(priority = 15)
    public void validateBlankCVC()
            throws InterruptedException {

        enterAllValidData();

        clearField(cvc);

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_15 - Blank CVC"
        );
    }


    // =========================================================
    // TEST 16
    // INVALID CVC
    // =========================================================

    @Test(priority = 16)
    public void validateInvalidCVC()
            throws InterruptedException {

        enterAllValidData();

        clearField(cvc);

        driver.findElement(cvc)
                .sendKeys("12");

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_16 - Invalid CVC"
        );
    }


    // =========================================================
    // TEST 17
    // ALPHABETIC CVC
    // =========================================================

    @Test(priority = 17)
    public void validateAlphabeticCVC()
            throws InterruptedException {

        enterAllValidData();

        clearField(cvc);

        driver.findElement(cvc)
                .sendKeys("ABC");

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_17 - Alphabetic CVC"
        );
    }


    // =========================================================
    // TEST 18
    // ALL SHIPPING FIELDS BLANK
    // =========================================================

    @Test(priority = 18)
    public void validateAllShippingFieldsBlank()
            throws InterruptedException {

        enterAllValidData();

        clearField(firstName);
        clearField(lastName);
        clearField(email);
        clearField(address);
        clearField(city);
        clearField(postalCode);

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_18 - All Shipping Fields Blank"
        );
    }


    // =========================================================
    // TEST 19
    // ALL PAYMENT FIELDS BLANK
    // =========================================================

    @Test(priority = 19)
    public void validateAllPaymentFieldsBlank()
            throws InterruptedException {

        enterAllValidData();

        clearField(cardNumber);
        clearField(cardHolderName);
        clearField(date);
        clearField(cvc);

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_19 - All Payment Fields Blank"
        );
    }


    // =========================================================
    // TEST 20
    // ALL CHECKOUT FIELDS BLANK
    // =========================================================

    @Test(priority = 20)
    public void validateAllFieldsBlank()
            throws InterruptedException {

        enterAllValidData();

        // =====================================================
        // SHIPPING FIELDS
        // =====================================================

        clearField(firstName);
        clearField(lastName);
        clearField(email);
        clearField(address);
        clearField(city);
        clearField(postalCode);


        // =====================================================
        // COUNTRY
        // =====================================================

        Select countrySelect =
                new Select(
                        wait.until(
                                ExpectedConditions
                                        .visibilityOfElementLocated(
                                                country
                                        )
                        )
                );

        boolean blankOptionFound = false;

        for (int i = 0;
             i < countrySelect.getOptions().size();
             i++) {

            String optionText =
                    countrySelect.getOptions()
                            .get(i)
                            .getText()
                            .trim();

            if (optionText.isEmpty()) {

                countrySelect.selectByIndex(i);

                blankOptionFound = true;

                break;
            }
        }

        if (!blankOptionFound) {

            System.out.println(
                    "No blank country option available."
            );
        }


        // =====================================================
        // PAYMENT FIELDS
        // =====================================================

        clearField(cardNumber);
        clearField(cardHolderName);
        clearField(date);
        clearField(cvc);


        // =====================================================
        // SUBMIT
        // =====================================================

        clickPlaceOrder();

        verifyValidationRejected(
                "CHK_VAL_20 - All Checkout Fields Blank"
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

        System.out.println("================================================");
        System.out.println("All 20 Checkout Validation Tests completed");
        System.out.println("Same Chrome browser was used");
        System.out.println("Chrome browser closed successfully");
        System.out.println("================================================");
    }
}