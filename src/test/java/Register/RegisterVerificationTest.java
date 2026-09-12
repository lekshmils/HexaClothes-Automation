package Register;

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

public class RegisterVerificationTest {

    WebDriver driver;
    WebDriverWait wait;

    // =====================================================
    // URL
    // =====================================================

    String homeURL =
            "https://hexaclothes.netlify.app/";

    String registerURL =
            "https://hexaclothes.netlify.app/register";


    // =====================================================
    // LOCATORS
    // =====================================================

    // Home page -> Login
    By loginButton = By.xpath(
            "//*[@id=\"root\"]/div[1]/nav/div[2]/a[3]/button"
    );

    // Register here link
    By registerLink = By.linkText("Register here");

    // Email field
    By emailField = By.id("email");

    // Name field
    By nameField = By.id("name");

    // Password field
    By passwordField = By.id("password");

    // Confirm password field
    By confirmPasswordField = By.id("confirmPassword");

    // Register button
    By registerButton = By.cssSelector(
            "button[type='submit']"
    );


    // =====================================================
    // SETUP
    // Chrome opens ONLY ONCE
    // =====================================================

    @BeforeClass
    public void setUp() {

        // Open Chrome only once
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Implicit wait
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        // Explicit wait
        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        // Open Home Page
        driver.get(homeURL);

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Chrome opened successfully"
        );

        System.out.println(
                "Register Verification Test Started"
        );

