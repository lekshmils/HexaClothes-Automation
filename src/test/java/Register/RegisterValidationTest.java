package Register;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterValidationTest {

    WebDriver driver;
    WebDriverWait wait;

    String baseUrl = "https://hexaclothes.netlify.app/";

    // =====================================================
    // LOCATORS
    // =====================================================

    By loginButton = By.xpath(
            "//*[@id=\"root\"]/div[1]/nav/div[2]/a[3]/button"
    );

    By registerLink = By.linkText("Register here");

    By emailField = By.id("email");

    By nameField = By.id("name");

    By passwordField = By.id("password");

    By confirmPasswordField = By.id("confirmPassword");

    By registerButton = By.cssSelector(
            "button[type='submit']"
    );


    // =====================================================
    // SETUP
    // Chrome opens ONLY ONCE
    // =====================================================

    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        driver.get(baseUrl);

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "Chrome Browser Opened"
        );

        System.out.println(
                "=========================================="
        );
    }


    // =====================================================
    // BEFORE EACH TEST
    // Navigate back to Home Page
    // Chrome is NOT closed
    // =====================================================

    @BeforeMethod
    public void beforeEachTest() {

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginButton
                )
        );

        System.out.println(
                "Starting next Register test..."
        );
    }


    // =====================================================
    // OPEN REGISTER PAGE
    // =====================================================

    public void openRegisterPage() {

        // Click Login
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginButton
                )
        ).click();

        // Click Register here
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        registerLink
                )
        ).click();

        // Wait for Register page fields
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        emailField
                )
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        nameField
                )
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        passwordField
                )
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        confirmPasswordField
                )
        );
    }


    // =====================================================
    // CLEAR ALL FIELDS
    // =====================================================

    public void clearAllFields() {

        driver.findElement(emailField).clear();

        driver.findElement(nameField).clear();

        driver.findElement(passwordField).clear();

        driver.findElement(confirmPasswordField).clear();
    }


    // =====================================================
    // ENTER VALID DATA
    // =====================================================

    public void enterValidData() {

        driver.findElement(emailField)
                .sendKeys("newuser987@gmail.com");

        driver.findElement(nameField)
                .sendKeys("Test User");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(confirmPasswordField)
                .sendKeys("Test@123");
    }


    // =====================================================
    // CHECK HTML5 VALIDATION
    // =====================================================

    public boolean isFieldInvalid(By locator) {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        return !(Boolean) js.executeScript(
                "return arguments[0].checkValidity();",
                driver.findElement(locator)
        );
    }


    // =====================================================
    // GET VALIDATION MESSAGE
    // =====================================================

    public String getValidationMessage(By locator) {

        return driver.findElement(locator)
                .getAttribute("validationMessage");
    }


    // =====================================================
    // REG_VALIDATION_01
    // Verify Register page opens
    // =====================================================

    @Test(priority = 1)
    public void verifyRegisterPageOpens() {

        openRegisterPage();

        Assert.assertTrue(
                driver.findElement(emailField).isDisplayed(),
                "Email field is not displayed"
        );

        System.out.println(
                "REG_VALIDATION_01: Register page opened successfully"
        );
    }


    // =====================================================
    // REG_VALIDATION_02
    // Verify Email field is displayed
    // =====================================================

    @Test(priority = 2)
    public void verifyEmailFieldDisplayed() {

        openRegisterPage();

        Assert.assertTrue(
                driver.findElement(emailField).isDisplayed(),
                "Email field is not displayed"
        );

        System.out.println(
                "REG_VALIDATION_02: Email field displayed"
        );
    }


    // =====================================================
    // REG_VALIDATION_03
    // Verify Name field is displayed
    // =====================================================

    @Test(priority = 3)
    public void verifyNameFieldDisplayed() {

        openRegisterPage();

        Assert.assertTrue(
                driver.findElement(nameField).isDisplayed(),
                "Name field is not displayed"
        );

        System.out.println(
                "REG_VALIDATION_03: Name field displayed"
        );
    }


    // =====================================================
    // REG_VALIDATION_04
    // Verify Password field is displayed
    // =====================================================

    @Test(priority = 4)
    public void verifyPasswordFieldDisplayed() {

        openRegisterPage();

        Assert.assertTrue(
                driver.findElement(passwordField).isDisplayed(),
                "Password field is not displayed"
        );

        System.out.println(
                "REG_VALIDATION_04: Password field displayed"
        );
    }


    // =====================================================
    // REG_VALIDATION_05
    // Verify Confirm Password field is displayed
    // =====================================================

    @Test(priority = 5)
    public void verifyConfirmPasswordFieldDisplayed() {

        openRegisterPage();

        Assert.assertTrue(
                driver.findElement(confirmPasswordField).isDisplayed(),
                "Confirm Password field is not displayed"
        );

        System.out.println(
                "REG_VALIDATION_05: Confirm Password field displayed"
        );
    }


    // =====================================================
    // REG_VALIDATION_06
    // Verify Register button is displayed
    // =====================================================

    @Test(priority = 6)
    public void verifyRegisterButtonDisplayed() {

        openRegisterPage();

        Assert.assertTrue(
                driver.findElement(registerButton).isDisplayed(),
                "Register button is not displayed"
        );

        System.out.println(
                "REG_VALIDATION_06: Register button displayed"
        );
    }


    // =====================================================
    // REG_VALIDATION_07
    // Verify all fields accept valid data
    // =====================================================

    @Test(priority = 7)
    public void validRegistrationData() {

        openRegisterPage();

        enterValidData();

        Assert.assertFalse(
                isFieldInvalid(emailField),
                "Valid email is considered invalid"
        );

        Assert.assertFalse(
                isFieldInvalid(nameField),
                "Valid name is considered invalid"
        );

        Assert.assertFalse(
                isFieldInvalid(passwordField),
                "Valid password is considered invalid"
        );

        Assert.assertFalse(
                isFieldInvalid(confirmPasswordField),
                "Valid confirm password is considered invalid"
        );

        System.out.println(
                "REG_VALIDATION_07: Valid data accepted"
        );
    }


    // =====================================================
    // REG_VALIDATION_08
    // Blank Email
    // =====================================================

    @Test(priority = 8)
    public void blankEmail() {

        openRegisterPage();

        driver.findElement(nameField)
                .sendKeys("Test User");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(confirmPasswordField)
                .sendKeys("Test@123");

        driver.findElement(registerButton)
                .click();

        Assert.assertTrue(
                isFieldInvalid(emailField),
                "Blank email was accepted"
        );

        System.out.println(
                "REG_VALIDATION_08: "
                        + getValidationMessage(emailField)
        );
    }


    // =====================================================
    // REG_VALIDATION_09
    // Blank Name
    // =====================================================

    @Test(priority = 9)
    public void blankName() {

        openRegisterPage();

        driver.findElement(emailField)
                .sendKeys("test987@gmail.com");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(confirmPasswordField)
                .sendKeys("Test@123");

        driver.findElement(registerButton)
                .click();

        Assert.assertTrue(
                isFieldInvalid(nameField),
                "Blank name was accepted"
        );

        System.out.println(
                "REG_VALIDATION_09: "
                        + getValidationMessage(nameField)
        );
    }


    // =====================================================
    // REG_VALIDATION_10
    // Blank Password
    // =====================================================

    @Test(priority = 10)
    public void blankPassword() {

        openRegisterPage();

        driver.findElement(emailField)
                .sendKeys("test987@gmail.com");

        driver.findElement(nameField)
                .sendKeys("Test User");

        driver.findElement(confirmPasswordField)
                .sendKeys("Test@123");

        driver.findElement(registerButton)
                .click();

        Assert.assertTrue(
                isFieldInvalid(passwordField),
                "Blank password was accepted"
        );

        System.out.println(
                "REG_VALIDATION_10: "
                        + getValidationMessage(passwordField)
        );
    }


    // =====================================================
    // REG_VALIDATION_11
    // Blank Confirm Password
    // =====================================================

    @Test(priority = 11)
    public void blankConfirmPassword() {

        openRegisterPage();

        driver.findElement(emailField)
                .sendKeys("test987@gmail.com");

        driver.findElement(nameField)
                .sendKeys("Test User");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(registerButton)
                .click();

        Assert.assertTrue(
                isFieldInvalid(confirmPasswordField),
                "Blank confirm password was accepted"
        );

        System.out.println(
                "REG_VALIDATION_11: "
                        + getValidationMessage(confirmPasswordField)
        );
    }


    // =====================================================
    // REG_VALIDATION_12
    // Blank All Fields
    // =====================================================

    @Test(priority = 12)
    public void blankAllFields() {

        openRegisterPage();

        driver.findElement(registerButton)
                .click();

        Assert.assertTrue(
                isFieldInvalid(emailField),
                "Blank email was not validated"
        );

        System.out.println(
                "REG_VALIDATION_12: Blank fields validated"
        );
    }


    // =====================================================
    // REG_VALIDATION_13
    // Invalid Email - Missing @
    // =====================================================

    @Test(priority = 13)
    public void invalidEmailWithoutAtSymbol() {

        openRegisterPage();

        driver.findElement(emailField)
                .sendKeys("testgmail.com");

        driver.findElement(nameField)
                .sendKeys("Test User");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(confirmPasswordField)
                .sendKeys("Test@123");

        driver.findElement(registerButton)
                .click();

        Assert.assertTrue(
                isFieldInvalid(emailField),
                "Email without @ was accepted"
        );

        System.out.println(
                "REG_VALIDATION_13: "
                        + getValidationMessage(emailField)
        );
    }


    // =====================================================
    // REG_VALIDATION_14
    // Invalid Email - Missing part after @
    // =====================================================

    @Test(priority = 14)
    public void invalidEmailWithoutPartAfterAt() {

        openRegisterPage();

        driver.findElement(emailField)
                .sendKeys("test@");

        driver.findElement(nameField)
                .sendKeys("Test User");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(confirmPasswordField)
                .sendKeys("Test@123");

        driver.findElement(registerButton)
                .click();

        Assert.assertTrue(
                isFieldInvalid(emailField),
                "Email without part after @ was accepted"
        );

        System.out.println(
                "REG_VALIDATION_14: "
                        + getValidationMessage(emailField)
        );
    }


    // =====================================================
    // REG_VALIDATION_15
    // Invalid Email - Missing Domain
    // =====================================================

    @Test(priority = 15)
    public void invalidEmailMissingDomain() {

        openRegisterPage();

        driver.findElement(emailField)
                .sendKeys("test@");

        driver.findElement(nameField)
                .sendKeys("Test User");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(confirmPasswordField)
                .sendKeys("Test@123");

        driver.findElement(registerButton)
                .click();

        String validationMessage =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        System.out.println(
                "REG_VALIDATION_15: Email validation message = "
                        + validationMessage
        );

        Assert.assertFalse(
                validationMessage.isEmpty(),
                "Email validation message was not displayed"
        );
    }


    // =====================================================
    // REG_VALIDATION_16
    // Email with spaces
    // =====================================================

    @Test(priority = 16)
    public void emailWithSpaces() {

        openRegisterPage();

        driver.findElement(emailField)
                .sendKeys("test user@gmail.com");

        driver.findElement(nameField)
                .sendKeys("Test User");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(confirmPasswordField)
                .sendKeys("Test@123");

        driver.findElement(registerButton)
                .click();

        Assert.assertTrue(
                isFieldInvalid(emailField),
                "Email containing spaces was accepted"
        );

        System.out.println(
                "REG_VALIDATION_16: "
                        + getValidationMessage(emailField)
        );
    }


    // =====================================================
    // REG_VALIDATION_17
    // Password and Confirm Password mismatch
    // =====================================================

    @Test(priority = 17)
    public void passwordMismatch() {

        openRegisterPage();

        driver.findElement(emailField)
                .sendKeys("mismatch987@gmail.com");

        driver.findElement(nameField)
                .sendKeys("Test User");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(confirmPasswordField)
                .sendKeys("Wrong@123");

        driver.findElement(registerButton)
                .click();

        Alert alert = wait.until(
                ExpectedConditions.alertIsPresent()
        );

        String alertText = alert.getText();

        System.out.println(
                "REG_VALIDATION_17 Alert: "
                        + alertText
        );

        Assert.assertEquals(
                alertText,
                "Passwords do not match",
                "Wrong alert message displayed"
        );

        alert.accept();

        System.out.println(
                "REG_VALIDATION_17: Password mismatch prevented registration"
        );
    }


    // =====================================================
    // REG_VALIDATION_18
    // Confirm Password different from Password
    // =====================================================

    @Test(priority = 18)
    public void confirmPasswordDifferent() {

        openRegisterPage();

        driver.findElement(emailField)
                .sendKeys("different987@gmail.com");

        driver.findElement(nameField)
                .sendKeys("Test User");

        driver.findElement(passwordField)
                .sendKeys("Password@123");

        driver.findElement(confirmPasswordField)
                .sendKeys("Password@456");

        driver.findElement(registerButton)
                .click();

        Alert alert = wait.until(
                ExpectedConditions.alertIsPresent()
        );

        String alertText = alert.getText();

        System.out.println(
                "REG_VALIDATION_18 Alert: "
                        + alertText
        );

        Assert.assertEquals(
                alertText,
                "Passwords do not match",
                "Wrong alert message displayed"
        );

        alert.accept();

        System.out.println(
                "REG_VALIDATION_18: Password mismatch validated"
        );
    }


    // =====================================================
    // REG_VALIDATION_19
    // Register button clickable
    // =====================================================

    @Test(priority = 19)
    public void verifyRegisterButtonClickable() {

        openRegisterPage();

        Assert.assertTrue(
                driver.findElement(registerButton).isEnabled(),
                "Register button is not enabled"
        );

        System.out.println(
                "REG_VALIDATION_19: Register button is clickable"
        );
    }


    // =====================================================
    // TEARDOWN
    // Chrome closes ONLY ONCE
    // =====================================================

    @AfterClass
    public void tearDown() {

        if (driver != null) {

            driver.quit();

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "All Register Validation Tests Completed"
            );

            System.out.println(
                    "Chrome Browser Closed"
            );

            System.out.println(
                    "=========================================="
            );
        }
    }
}