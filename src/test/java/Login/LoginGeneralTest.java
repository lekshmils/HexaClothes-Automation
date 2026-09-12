package Login;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utilities.DataProviderUtility;

public class LoginGeneralTest {

    WebDriver driver;
    WebDriverWait wait;

    String baseUrl = "https://hexaclothes.netlify.app/";


    // =====================================================
    // LOCATORS
    // =====================================================

    By loginButton = By.xpath(
            "//*[@id=\"root\"]/div[1]/nav/div[2]/a[3]/button"
    );

    By emailField = By.id("email");

    By passwordField = By.id("password");

    By loginSubmitButton = By.xpath(
            "//*[@id=\"root\"]/div[1]/div[2]/form/button"
    );

    By logoutButton = By.xpath(
            "//*[@id=\"root\"]/div[1]/nav/div[2]/button"
    );


    // =====================================================
    // SETUP
    // CHROME OPENS ONLY ONCE
    // =====================================================

    @BeforeClass
    public void setUp() {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("OPENING CHROME");
        System.out.println("==========================================");

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

        System.out.println("Chrome opened successfully");
        System.out.println("Home page opened");
    }


    // =====================================================
    // OPEN LOGIN PAGE
    // =====================================================

    public void openLoginPage()
            throws InterruptedException {

        // If currently not on Home page, open Home page
        if (!driver.getCurrentUrl().equals(baseUrl)) {

            driver.get(baseUrl);

            Thread.sleep(1000);
        }

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginButton
                )
        ).click();

        Thread.sleep(1000);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        emailField
                )
        );

        System.out.println("Login page opened");
    }


    // =====================================================
    // CHECK LOGIN STATUS
    // =====================================================

    public boolean isLoggedIn()
            throws InterruptedException {

        Thread.sleep(1000);

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
    // PERFORM LOGIN
    // =====================================================

    public void performLogin(
            String email,
            String password)
            throws InterruptedException {

        // Clear email field
        driver.findElement(emailField).clear();

        // Enter email
        if (email != null && !email.isEmpty()) {

            driver.findElement(emailField)
                    .sendKeys(email);
        }

        Thread.sleep(500);

        // Clear password field
        driver.findElement(passwordField).clear();

        // Enter password
        if (password != null && !password.isEmpty()) {

            driver.findElement(passwordField)
                    .sendKeys(password);
        }

        Thread.sleep(500);

        // Click Login
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginSubmitButton
                )
        ).click();

        Thread.sleep(1000);
    }


    // =====================================================
    // RESET TO HOME PAGE
    // =====================================================

    public void resetToHome() {

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        loginButton
                )
        );
    }


    // =====================================================
    // LOGIN_02
    // Valid Login
    // =====================================================

    @Test(priority = 1)
    public void login_02_validLogin()
            throws InterruptedException {

        System.out.println();
        System.out.println("LOGIN_02 STARTED");

        resetToHome();

        openLoginPage();

        performLogin(
                "test@gmail.com",
                "Test@123"
        );

        boolean loggedIn = isLoggedIn();

        System.out.println(
                "LOGIN_02 Actual Result: "
                        + (loggedIn
                        ? "User logged in successfully"
                        : "User was not logged in")
        );

        Assert.assertTrue(
                loggedIn,
                "Valid credentials were not accepted"
        );

        System.out.println("LOGIN_02 PASSED");
    }


    // =====================================================
    // LOGIN_09
    // Unregistered Email
    // =====================================================

    @Test(priority = 2)
    public void login_09_unregisteredEmail()
            throws InterruptedException {

        System.out.println();
        System.out.println("LOGIN_09 STARTED");

        resetToHome();

        openLoginPage();

        performLogin(
                "unregistered987@gmail.com",
                "Test@123"
        );

        boolean loggedIn = isLoggedIn();

        System.out.println(
                "LOGIN_09 Actual Result: "
                        + (loggedIn
                        ? "User was able to log in with unregistered email"
                        : "User was not able to log in")
        );

        Assert.assertFalse(
                loggedIn,
                "Unregistered email was accepted"
        );

        System.out.println("LOGIN_09 PASSED");
    }


    // =====================================================
    // LOGIN_10
    // Incorrect Password
    // =====================================================

    @Test(priority = 3)
    public void login_10_incorrectPassword()
            throws InterruptedException {

        System.out.println();
        System.out.println("LOGIN_10 STARTED");

        resetToHome();

        openLoginPage();

        performLogin(
                "test@gmail.com",
                "Wrong@999"
        );

        boolean loggedIn = isLoggedIn();

        System.out.println(
                "LOGIN_10 Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "User did not log in")
        );

        Assert.assertFalse(
                loggedIn,
                "Incorrect password was accepted"
        );

        System.out.println("LOGIN_10 PASSED");
    }


    // =====================================================
    // LOGIN_11
    // Random Email and Password
    // =====================================================

    @Test(priority = 4)
    public void login_11_randomEmailAndPassword()
            throws InterruptedException {

        System.out.println();
        System.out.println("LOGIN_11 STARTED");

        resetToHome();

        openLoginPage();

        performLogin(
                "random98765@gmail.com",
                "Random@123"
        );

        boolean loggedIn = isLoggedIn();

        System.out.println(
                "LOGIN_11 Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "User did not log in")
        );

        Assert.assertFalse(
                loggedIn,
                "Random email and password were accepted"
        );

        System.out.println("LOGIN_11 PASSED");
    }


    // =====================================================
    // LOGIN_28
    // Uppercase Email
    // =====================================================

    @Test(priority = 5)
    public void login_28_uppercaseEmail()
            throws InterruptedException {

        System.out.println();
        System.out.println("LOGIN_28 STARTED");

        resetToHome();

        openLoginPage();

        performLogin(
                "TEST@GMAIL.COM",
                "Test@123"
        );

        boolean loggedIn = isLoggedIn();

        System.out.println(
                "LOGIN_28 Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "User did not log in")
        );

        Assert.assertTrue(
                loggedIn,
                "Uppercase email was not accepted"
        );

        System.out.println("LOGIN_28 PASSED");
    }


    // =====================================================
    // LOGIN_29
    // Leading Spaces in Email
    // =====================================================

    @Test(priority = 6)
    public void login_29_leadingSpacesInEmail()
            throws InterruptedException {

        System.out.println();
        System.out.println("LOGIN_29 STARTED");

        resetToHome();

        openLoginPage();

        performLogin(
                " test@gmail.com",
                "Test@123"
        );

        boolean loggedIn = isLoggedIn();

        System.out.println(
                "LOGIN_29 Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "User did not log in")
        );

        Assert.assertTrue(
                loggedIn,
                "Leading-space email was not accepted"
        );

        System.out.println("LOGIN_29 PASSED");
    }


    // =====================================================
    // LOGIN_30
    // Trailing Spaces in Email
    // =====================================================

    @Test(priority = 7)
    public void login_30_trailingSpacesInEmail()
            throws InterruptedException {

        System.out.println();
        System.out.println("LOGIN_30 STARTED");

        resetToHome();

        openLoginPage();

        performLogin(
                "test@gmail.com ",
                "Test@123"
        );

        boolean loggedIn = isLoggedIn();

        System.out.println(
                "LOGIN_30 Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "User did not log in")
        );

        Assert.assertTrue(
                loggedIn,
                "Trailing-space email was not accepted"
        );

        System.out.println("LOGIN_30 PASSED");
    }


    // =====================================================
    // LOGIN_37
    // Password Containing Spaces
    // =====================================================

    @Test(priority = 8)
    public void login_37_passwordContainingSpaces()
            throws InterruptedException {

        System.out.println();
        System.out.println("LOGIN_37 STARTED");

        resetToHome();

        openLoginPage();

        performLogin(
                "test@gmail.com",
                "Test 123"
        );

        boolean loggedIn = isLoggedIn();

        System.out.println(
                "LOGIN_37 Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "User did not log in")
        );

        Assert.assertTrue(
                loggedIn,
                "Password containing spaces was not accepted"
        );

        System.out.println("LOGIN_37 PASSED");
    }


    // =====================================================
    // LOGIN_38
    // Special Character Password
    // =====================================================

    @Test(priority = 9)
    public void login_38_specialCharacterPassword()
            throws InterruptedException {

        System.out.println();
        System.out.println("LOGIN_38 STARTED");

        resetToHome();

        openLoginPage();

        performLogin(
                "test@gmail.com",
                "@#$%123"
        );

        boolean loggedIn = isLoggedIn();

        System.out.println(
                "LOGIN_38 Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "User did not log in")
        );

        Assert.assertTrue(
                loggedIn,
                "Special-character password was not accepted"
        );

        System.out.println("LOGIN_38 PASSED");
    }


    // =====================================================
    // LOGIN_39
    // Numeric Only Password
    // =====================================================

    @Test(priority = 10)
    public void login_39_numericOnlyPassword()
            throws InterruptedException {

        System.out.println();
        System.out.println("LOGIN_39 STARTED");

        resetToHome();

        openLoginPage();

        performLogin(
                "test@gmail.com",
                "123456"
        );

        boolean loggedIn = isLoggedIn();

        System.out.println(
                "LOGIN_39 Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "User did not log in")
        );

        Assert.assertTrue(
                loggedIn,
                "Numeric-only password was not accepted"
        );

        System.out.println("LOGIN_39 PASSED");
    }


    // =====================================================
    // LOGIN_40
    // One Character Password
    // =====================================================

    @Test(priority = 11)
    public void login_40_oneCharacterPassword()
            throws InterruptedException {

        System.out.println();
        System.out.println("LOGIN_40 STARTED");

        resetToHome();

        openLoginPage();

        performLogin(
                "test@gmail.com",
                "a"
        );

        boolean loggedIn = isLoggedIn();

        System.out.println(
                "LOGIN_40 Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "User did not log in")
        );

        Assert.assertFalse(
                loggedIn,
                "One-character password was accepted"
        );

        System.out.println("LOGIN_40 PASSED");
    }


    // =====================================================
    // LOGIN_49
    // Login with Valid Input
    // =====================================================

    @Test(priority = 12)
    public void login_49_validInput()
            throws InterruptedException {

        System.out.println();
        System.out.println("LOGIN_49 STARTED");

        resetToHome();

        openLoginPage();

        performLogin(
                "test@gmail.com",
                "Test@123"
        );

        boolean loggedIn = isLoggedIn();

        System.out.println(
                "LOGIN_49 Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "User did not log in")
        );

        Assert.assertTrue(
                loggedIn,
                "Login action failed with valid input"
        );

        System.out.println("LOGIN_49 PASSED");
    }


    // =====================================================
    // LOGIN_50
    // Login with Invalid Input
    // =====================================================

    @Test(priority = 13)
    public void login_50_invalidInput()
            throws InterruptedException {

        System.out.println();
        System.out.println("LOGIN_50 STARTED");

        resetToHome();

        openLoginPage();

        performLogin(
                "abc123",
                "Test@123"
        );

        boolean loggedIn = isLoggedIn();

        System.out.println(
                "LOGIN_50 Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "Email validation prevented login")
        );

        Assert.assertFalse(
                loggedIn,
                "Invalid email was accepted"
        );

        System.out.println("LOGIN_50 PASSED");
    }


    // =====================================================
    // EXCEL DATA-DRIVEN LOGIN TEST
    // =====================================================

    @Test(
            priority = 14,
            dataProvider = "loginData",
            dataProviderClass = DataProviderUtility.class
    )
    public void loginUsingExcel(
            String testCaseID,
            String email,
            String password,
            String expected)
            throws InterruptedException {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("EXCEL LOGIN TEST");
        System.out.println("==========================================");

        System.out.println(
                "Test Case ID : " + testCaseID
        );

        System.out.println(
                "Email        : " + email
        );

        System.out.println(
                "Password     : " + password
        );

        System.out.println(
                "Expected     : " + expected
        );


        resetToHome();

        openLoginPage();

        performLogin(
                email,
                password
        );

        Thread.sleep(1000);

        boolean loggedIn = isLoggedIn();


        System.out.println(
                "Actual Result: "
                        + (loggedIn
                        ? "User logged in"
                        : "User did not log in")
        );


        // =================================================
        // SUCCESS
        // =================================================

        if (expected.equalsIgnoreCase("Success")) {

            Assert.assertTrue(
                    loggedIn,
                    testCaseID
                            + ": User was not logged in"
            );
        }


        // =================================================
        // SHOULD NOT LOGIN
        // =================================================

        else if (
                expected.equalsIgnoreCase(
                        "Should Not Login"
                )
        ) {

            Assert.assertFalse(
                    loggedIn,
                    testCaseID
                            + ": Invalid credentials were accepted"
            );
        }


        // =================================================
        // INVALID EMAIL
        // =================================================

        else if (
                expected.equalsIgnoreCase(
                        "Invalid Email"
                )
        ) {

            Assert.assertFalse(
                    loggedIn,
                    testCaseID
                            + ": Invalid email was accepted"
            );
        }


        // =================================================
        // REQUIRED
        // =================================================

        else if (
                expected.equalsIgnoreCase(
                        "Required"
                )
        ) {

            Assert.assertFalse(
                    loggedIn,
                    testCaseID
                            + ": Required field was accepted"
            );
        }


        // =================================================
        // UNKNOWN EXPECTED VALUE
        // =================================================

        else {

            Assert.fail(
                    testCaseID
                            + ": Unknown Expected value = "
                            + expected
            );
        }

        System.out.println(
                "=========================================="
        );
    }


    // =====================================================
    // SCREENSHOT UTILITY
    // =====================================================

    public void takeScreenshot(String testName) {

        try {

            TakesScreenshot screenshot =
                    (TakesScreenshot) driver;

            File source =
                    screenshot.getScreenshotAs(
                            OutputType.FILE
                    );

            File folder =
                    new File("screenshots");

            if (!folder.exists()) {

                folder.mkdirs();
            }

            File destination =
                    new File(
                            folder,
                            testName + ".png"
                    );

            Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println(
                    "Screenshot saved: "
                            + destination.getPath()
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    // =====================================================
    // CLOSE CHROME
    // CHROME CLOSES ONLY ONCE
    // =====================================================

    @AfterClass
    public void tearDown() {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("ALL LOGIN TEST CASES COMPLETED");
        System.out.println("CLOSING CHROME");
        System.out.println("==========================================");

        if (driver != null) {

            driver.quit();

            System.out.println(
                    "Chrome closed successfully"
            );
        }
    }
}