        System.out.println(
                "=============================================="
        );
    }


    // =====================================================
    // GO TO HOME PAGE
    // =====================================================

    public void goToHomePage() {

        driver.get(homeURL);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginButton
                )
        );
    }


    // =====================================================
    // OPEN REGISTER PAGE
    // =====================================================

    public void openRegisterPage() {

        // Make sure we start from Home Page
        goToHomePage();

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

        // Wait for Register page
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
    // ENTER REGISTRATION DATA
    // =====================================================

    public void enterRegistrationData(
            String email,
            String name,
            String password,
            String confirmPassword) {

        driver.findElement(emailField)
                .sendKeys(email);

        driver.findElement(nameField)
                .sendKeys(name);

        driver.findElement(passwordField)
                .sendKeys(password);

        driver.findElement(confirmPasswordField)
                .sendKeys(confirmPassword);
    }


    // =====================================================
    // REG_VERIFICATION_01
    // Verify Register Page URL
    // =====================================================

    @Test(priority = 1)
    public void verifyRegisterPageUrl() {

        openRegisterPage();

        String currentURL =
                driver.getCurrentUrl();

        System.out.println(
                "REG_VERIFICATION_01 Actual Result: "
                        + currentURL
        );

        Assert.assertEquals(
                currentURL,
                registerURL,
                "Incorrect Register page URL"
        );
    }


    // =====================================================
    // REG_VERIFICATION_02
    // Verify Email Field
    // =====================================================

    @Test(priority = 2)
    public void verifyEmailField() {

        openRegisterPage();

        boolean displayed =
                driver.findElement(emailField)
                        .isDisplayed();

        System.out.println(
                "REG_VERIFICATION_02 Actual Result: "
                        + displayed
        );

        Assert.assertTrue(
                displayed,
                "Email field is not displayed"
        );
    }


    // =====================================================
    // REG_VERIFICATION_03
    // Verify Name Field
    // =====================================================

    @Test(priority = 3)
    public void verifyNameField() {

        openRegisterPage();

        boolean displayed =
                driver.findElement(nameField)
                        .isDisplayed();

        System.out.println(
                "REG_VERIFICATION_03 Actual Result: "
                        + displayed
        );

        Assert.assertTrue(
                displayed,
                "Name field is not displayed"
        );
    }


    // =====================================================
    // REG_VERIFICATION_04
    // Verify Password Field
    // =====================================================

    @Test(priority = 4)
    public void verifyPasswordField() {

        openRegisterPage();

        boolean displayed =
                driver.findElement(passwordField)
                        .isDisplayed();

        System.out.println(
                "REG_VERIFICATION_04 Actual Result: "
                        + displayed
        );

        Assert.assertTrue(
                displayed,
                "Password field is not displayed"
        );
    }


    // =====================================================
    // REG_VERIFICATION_05
    // Verify Confirm Password Field
    // =====================================================

    @Test(priority = 5)
    public void verifyConfirmPasswordField() {

        openRegisterPage();

        boolean displayed =
                driver.findElement(confirmPasswordField)
                        .isDisplayed();

        System.out.println(
                "REG_VERIFICATION_05 Actual Result: "
                        + displayed
        );

        Assert.assertTrue(
                displayed,
                "Confirm Password field is not displayed"
        );
    }


    // =====================================================
    // REG_VERIFICATION_06
    // Verify Register Button
    // =====================================================

    @Test(priority = 6)
    public void verifyRegisterButton() {

        openRegisterPage();

        boolean displayed =
                driver.findElement(registerButton)
                        .isDisplayed();

        System.out.println(
                "REG_VERIFICATION_06 Actual Result: "
                        + displayed
        );

        Assert.assertTrue(
                displayed,
                "Register button is not displayed"
        );
    }


    // =====================================================
    // REG_VERIFICATION_07
    // Verify Email Field Enabled
    // =====================================================

    @Test(priority = 7)
    public void verifyEmailFieldEnabled() {

        openRegisterPage();

        boolean enabled =
                driver.findElement(emailField)
                        .isEnabled();

        System.out.println(
                "REG_VERIFICATION_07 Actual Result: "
                        + enabled
        );

        Assert.assertTrue(
                enabled,
                "Email field is not enabled"
        );
    }


    // =====================================================
    // REG_VERIFICATION_08
    // Verify Name Field Enabled
    // =====================================================

    @Test(priority = 8)
    public void verifyNameFieldEnabled() {

        openRegisterPage();

        boolean enabled =
                driver.findElement(nameField)
                        .isEnabled();

        System.out.println(
                "REG_VERIFICATION_08 Actual Result: "
                        + enabled
        );

        Assert.assertTrue(
                enabled,
                "Name field is not enabled"
        );
    }


    // =====================================================
    // REG_VERIFICATION_09
    // Verify Password Field Enabled
    // =====================================================

    @Test(priority = 9)
    public void verifyPasswordFieldEnabled() {

        openRegisterPage();

        boolean enabled =
                driver.findElement(passwordField)
                        .isEnabled();

        System.out.println(
                "REG_VERIFICATION_09 Actual Result: "
                        + enabled
        );

        Assert.assertTrue(
                enabled,
                "Password field is not enabled"
        );
    }


    // =====================================================
    // REG_VERIFICATION_10
    // Verify Confirm Password Enabled
    // =====================================================

    @Test(priority = 10)
    public void verifyConfirmPasswordEnabled() {

        openRegisterPage();

        boolean enabled =
                driver.findElement(confirmPasswordField)
                        .isEnabled();

        System.out.println(
                "REG_VERIFICATION_10 Actual Result: "
                        + enabled
        );

        Assert.assertTrue(
                enabled,
                "Confirm Password field is not enabled"
        );
    }


    // =====================================================
    // REG_VERIFICATION_11
    // Verify Register Button Enabled
    // =====================================================

    @Test(priority = 11)
    public void verifyRegisterButtonEnabled() {

        openRegisterPage();

        boolean enabled =
                driver.findElement(registerButton)
                        .isEnabled();

        System.out.println(
                "REG_VERIFICATION_11 Actual Result: "
                        + enabled
        );

        Assert.assertTrue(
                enabled,
                "Register button is not enabled"
        );
    }


    // =====================================================
    // REG_VERIFICATION_12
    // Verify Registered Email Cannot Register Again
    // =====================================================

    @Test(priority = 12)
    public void verifyRegisteredEmailCannotRegisterAgain()
            throws InterruptedException {

        String registeredEmail =
                "verification987@gmail.com";


        // -------------------------------------------------
        // FIRST REGISTRATION
        // -------------------------------------------------

        openRegisterPage();

        enterRegistrationData(
                registeredEmail,
                "Verification User",
                "Test@123",
                "Test@123"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        registerButton
                )
        ).click();

        Thread.sleep(1000);


        // -------------------------------------------------
        // RETURN TO HOME PAGE
        // -------------------------------------------------

        goToHomePage();


        // -------------------------------------------------
        // SECOND REGISTRATION WITH SAME EMAIL
        // -------------------------------------------------

        openRegisterPage();

        enterRegistrationData(
                registeredEmail,
                "Another Verification User",
                "Test@123",
                "Test@123"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        registerButton
                )
        ).click();

        Thread.sleep(1000);


        // -------------------------------------------------
        // CHECK RESULT
        // -------------------------------------------------

        String currentURL =
                driver.getCurrentUrl();

        System.out.println(
                "REG_VERIFICATION_12 Actual Result: "
                        + currentURL
        );


        /*
         * EXPECTED:
         *
         * Already registered email should NOT be
         * accepted again.
         *
         * User should remain on Register page
         * or receive duplicate-email validation.
         *
         * ACTUAL:
         *
         * If website allows the same email again,
         * this test will FAIL.
         */

        Assert.assertEquals(
                currentURL,
                registerURL,
                "BUG: Registered email was accepted again"
        );
    }


    // =====================================================
    // TEARDOWN
    // Chrome closes ONLY ONCE
    // =====================================================

    @AfterClass
    public void tearDown()
            throws InterruptedException {

        Thread.sleep(1000);

        if (driver != null) {

            driver.quit();

            System.out.println(
                    "=============================================="
            );

            System.out.println(
                    "Chrome closed successfully"
            );

            System.out.println(
                    "Register Verification Test Completed"
            );

            System.out.println(
                    "=============================================="
            );
        }
    }
}