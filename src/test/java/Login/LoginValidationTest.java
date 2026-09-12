package Login;

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

public class LoginValidationTest {

    WebDriver driver;
    WebDriverWait wait;

    String baseUrl = "https://hexaclothes.netlify.app/";


    // =====================================================
    // LOCATORS
    // =====================================================

    // Home page -> Login
    By loginButton = By.xpath(
            "//*[@id=\"root\"]/div[1]/nav/div[2]/a[3]/button"
    );

    // Email
    By emailField = By.id("email");

    // Password
    By passwordField = By.id("password");

    // Login submit button
    By loginSubmitButton = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/form/button"
    );

    // Logout button
    By logoutButton = By.xpath(
            "//*[@id=\"root\"]/div[1]/nav/div[2]/button"
    );


    // =====================================================
    // SETUP
    // Chrome opens ONLY ONCE
    // =====================================================

    @BeforeClass
    public void setUp() {

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "Opening Chrome Browser"
        );

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Implicit Wait
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        // Explicit Wait
        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        driver.get(baseUrl);

        System.out.println(
                "Home Page Opened"
        );

        System.out.println(
                "=========================================="
        );
    }


    // =====================================================
    // OPEN LOGIN PAGE
    // =====================================================

    public void openLoginPage()
            throws InterruptedException {

        // Always return to Home page
        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginButton
                )
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        emailField
                )
        );
    }


    // =====================================================
    // CHECK LOGIN STATUS
    // =====================================================

    public boolean isLoggedIn() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            logoutButton
                    )
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }


    // =====================================================
    // LOGIN_03
    // Invalid email format
    // =====================================================

    @Test(priority = 3)
    public void login_03_invalidEmailFormat()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("abc123");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_03 Actual Result: " + message
        );

        Assert.assertFalse(
                loggedIn,
                "Invalid email was accepted"
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Email validation message was not displayed"
        );
    }


    // =====================================================
    // LOGIN_04
    // Blank email
    // =====================================================

    @Test(priority = 4)
    public void login_04_blankEmail()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_04 Actual Result: " + message
        );

        Assert.assertFalse(
                loggedIn,
                "User logged in with blank email"
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Required validation was not displayed"
        );
    }


    // =====================================================
    // LOGIN_05
    // Both fields blank
    // =====================================================

    @Test(priority = 5)
    public void login_05_bothFieldsBlank()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_05 Actual Result: " + message
        );

        Assert.assertFalse(
                loggedIn,
                "User logged in with both fields blank"
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Required validation was not displayed"
        );
    }


    // =====================================================
    // LOGIN_06
    // Blank password
    // =====================================================

    @Test(priority = 6)
    public void login_06_blankPassword()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("test@gmail.com");

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(passwordField)
                        .getAttribute("validationMessage");

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_06 Actual Result: " + message
        );

        Assert.assertFalse(
                loggedIn,
                "User logged in with blank password"
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Required password validation was not displayed"
        );
    }


    // =====================================================
    // LOGIN_12
    // Spaces only in email
    // =====================================================

    @Test(priority = 12)
    public void login_12_spacesOnlyInEmail()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("     ");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_12 Actual Result: " + message
        );

        Assert.assertFalse(
                loggedIn,
                "Spaces-only email was accepted"
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Email validation message was not displayed"
        );
    }


    // =====================================================
    // LOGIN_13
    // Spaces only in password
    // =====================================================

    @Test(priority = 13)
    public void login_13_spacesOnlyInPassword()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("test@gmail.com");

        driver.findElement(passwordField)
                .sendKeys("     ");

        driver.findElement(loginSubmitButton)
                .click();

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_13 Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "User did not log in")
        );

        Assert.assertFalse(
                loggedIn,
                "DEFECT: Spaces-only password was accepted"
        );
    }


    // =====================================================
    // LOGIN_14
    // Email containing spaces
    // =====================================================

    @Test(priority = 14)
    public void login_14_emailContainingSpaces()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("test @gmail.com");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_14 Actual Result: " + message
        );

        Assert.assertFalse(
                loggedIn,
                "Email containing spaces was accepted"
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Email validation message was not displayed"
        );
    }


    // =====================================================
    // LOGIN_15
    // Very long email
    // =====================================================

    @Test(priority = 15)
    public void login_15_veryLongEmail()
            throws InterruptedException {

        openLoginPage();

        String longEmail =
                "test12345678901234567890123456789012345678901234567890@gmail.com";

        driver.findElement(emailField)
                .sendKeys(longEmail);

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(loginSubmitButton)
                .click();

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_15 Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "User did not log in")
        );

        Assert.assertFalse(
                loggedIn,
                "DEFECT: Very long email was accepted"
        );
    }


    // =====================================================
    // LOGIN_16
    // Very long password
    // =====================================================

    @Test(priority = 16)
    public void login_16_veryLongPassword()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("test@gmail.com");

        String longPassword =
                "Test@12345678901234567890123456789012345678901234567890";

        driver.findElement(passwordField)
                .sendKeys(longPassword);

        driver.findElement(loginSubmitButton)
                .click();

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_16 Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "User did not log in")
        );

        Assert.assertFalse(
                loggedIn,
                "DEFECT: Very long password was accepted"
        );
    }


    // =====================================================
    // LOGIN_24
    // Blank fields validation
    // =====================================================

    @Test(priority = 24)
    public void login_24_blankFieldsValidation()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        System.out.println(
                "LOGIN_24 Actual Result: " + message
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Validation was not triggered"
        );
    }


    // =====================================================
    // LOGIN_25
    // Invalid email validation message
    // =====================================================

    @Test(priority = 25)
    public void login_25_invalidEmailValidationMessage()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("abc123");

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        System.out.println(
                "LOGIN_25 Actual Result: " + message
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Invalid email validation message was not displayed"
        );

        Assert.assertTrue(
                message.contains("@")
                        || message.toLowerCase()
                        .contains("email"),
                "Unexpected email validation message"
        );
    }


    // =====================================================
    // LOGIN_26
    // Blank email validation message
    // =====================================================

    @Test(priority = 26)
    public void login_26_blankEmailValidationMessage()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        System.out.println(
                "LOGIN_26 Actual Result: " + message
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Blank email validation message was not displayed"
        );
    }


    // =====================================================
    // LOGIN_27
    // Blank password validation message
    // =====================================================

    @Test(priority = 27)
    public void login_27_blankPasswordValidationMessage()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("test@gmail.com");

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(passwordField)
                        .getAttribute("validationMessage");

        System.out.println(
                "LOGIN_27 Actual Result: " + message
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Blank password validation message was not displayed"
        );
    }


    // =====================================================
    // LOGIN_31
    // Special characters in email
    // =====================================================

    @Test(priority = 31)
    public void login_31_specialCharactersInEmail()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("test!#$gmail.com");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_31 Actual Result: " + message
        );

        Assert.assertFalse(
                loggedIn,
                "Special-character email was accepted"
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Email validation message was not displayed"
        );
    }


    // =====================================================
    // LOGIN_32
    // Numeric-only email
    // =====================================================

    @Test(priority = 32)
    public void login_32_numericOnlyEmail()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("123456789");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_32 Actual Result: " + message
        );

        Assert.assertFalse(
                loggedIn,
                "Numeric-only email was accepted"
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Email validation message was not displayed"
        );
    }


    // =====================================================
    // LOGIN_33
    // Email without @
    // =====================================================

    @Test(priority = 33)
    public void login_33_emailWithoutAt()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("testgmail.com");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_33 Actual Result: " + message
        );

        Assert.assertFalse(
                loggedIn,
                "Email without @ was accepted"
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Email validation message was not displayed"
        );
    }


    // =====================================================
    // LOGIN_34
    // Email ending with @
    // =====================================================

    @Test(priority = 34)
    public void login_34_emailEndingWithAt()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("test@");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_34 Actual Result: " + message
        );

        Assert.assertFalse(
                loggedIn,
                "Incomplete email was accepted"
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Email validation message was not displayed"
        );
    }


    // =====================================================
    // LOGIN_35
    // Email beginning with @
    // =====================================================

    @Test(priority = 35)
    public void login_35_emailBeginningWithAt()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("@gmail.com");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_35 Actual Result: " + message
        );

        Assert.assertFalse(
                loggedIn,
                "Incomplete email was accepted"
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Email validation message was not displayed"
        );
    }


    // =====================================================
    // LOGIN_36
    // Multiple @ symbols
    // =====================================================

    @Test(priority = 36)
    public void login_36_multipleAtSymbols()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("test@@gmail.com");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(loginSubmitButton)
                .click();

        String message =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        boolean loggedIn =
                isLoggedIn();

        System.out.println(
                "LOGIN_36 Actual Result: " + message
        );

        Assert.assertFalse(
                loggedIn,
                "Email with multiple @ symbols was accepted"
        );

        Assert.assertFalse(
                message.isEmpty(),
                "Email validation message was not displayed"
        );
    }


    // =====================================================
    // CLOSE BROWSER
    // Chrome closes ONLY ONCE
    // =====================================================

    @AfterClass
    public void tearDown() {

        System.out.println();
        System.out.println(
                "=========================================="
        );

        System.out.println(
                "All Login Validation Test Cases Executed"
        );

        System.out.println(
                "Closing Chrome Browser"
        );

        if (driver != null) {

            driver.quit();
        }

        System.out.println(
                "Chrome Closed Successfully"
        );

        System.out.println(
                "=========================================="
        );
    }
}