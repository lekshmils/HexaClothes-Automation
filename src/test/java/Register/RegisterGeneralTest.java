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

public class RegisterGeneralTest {

    WebDriver driver;
    WebDriverWait wait;

    String baseUrl =
            "https://hexaclothes.netlify.app/";


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
                "================================================="
        );

        System.out.println(
                "Chrome browser opened successfully"
        );

        System.out.println(
                "Register Functional Test Started"
        );

        System.out.println(
                "All test cases will execute in SAME Chrome"
        );

        System.out.println(
                "================================================="
        );
    }


    // =====================================================
    // OPEN REGISTER PAGE
    // =====================================================

    public void openRegisterPage() {

        /*
         * Navigate to Home page.
         *
         * This DOES NOT open a new browser.
         * The same Chrome session is reused.
         */

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginButton
                )
        ).click();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        registerLink
                )
        ).click();

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
    // CLICK REGISTER
    // =====================================================

    public void clickRegister() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        registerButton
                )
        ).click();
    }


    // =====================================================
    // REG_FUNC_01
    // Valid Registration
    // =====================================================

    @Test(priority = 1)
    public void validRegistration()
            throws InterruptedException {

        openRegisterPage();

        enterRegistrationData(
                "validuser987@gmail.com",
                "Valid User",
                "Strong@123",
                "Strong@123"
        );

        clickRegister();

        Thread.sleep(1000);

        String currentUrl =
                driver.getCurrentUrl();

        System.out.println(
                "REG_FUNC_01 Actual Result: "
                        + currentUrl
        );

        Assert.assertNotEquals(
                currentUrl,
                baseUrl + "register",
                "Valid registration did not proceed"
        );
    }


    // =====================================================
    // REG_FUNC_02
    // Valid Name Registration
    // =====================================================

    @Test(priority = 2)
    public void validNameRegistration()
            throws InterruptedException {

        openRegisterPage();

        enterRegistrationData(
                "nameuser987@gmail.com",
                "Test User",
                "Strong@123",
                "Strong@123"
        );

        clickRegister();

        Thread.sleep(1000);

        System.out.println(
                "REG_FUNC_02 Actual Result: "
                        + driver.getCurrentUrl()
        );
    }


    // =====================================================
    // REG_FUNC_03
    // Different Valid Email Registration
    // =====================================================

    @Test(priority = 3)
    public void differentValidEmailRegistration()
            throws InterruptedException {

        openRegisterPage();

        enterRegistrationData(
                "different987@gmail.com",
                "Different User",
                "Strong@123",
                "Strong@123"
        );

        clickRegister();

        Thread.sleep(1000);

        String currentUrl =
                driver.getCurrentUrl();

        System.out.println(
                "REG_FUNC_03 Actual Result: "
                        + currentUrl
        );

        Assert.assertNotEquals(
                currentUrl,
                baseUrl + "register",
                "Valid different email registration failed"
        );
    }


    // =====================================================
    // REG_FUNC_04
    // Valid Password Registration
    // =====================================================

    @Test(priority = 4)
    public void validPasswordRegistration()
            throws InterruptedException {

        openRegisterPage();

        enterRegistrationData(
                "passworduser987@gmail.com",
                "Password User",
                "Strong@123",
                "Strong@123"
        );

        clickRegister();

        Thread.sleep(1000);

        System.out.println(
                "REG_FUNC_04 Actual Result: "
                        + driver.getCurrentUrl()
        );
    }


    // =====================================================
    // REG_FUNC_05
    // Registration Redirect
    // =====================================================

    @Test(priority = 5)
    public void registrationRedirect()
            throws InterruptedException {

        openRegisterPage();

        enterRegistrationData(
                "redirect987@gmail.com",
                "Redirect User",
                "Strong@123",
                "Strong@123"
        );

        clickRegister();

        Thread.sleep(1000);

        String currentUrl =
                driver.getCurrentUrl();

        System.out.println(
                "REG_FUNC_05 Actual Result: "
                        + currentUrl
        );

        Assert.assertNotEquals(
                currentUrl,
                baseUrl + "register",
                "Registration did not redirect"
        );
    }


    // =====================================================
    // REG_FUNC_06
    // Register with Uppercase Email
    // =====================================================

    @Test(priority = 6)
    public void uppercaseEmailRegistration()
            throws InterruptedException {

        openRegisterPage();

        enterRegistrationData(
                "UPPERCASE987@GMAIL.COM",
                "Uppercase User",
                "Strong@123",
                "Strong@123"
        );

        clickRegister();

        Thread.sleep(1000);

        System.out.println(
                "REG_FUNC_06 Actual Result: "
                        + driver.getCurrentUrl()
        );
    }


    // =====================================================
    // REG_FUNC_07
    // Random Email Registration
    // =====================================================

    @Test(priority = 7)
    public void randomEmailRegistration()
            throws InterruptedException {

        openRegisterPage();

        enterRegistrationData(
                "random987654@gmail.com",
                "Random User",
                "Strong@123",
                "Strong@123"
        );

        clickRegister();

        Thread.sleep(1000);

        System.out.println(
                "REG_FUNC_07 Actual Result: "
                        + driver.getCurrentUrl()
        );
    }


    // =====================================================
    // REG_FUNC_08
    // Numeric Email Registration
    // =====================================================

    @Test(priority = 8)
    public void numericEmailRegistration()
            throws InterruptedException {

        openRegisterPage();

        enterRegistrationData(
                "123456987@gmail.com",
                "Numeric User",
                "Strong@123",
                "Strong@123"
        );

        clickRegister();

        Thread.sleep(1000);

        System.out.println(
                "REG_FUNC_08 Actual Result: "
                        + driver.getCurrentUrl()
        );
    }


    // =====================================================
    // REG_FUNC_09
    // Repeated Email Registration
    // =====================================================

    @Test(priority = 9)
    public void repeatedEmailRegistration()
            throws InterruptedException {

        openRegisterPage();

        String registeredEmail =
                "registered987@gmail.com";

        // =================================================
        // FIRST REGISTRATION
        // =================================================

        enterRegistrationData(
                registeredEmail,
                "Registered User",
                "Strong@123",
                "Strong@123"
        );

        clickRegister();

        Thread.sleep(1000);


        // =================================================
        // RETURN TO HOME
        // Same Chrome browser
        // =================================================

        driver.get(baseUrl);


        // =================================================
        // OPEN REGISTER AGAIN
        // =================================================

        openRegisterPage();


        // =================================================
        // SECOND REGISTRATION
        // =================================================

        enterRegistrationData(
                registeredEmail,
                "Another User",
                "Strong@123",
                "Strong@123"
        );

        clickRegister();

        Thread.sleep(1000);


        String currentUrl =
                driver.getCurrentUrl();

        boolean registrationRejected =
                currentUrl.equals(
                        baseUrl + "register"
                );

        System.out.println(
                "REG_FUNC_09 Actual Result: "
                        + currentUrl
        );

        Assert.assertTrue(
                registrationRejected,
                "Application allowed repeated registration"
        );
    }


    // =====================================================
    // REG_FUNC_10
    // Duplicate Email Registration
    // =====================================================

    @Test(priority = 10)
    public void duplicateEmailRegistration()
            throws InterruptedException {

        openRegisterPage();

        String duplicateEmail =
                "duplicate987@gmail.com";


        // =================================================
        // FIRST REGISTRATION
        // =================================================

        enterRegistrationData(
                duplicateEmail,
                "Duplicate User",
                "Strong@123",
                "Strong@123"
        );

        clickRegister();

        Thread.sleep(1000);


        // =================================================
        // RETURN TO HOME
        // Same Chrome browser
        // =================================================

        driver.get(baseUrl);


        // =================================================
        // OPEN REGISTER AGAIN
        // =================================================

        openRegisterPage();


        // =================================================
        // SECOND REGISTRATION
        // =================================================

        enterRegistrationData(
                duplicateEmail,
                "Duplicate User Again",
                "Strong@123",
                "Strong@123"
        );

        clickRegister();

        Thread.sleep(1000);


        String currentUrl =
                driver.getCurrentUrl();

        boolean duplicateRejected =
                currentUrl.equals(
                        baseUrl + "register"
                );

        System.out.println(
                "REG_FUNC_10 Actual Result: "
                        + currentUrl
        );

        Assert.assertTrue(
                duplicateRejected,
                "Duplicate email was accepted"
        );
    }


    // =====================================================
    // TEARDOWN
    // Chrome closes ONLY ONCE
    // =====================================================

    @AfterClass
    public void tearDown()
            throws InterruptedException {

        System.out.println(
                "================================================="
        );

        System.out.println(
                "All Register Functional test cases completed."
        );

        System.out.println(
                "Closing Chrome browser..."
        );

        System.out.println(
                "================================================="
        );

        if (driver != null) {

            driver.quit();
        }
    }
}