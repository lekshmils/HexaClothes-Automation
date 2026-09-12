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

public class LoginVerificationTest {

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
                "All Login Verification test cases will execute"
        );

        System.out.println(
                "in the same Chrome browser session."
        );

        System.out.println(
                "================================================="
        );
    }


    // =====================================================
    // OPEN LOGIN PAGE
    // =====================================================

    public void openLoginPage()
            throws InterruptedException {

        /*
         * Navigate to Home page before every test.
         * This does NOT open a new browser.
         * Same Chrome session is used.
         */

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
    // LOGIN_01
    // Verify Login page opens
    // =====================================================

    @Test(priority = 1)
    public void login_01_loginPageOpens()
            throws InterruptedException {

        openLoginPage();

        boolean emailDisplayed =
                driver.findElement(emailField)
                        .isDisplayed();

        boolean passwordDisplayed =
                driver.findElement(passwordField)
                        .isDisplayed();

        System.out.println(
                "LOGIN_01 Actual Result: Login page opened successfully"
        );

        Assert.assertTrue(
                emailDisplayed,
                "Email field is not displayed"
        );

        Assert.assertTrue(
                passwordDisplayed,
                "Password field is not displayed"
        );
    }


    // =====================================================
    // LOGIN_07
    // Verify password masking
    // =====================================================

    @Test(priority = 2)
    public void login_07_passwordMasking()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        String type =
                driver.findElement(passwordField)
                        .getAttribute("type");

        System.out.println(
                "LOGIN_07 Actual Result: Password field type = "
                        + type
        );

        Assert.assertEquals(
                type,
                "password",
                "Password is not masked"
        );
    }


    // =====================================================
    // LOGIN_08
    // Verify Login button
    // =====================================================

    @Test(priority = 3)
    public void login_08_loginButton()
            throws InterruptedException {

        openLoginPage();

        boolean displayed =
                driver.findElement(loginSubmitButton)
                        .isDisplayed();

        boolean enabled =
                driver.findElement(loginSubmitButton)
                        .isEnabled();

        System.out.println(
                "LOGIN_08 Actual Result: Login button is visible and clickable"
        );

        Assert.assertTrue(
                displayed,
                "Login button is not visible"
        );

        Assert.assertTrue(
                enabled,
                "Login button is not enabled"
        );
    }


    // =====================================================
    // LOGIN_17
    // Verify email field accepts valid email
    // =====================================================

    @Test(priority = 4)
    public void login_17_validEmailAccepted()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("test@gmail.com");

        String value =
                driver.findElement(emailField)
                        .getAttribute("value");

        System.out.println(
                "LOGIN_17 Actual Result: "
                        + value
        );

        Assert.assertEquals(
                value,
                "test@gmail.com",
                "Email was not accepted"
        );
    }


    // =====================================================
    // LOGIN_18
    // Verify password field accepts input
    // =====================================================

    @Test(priority = 5)
    public void login_18_passwordAcceptsInput()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        String value =
                driver.findElement(passwordField)
                        .getAttribute("value");

        System.out.println(
                "LOGIN_18 Actual Result: Password input accepted"
        );

        Assert.assertEquals(
                value,
                "Test@123",
                "Password field did not accept input"
        );
    }


    // =====================================================
    // LOGIN_19
    // Verify Login page URL
    // =====================================================

    @Test(priority = 6)
    public void login_19_loginPageURL()
            throws InterruptedException {

        openLoginPage();

        String currentURL =
                driver.getCurrentUrl();

        System.out.println(
                "LOGIN_19 Actual Result: "
                        + currentURL
        );

        Assert.assertTrue(
                currentURL.contains("/login"),
                "Incorrect Login page URL"
        );
    }


    // =====================================================
    // LOGIN_20
    // Verify Login page title
    // =====================================================

    @Test(priority = 7)
    public void login_20_loginPageTitle()
            throws InterruptedException {

        openLoginPage();

        String title =
                driver.getTitle();

        System.out.println(
                "LOGIN_20 Actual Result: "
                        + title
        );

        Assert.assertFalse(
                title.isEmpty(),
                "Page title is empty"
        );
    }


    // =====================================================
    // LOGIN_21
    // Verify email field available
    // =====================================================

    @Test(priority = 8)
    public void login_21_emailFieldAvailable()
            throws InterruptedException {

        openLoginPage();

        boolean displayed =
                driver.findElement(emailField)
                        .isDisplayed();

        System.out.println(
                "LOGIN_21 Actual Result: Email field available"
        );

        Assert.assertTrue(
                displayed,
                "Email field is not available"
        );
    }


    // =====================================================
    // LOGIN_22
    // Verify password field available
    // =====================================================

    @Test(priority = 9)
    public void login_22_passwordFieldAvailable()
            throws InterruptedException {

        openLoginPage();

        boolean displayed =
                driver.findElement(passwordField)
                        .isDisplayed();

        System.out.println(
                "LOGIN_22 Actual Result: Password field available"
        );

        Assert.assertTrue(
                displayed,
                "Password field is not available"
        );
    }


    // =====================================================
    // LOGIN_23
    // Verify Login button available
    // =====================================================

    @Test(priority = 10)
    public void login_23_loginButtonAvailable()
            throws InterruptedException {

        openLoginPage();

        boolean displayed =
                driver.findElement(loginSubmitButton)
                        .isDisplayed();

        System.out.println(
                "LOGIN_23 Actual Result: Login button available"
        );

        Assert.assertTrue(
                displayed,
                "Login button is not available"
        );
    }


    // =====================================================
    // LOGIN_41
    // Verify page after unsuccessful login
    // =====================================================

    @Test(priority = 11)
    public void login_41_pageAfterUnsuccessfulLogin()
            throws InterruptedException {

        openLoginPage();

        driver.findElement(emailField)
                .sendKeys("abc123");

        driver.findElement(passwordField)
                .sendKeys("Wrong@123");

        driver.findElement(loginSubmitButton)
                .click();

        String validationMessage =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        boolean stillOnLoginPage =
                driver.findElement(emailField)
                        .isDisplayed();

        System.out.println(
                "LOGIN_41 Actual Result: User remained on Login page"
        );

        System.out.println(
                "Validation Message: "
                        + validationMessage
        );

        Assert.assertTrue(
                stillOnLoginPage,
                "User did not remain on Login page"
        );
    }


    // =====================================================
    // LOGIN_42
    // Verify Login page after browser refresh
    // =====================================================

    @Test(priority = 12)
    public void login_42_browserRefresh()
            throws InterruptedException {

        openLoginPage();

        driver.navigate().refresh();

        String url =
                driver.getCurrentUrl();

        System.out.println(
                "LOGIN_42 Actual Result: "
                        + url
        );

        Assert.assertTrue(
                url.contains("/login"),
                "DEFECT: Login page was not available after refresh"
        );
    }


    // =====================================================
    // LOGIN_43
    // Verify browser Back navigation
    // =====================================================

    @Test(priority = 13)
    public void login_43_browserBack()
            throws InterruptedException {

        openLoginPage();

        driver.navigate().back();

        wait.until(
                ExpectedConditions.urlToBe(baseUrl)
        );

        String url =
                driver.getCurrentUrl();

        System.out.println(
                "LOGIN_43 Actual Result: "
                        + url
        );

        Assert.assertEquals(
                url,
                baseUrl,
                "Previous Home page did not open"
        );
    }


    // =====================================================
    // LOGIN_44
    // Verify browser Forward navigation
    // =====================================================

    @Test(priority = 14)
    public void login_44_browserForward()
            throws InterruptedException {

        openLoginPage();

        driver.navigate().back();

        wait.until(
                ExpectedConditions.urlToBe(baseUrl)
        );

        driver.navigate().forward();

        wait.until(
                ExpectedConditions.urlContains("/login")
        );

        String url =
                driver.getCurrentUrl();

        System.out.println(
                "LOGIN_44 Actual Result: "
                        + url
        );

        Assert.assertTrue(
                url.contains("/login"),
                "Login page did not open after Forward"
        );
    }


    // =====================================================
    // LOGIN_45
    // Verify multiple login attempts
    // =====================================================

    @Test(priority = 15)
    public void login_45_multipleLoginAttempts()
            throws InterruptedException {

        openLoginPage();

        // =================================================
        // FIRST ATTEMPT
        // =================================================

        driver.findElement(emailField)
                .sendKeys("abc123");

        driver.findElement(passwordField)
                .sendKeys("Wrong@123");

        driver.findElement(loginSubmitButton)
                .click();

        String firstMessage =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        System.out.println(
                "First attempt validation: "
                        + firstMessage
        );


        // =================================================
        // SECOND ATTEMPT
        // =================================================

        driver.findElement(emailField)
                .clear();

        driver.findElement(passwordField)
                .clear();

        driver.findElement(emailField)
                .sendKeys("abc123");

        driver.findElement(passwordField)
                .sendKeys("Wrong@123");

        driver.findElement(loginSubmitButton)
                .click();

        String secondMessage =
                driver.findElement(emailField)
                        .getAttribute("validationMessage");

        System.out.println(
                "Second attempt validation: "
                        + secondMessage
        );

        Assert.assertFalse(
                secondMessage.isEmpty(),
                "Repeated login attempt was not handled"
        );
    }


    // =====================================================
    // LOGIN_46
    // Login after reopening browser
    //
    // IMPORTANT:
    // Chrome is NOT closed and reopened.
    // Same Chrome session is reused.
    // =====================================================

    @Test(priority = 16)
    public void login_46_reopenBrowser()
            throws InterruptedException {

        /*
         * Instead of:
         *
         * driver.quit();
         * driver = new ChromeDriver();
         *
         * we simply navigate back to the Home page.
         *
         * This keeps only ONE Chrome browser session.
         */

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

        driver.findElement(emailField)
                .sendKeys("test@gmail.com");

        driver.findElement(passwordField)
                .sendKeys("Test@123");

        driver.findElement(loginSubmitButton)
                .click();

        boolean loggedIn;

        try {

            loggedIn =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    logoutButton
                            )
                    ).isDisplayed();

        } catch (Exception e) {

            loggedIn = false;
        }

        System.out.println(
                "LOGIN_46 Actual Result: "
                        + (loggedIn
                        ? "Login working normally"
                        : "Login failed")
        );

        Assert.assertTrue(
                loggedIn,
                "Login did not work"
        );
    }


    // =====================================================
    // LOGIN_47
    // Verify password field type
    // =====================================================

    @Test(priority = 17)
    public void login_47_passwordFieldType()
            throws InterruptedException {

        openLoginPage();

        String type =
                driver.findElement(passwordField)
                        .getAttribute("type");

        System.out.println(
                "LOGIN_47 Actual Result: Field type = "
                        + type
        );

        Assert.assertEquals(
                type,
                "password",
                "Password field type is not password"
        );
    }


    // =====================================================
    // LOGIN_48
    // Verify email field type
    // =====================================================

    @Test(priority = 18)
    public void login_48_emailFieldType()
            throws InterruptedException {

        openLoginPage();

        String type =
                driver.findElement(emailField)
                        .getAttribute("type");

        System.out.println(
                "LOGIN_48 Actual Result: Field type = "
                        + type
        );

        Assert.assertEquals(
                type,
                "email",
                "Email field type is not email"
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
                "All Login Verification test cases completed."
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