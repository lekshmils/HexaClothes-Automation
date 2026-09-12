package Cart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartGeneralTest {

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

    By productCard = By.xpath(
            "//div[contains(@class,'bg-white') and .//h2][1]"
    );

    By productName = By.xpath(
            "//div[contains(@class,'bg-white') and .//h2][1]//h2"
    );

    By productImage = By.xpath(
            "//div[contains(@class,'bg-white') and .//h2][1]//img"
    );

    By productPrice = By.xpath(
            "//div[contains(@class,'bg-white') and .//h2][1]//p"
    );

    By minusButton = By.xpath(
            "//div[contains(@class,'bg-white') and .//h2][1]"
            + "//div[contains(@class,'flex') and contains(@class,'items-center')]"
            + "//button[1]"
    );

    By quantity = By.xpath(
            "//div[contains(@class,'bg-white') and .//h2][1]"
            + "//div[contains(@class,'flex') and contains(@class,'items-center')]"
            + "//span"
    );

    By plusButton = By.xpath(
            "//div[contains(@class,'bg-white') and .//h2][1]"
            + "//div[contains(@class,'flex') and contains(@class,'items-center')]"
            + "//button[2]"
    );

    By removeButton = By.xpath(
            "//div[contains(@class,'bg-white') and .//h2][1]"
            + "//button[contains(normalize-space(),'Remove')]"
    );

    By cartSummary = By.xpath(
            "//div[contains(@class,'bg-white') "
            + "and .//h2[contains(normalize-space(),'Cart Summary')]]"
    );

    By totalItems = By.xpath(
            "//span[contains(normalize-space(),'Total Items:')]"
            + "/following-sibling::span"
    );

    By totalPrice = By.xpath(
            "//span[contains(normalize-space(),'Total Price:')]"
            + "/following-sibling::span"
    );

    By checkoutButton = By.xpath(
            "//button[contains(normalize-space(),'Proceed to Checkout')]"
    );


    // ============================================================
    // SETUP
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

        // Website opened only once
        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        addToCartButton
                )
        );

        System.out.println(
                "================================================"
        );

        System.out.println(
                "Chrome opened successfully"
        );

        System.out.println(
                "Home page opened"
        );

        System.out.println(
                "Browser will remain open for all Cart tests"
        );

        System.out.println(
                "================================================"
        );
    }


    // ============================================================
    // BEFORE EACH TEST
    // DOES NOT CREATE NEW CHROME
    // ============================================================

    @BeforeMethod
    public void resetTestState() {

        /*
         * The same Chrome browser is reused.
         *
         * We clear local storage so that the previous test's
         * cart data does not affect the next test.
         */

        driver.get(baseUrl);

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("window.localStorage.clear();");

        driver.navigate().refresh();

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        addToCartButton
                )
        );

        System.out.println(
                "Test state reset - Same Chrome browser reused"
        );
    }


    // ============================================================
    // COMMON METHOD
    // HOME → ADD TO CART → CART
    // ============================================================

    private void openCartAfterAddingProduct()
            throws InterruptedException {

        // Make sure Home page is ready

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        addToCartButton
                )
        ).click();

        Thread.sleep(700);

        // Navigate to Cart

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        cartNav
                )
        ).click();

        // Wait for Cart page

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productCard
                )
        );

        System.out.println(
                "Product added and Cart opened"
        );
    }


    // ============================================================
    // FUNCTIONAL TEST 01
    // Verify product can be added to Cart
    // ============================================================

    @Test(priority = 1)
    public void verifyAddProductToCart()
            throws InterruptedException {

        openCartAfterAddingProduct();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                productCard
                        )
                ).isDisplayed(),
                "Product was not added to Cart"
        );

        System.out.println(
                "CART_FUNCTIONAL_01 - Product added successfully"
        );
    }


    // ============================================================
    // FUNCTIONAL TEST 02
    // Verify Cart navigation works
    // ============================================================

    @Test(priority = 2)
    public void verifyCartNavigation()
            throws InterruptedException {

        openCartAfterAddingProduct();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/cart"),
                "Cart navigation failed"
        );

        System.out.println(
                "CART_FUNCTIONAL_02 - Cart navigation successful"
        );
    }


    // ============================================================
    // FUNCTIONAL TEST 03
    // Verify quantity can be increased
    // ============================================================

    @Test(priority = 3)
    public void verifyIncreaseQuantity()
            throws InterruptedException {

        openCartAfterAddingProduct();

        int before = getQuantity();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        int after = getQuantity();

        Assert.assertEquals(
                after,
                before + 1,
                "Quantity was not increased"
        );

        System.out.println(
                "CART_FUNCTIONAL_03 - Quantity increased from "
                + before + " to " + after
        );
    }


    // ============================================================
    // FUNCTIONAL TEST 04
    // Verify quantity can be decreased
    // ============================================================

    @Test(priority = 4)
    public void verifyDecreaseQuantity()
            throws InterruptedException {

        openCartAfterAddingProduct();

        // Increase quantity first

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        int before = getQuantity();

        // Decrease quantity

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        minusButton
                )
        ).click();

        Thread.sleep(500);

        int after = getQuantity();

        Assert.assertEquals(
                after,
                before - 1,
                "Quantity was not decreased"
        );

        System.out.println(
                "CART_FUNCTIONAL_04 - Quantity decreased successfully"
        );
    }


    // ============================================================
    // FUNCTIONAL TEST 05
    // Verify product can be removed
    // ============================================================

    @Test(priority = 5)
    public void verifyRemoveProduct()
            throws InterruptedException {

        openCartAfterAddingProduct();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        removeButton
                )
        ).click();

        Thread.sleep(700);

        boolean productPresent;

        try {

            productPresent =
                    driver.findElement(
                            productCard
                    ).isDisplayed();

        } catch (Exception e) {

            productPresent = false;
        }

        Assert.assertFalse(
                productPresent,
                "Product was not removed"
        );

        System.out.println(
                "CART_FUNCTIONAL_05 - Product removed successfully"
        );
    }


    // ============================================================
    // FUNCTIONAL TEST 06
    // Verify multiple quantity
    // ============================================================

    @Test(priority = 6)
    public void verifyMultipleQuantity()
            throws InterruptedException {

        openCartAfterAddingProduct();

        // Quantity 1 → 2

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(400);

        // Quantity 2 → 3

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        int actualQuantity =
                getQuantity();

        Assert.assertEquals(
                actualQuantity,
                3,
                "Quantity did not become 3"
        );

        System.out.println(
                "CART_FUNCTIONAL_06 - Multiple quantity added: "
                + actualQuantity
        );
    }


    // ============================================================
    // FUNCTIONAL TEST 07
    // Verify total items update
    // ============================================================

    @Test(priority = 7)
    public void verifyTotalItemsUpdate()
            throws InterruptedException {

        openCartAfterAddingProduct();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        int items =
                getTotalItems();

        Assert.assertEquals(
                items,
                2,
                "Total Items was not updated"
        );

        System.out.println(
                "CART_FUNCTIONAL_07 - Total Items updated to "
                + items
        );
    }


    // ============================================================
    // FUNCTIONAL TEST 08
    // Verify total price updates
    // ============================================================

    @Test(priority = 8)
    public void verifyTotalPriceUpdate()
            throws InterruptedException {

        openCartAfterAddingProduct();

        double productPrice =
                getProductPrice();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        double actualTotal =
                getTotalPrice();

        double expectedTotal =
                productPrice * 2;

        Assert.assertEquals(
                actualTotal,
                expectedTotal,
                0.01,
                "Total Price was not updated correctly"
        );

        System.out.println(
                "CART_FUNCTIONAL_08 - Total Price updated correctly"
        );

        System.out.println(
                "Expected: $" + expectedTotal
        );

        System.out.println(
                "Actual: $" + actualTotal
        );
    }


    // ============================================================
    // FUNCTIONAL TEST 09
    // Verify checkout button works
    // ============================================================

    @Test(priority = 9)
    public void verifyProceedToCheckout()
            throws InterruptedException {

        openCartAfterAddingProduct();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        checkoutButton
                )
        ).click();

        Thread.sleep(1000);

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.tagName("body")
                )
        );

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .toLowerCase()
                        .contains("checkout"),
                "Checkout page did not open"
        );

        System.out.println(
                "CART_FUNCTIONAL_09 - Checkout navigation successful"
        );
    }


    // ============================================================
    // FUNCTIONAL TEST 10
    // Verify Cart summary updates after quantity change
    // ============================================================

    @Test(priority = 10)
    public void verifyCartSummaryUpdate()
            throws InterruptedException {

        openCartAfterAddingProduct();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                cartSummary
                        )
                ).isDisplayed(),
                "Cart Summary is not displayed"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        int items =
                getTotalItems();

        Assert.assertEquals(
                items,
                2,
                "Cart Summary did not update"
        );

        System.out.println(
                "CART_FUNCTIONAL_10 - Cart Summary updated successfully"
        );
    }


    // ============================================================
    // FUNCTIONAL TEST 11
    // Verify remove makes Cart empty
    // ============================================================

    @Test(priority = 11)
    public void verifyCartBecomesEmpty()
            throws InterruptedException {

        openCartAfterAddingProduct();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        removeButton
                )
        ).click();

        Thread.sleep(700);

        String pageText =
                driver.findElement(
                        By.tagName("body")
                ).getText();

        Assert.assertTrue(
                pageText.toLowerCase().contains("empty"),
                "Cart did not become empty"
        );

        System.out.println(
                "CART_FUNCTIONAL_11 - Cart became empty"
        );
    }


    // ============================================================
    // FUNCTIONAL TEST 12
    // Verify price calculation after quantity 3
    // ============================================================

    @Test(priority = 12)
    public void verifyPriceCalculation()
            throws InterruptedException {

        openCartAfterAddingProduct();

        double price =
                getProductPrice();

        // Quantity 1 → 2

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(400);

        // Quantity 2 → 3

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        int qty =
                getQuantity();

        double actualTotal =
                getTotalPrice();

        double expectedTotal =
                price * qty;

        Assert.assertEquals(
                actualTotal,
                expectedTotal,
                0.01,
                "Price calculation is incorrect"
        );

        System.out.println(
                "CART_FUNCTIONAL_12 - Price calculation verified"
        );

        System.out.println(
                "Price: $" + price
        );

        System.out.println(
                "Quantity: " + qty
        );

        System.out.println(
                "Total: $" + actualTotal
        );
    }


    // ============================================================
    // HELPER METHOD - PRODUCT PRICE
    // ============================================================

    private double getProductPrice() {

        String text =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                productPrice
                        )
                ).getText();

        text = text.replaceAll(
                "[^0-9.]",
                ""
        );

        return Double.parseDouble(text);
    }


    // ============================================================
    // HELPER METHOD - TOTAL PRICE
    // ============================================================

    private double getTotalPrice() {

        String text =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                totalPrice
                        )
                ).getText();

        text = text.replaceAll(
                "[^0-9.]",
                ""
        );

        return Double.parseDouble(text);
    }


    // ============================================================
    // HELPER METHOD - QUANTITY
    // ============================================================

    private int getQuantity() {

        String text =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                quantity
                        )
                ).getText();

        return Integer.parseInt(
                text.trim()
        );
    }


    // ============================================================
    // HELPER METHOD - TOTAL ITEMS
    // ============================================================

    private int getTotalItems() {

        String text =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                totalItems
                        )
                ).getText();

        return Integer.parseInt(
                text.trim()
        );
    }


    // ============================================================
    // TEARDOWN
    // CHROME CLOSES ONLY ONCE
    // ============================================================

    @AfterClass
    public void tearDown() {

        if (driver != null) {

            driver.quit();
        }

        System.out.println(
                "================================================"
        );

        System.out.println(
                "All Cart Functional Tests completed"
        );

        System.out.println(
                "Chrome browser closed"
        );

        System.out.println(
                "================================================"
        );
    }
}