package Cart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartVerificationTest {

    WebDriver driver;
    WebDriverWait wait;

    String baseUrl = "https://hexaclothes.netlify.app/";

    // ============================================================
    // HOME PAGE LOCATORS
    // ============================================================

    By addToCartButton =
            By.xpath("//button[contains(normalize-space(),'Add to Cart')]");

    By cartNav =
            By.cssSelector("nav a[href*='cart']");


    // ============================================================
    // CART LOCATORS
    // ============================================================

    By productCard =
            By.xpath("//div[contains(@class,'bg-white') and .//h2][1]");

    By productName =
            By.xpath(
                "//div[contains(@class,'bg-white') and .//h2][1]//h2"
            );

    By productImage =
            By.xpath(
                "//div[contains(@class,'bg-white') and .//h2][1]//img"
            );

    By productPrice =
            By.xpath(
                "//div[contains(@class,'bg-white') and .//h2][1]//p"
            );

    By minusButton =
            By.xpath(
                "//div[contains(@class,'bg-white') and .//h2][1]"
                + "//div[contains(@class,'flex') and contains(@class,'items-center')]"
                + "//button[1]"
            );

    By quantity =
            By.xpath(
                "//div[contains(@class,'bg-white') and .//h2][1]"
                + "//div[contains(@class,'flex') and contains(@class,'items-center')]"
                + "//span"
            );

    By plusButton =
            By.xpath(
                "//div[contains(@class,'bg-white') and .//h2][1]"
                + "//div[contains(@class,'flex') and contains(@class,'items-center')]"
                + "//button[2]"
            );

    By removeButton =
            By.xpath(
                "//div[contains(@class,'bg-white') and .//h2][1]"
                + "//button[contains(normalize-space(),'Remove')]"
            );

    By cartSummary =
            By.xpath(
                "//div[contains(@class,'bg-white') "
                + "and .//h2[contains(normalize-space(),'Cart Summary')]]"
            );

    By totalItems =
            By.xpath(
                "//span[contains(normalize-space(),'Total Items:')]"
                + "/following-sibling::span"
            );

    By totalPrice =
            By.xpath(
                "//span[contains(normalize-space(),'Total Price:')]"
                + "/following-sibling::span"
            );

    By checkoutButton =
            By.xpath(
                "//button[contains(normalize-space(),'Proceed to Checkout')]"
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
                "Same browser will be used for all tests"
        );

        System.out.println(
                "================================================"
        );
    }


    // ============================================================
    // BEFORE METHOD
    // RESET TEST STATE
    //
    // IMPORTANT:
    // DOES NOT OPEN NEW CHROME
    // ============================================================

    @BeforeMethod
    public void resetTestState() {

        /*
         * Go back to Home page before every test.
         *
         * The SAME Chrome browser is reused.
         */

        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        addToCartButton
                )
        );

        /*
         * Clear browser local storage.
         *
         * This prevents the previous test's cart data
         * from affecting the next test.
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

        System.out.println(
                "------------------------------------------------"
        );

        System.out.println(
                "Test state reset"
        );

        System.out.println(
                "Same Chrome browser reused"
        );

        System.out.println(
                "------------------------------------------------"
        );
    }


    // ============================================================
    // COMMON METHOD
    // ADD PRODUCT AND OPEN CART
    // ============================================================

    private void navigateToCart()
            throws InterruptedException {

        // --------------------------------------------------------
        // STEP 1
        // Add product to Cart
        // --------------------------------------------------------

        WebElement addCart =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                addToCartButton
                        )
                );

        addCart.click();

        Thread.sleep(700);

        System.out.println(
                "Product added to Cart"
        );


        // --------------------------------------------------------
        // STEP 2
        // Open Cart
        // --------------------------------------------------------

        WebElement cart =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                cartNav
                        )
                );

        cart.click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productCard
                )
        );

        System.out.println(
                "Cart page opened"
        );
    }


    // ============================================================
    // COMMON METHOD
    // GET PRODUCT PRICE
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
    // COMMON METHOD
    // GET TOTAL PRICE
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
    // COMMON METHOD
    // GET QUANTITY
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
    // COMMON METHOD
    // GET TOTAL ITEMS
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
    // TEST 01
    // Verify Product Added From Home Appears In Cart
    // ============================================================

    @Test(priority = 1)
    public void verifyProductAddedFromHome()
            throws InterruptedException {

        navigateToCart();

        WebElement product =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                productCard
                        )
                );

        Assert.assertTrue(
                product.isDisplayed(),
                "Added product is not displayed in Cart"
        );

        System.out.println(
                "CART_VERIFICATION_01 - PASSED"
        );
    }


    // ============================================================
    // TEST 02
    // Verify Product Name Is Retained
    // ============================================================

    @Test(priority = 2)
    public void verifyProductNameAfterNavigation()
            throws InterruptedException {

        navigateToCart();

        String name =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                productName
                        )
                ).getText();

        Assert.assertFalse(
                name.trim().isEmpty(),
                "Product name is missing"
        );

        System.out.println(
                "CART_VERIFICATION_02 - PASSED"
        );

        System.out.println(
                "Product Name: " + name
        );
    }


    // ============================================================
    // TEST 03
    // Verify Product Image Is Retained
    // ============================================================

    @Test(priority = 3)
    public void verifyProductImageAfterNavigation()
            throws InterruptedException {

        navigateToCart();

        WebElement image =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                productImage
                        )
                );

        Assert.assertTrue(
                image.isDisplayed(),
                "Product image is not displayed"
        );

        System.out.println(
                "CART_VERIFICATION_03 - PASSED"
        );
    }


    // ============================================================
    // TEST 04
    // Verify Initial Quantity Is One
    // ============================================================

    @Test(priority = 4)
    public void verifyInitialQuantity()
            throws InterruptedException {

        navigateToCart();

        int actual =
                getQuantity();

        Assert.assertEquals(
                actual,
                1,
                "Initial quantity should be 1"
        );

        System.out.println(
                "CART_VERIFICATION_04 - PASSED"
        );

        System.out.println(
                "Initial Quantity: " + actual
        );
    }


    // ============================================================
    // TEST 05
    // Verify Plus Button Updates Quantity
    // ============================================================

    @Test(priority = 5)
    public void verifyPlusButtonFunction()
            throws InterruptedException {

        navigateToCart();

        int before =
                getQuantity();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        int after =
                getQuantity();

        Assert.assertEquals(
                after,
                before + 1,
                "Plus button did not increase quantity"
        );

        System.out.println(
                "CART_VERIFICATION_05 - PASSED"
        );

        System.out.println(
                "Before: " + before
        );

        System.out.println(
                "After: " + after
        );
    }


    // ============================================================
    // TEST 06
    // Verify Minus Button Updates Quantity
    // ============================================================

    @Test(priority = 6)
    public void verifyMinusButtonFunction()
            throws InterruptedException {

        navigateToCart();

        // Increase first so we can safely decrease

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        int before =
                getQuantity();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        minusButton
                )
        ).click();

        Thread.sleep(500);

        int after =
                getQuantity();

        Assert.assertEquals(
                after,
                before - 1,
                "Minus button did not decrease quantity"
        );

        System.out.println(
                "CART_VERIFICATION_06 - PASSED"
        );

        System.out.println(
                "Before: " + before
        );

        System.out.println(
                "After: " + after
        );
    }


    // ============================================================
    // TEST 07
    // Verify Total Items Updates
    // ============================================================

    @Test(priority = 7)
    public void verifyTotalItemsUpdate()
            throws InterruptedException {

        navigateToCart();

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
                "Total Items was not updated to 2"
        );

        System.out.println(
                "CART_VERIFICATION_07 - PASSED"
        );

        System.out.println(
                "Total Items: " + items
        );
    }


    // ============================================================
    // TEST 08
    // Verify Total Price Updates
    // ============================================================

    @Test(priority = 8)
    public void verifyTotalPriceUpdate()
            throws InterruptedException {

        navigateToCart();

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
                "CART_VERIFICATION_08 - PASSED"
        );

        System.out.println(
                "Product Price: $" + productPrice
        );

        System.out.println(
                "Expected Total: $" + expectedTotal
        );

        System.out.println(
                "Actual Total: $" + actualTotal
        );
    }


    // ============================================================
    // TEST 09
    // Verify Price Calculation For Quantity Three
    // ============================================================

    @Test(priority = 9)
    public void verifyPriceCalculationForThree()
            throws InterruptedException {

        navigateToCart();

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

        double actual =
                getTotalPrice();

        double expected =
                price * qty;

        Assert.assertEquals(
                actual,
                expected,
                0.01,
                "Price calculation is incorrect"
        );

        System.out.println(
                "CART_VERIFICATION_09 - PASSED"
        );

        System.out.println(
                "Price: $" + price
        );

        System.out.println(
                "Quantity: " + qty
        );

        System.out.println(
                "Total: $" + actual
        );
    }


    // ============================================================
    // TEST 10
    // Verify Price Returns After Decrease
    // ============================================================

    @Test(priority = 10)
    public void verifyPriceAfterDecrease()
            throws InterruptedException {

        navigateToCart();

        double originalPrice =
                getProductPrice();

        // Increase quantity

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(400);

        // Decrease quantity

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        minusButton
                )
        ).click();

        Thread.sleep(500);

        double actual =
                getTotalPrice();

        Assert.assertEquals(
                actual,
                originalPrice,
                0.01,
                "Total price did not return to original price"
        );

        System.out.println(
                "CART_VERIFICATION_10 - PASSED"
        );
    }


    // ============================================================
    // TEST 11
    // Verify Remove Product
    // ============================================================

    @Test(priority = 11)
    public void verifyRemoveProduct()
            throws InterruptedException {

        navigateToCart();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        removeButton
                )
        ).click();

        Thread.sleep(700);

        boolean productStillPresent;

        try {

            productStillPresent =
                    driver.findElement(
                            productCard
                    ).isDisplayed();

        } catch (Exception e) {

            productStillPresent = false;
        }

        Assert.assertFalse(
                productStillPresent,
                "Product was not removed from Cart"
        );

        System.out.println(
                "CART_VERIFICATION_11 - PASSED"
        );
    }


    // ============================================================
    // TEST 12
    // Verify Checkout Button Is Available
    // ============================================================

    @Test(priority = 12)
    public void verifyCheckoutButton()
            throws InterruptedException {

        navigateToCart();

        WebElement checkout =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                checkoutButton
                        )
                );

        Assert.assertTrue(
                checkout.isDisplayed(),
                "Proceed to Checkout button is not displayed"
        );

        Assert.assertTrue(
                checkout.isEnabled(),
                "Proceed to Checkout button is disabled"
        );

        System.out.println(
                "CART_VERIFICATION_12 - PASSED"
        );
    }


    // ============================================================
    // TEST 13
    // Verify Cart Summary Contains Total Items
    // ============================================================

    @Test(priority = 13)
    public void verifyCartSummaryTotalItems()
            throws InterruptedException {

        navigateToCart();

        WebElement summary =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                cartSummary
                        )
                );

        Assert.assertTrue(
                summary.getText().contains(
                        "Total Items"
                ),
                "Total Items is not shown in Cart Summary"
        );

        System.out.println(
                "CART_VERIFICATION_13 - PASSED"
        );
    }


    // ============================================================
    // TEST 14
    // Verify Cart Summary Contains Total Price
    // ============================================================

    @Test(priority = 14)
    public void verifyCartSummaryTotalPrice()
            throws InterruptedException {

        navigateToCart();

        WebElement summary =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                cartSummary
                        )
                );

        Assert.assertTrue(
                summary.getText().contains(
                        "Total Price"
                ),
                "Total Price is not shown in Cart Summary"
        );

        System.out.println(
                "CART_VERIFICATION_14 - PASSED"
        );
    }


    // ============================================================
    // TEST 15
    // Verify Product Price And Cart Total Match
    // ============================================================

    @Test(priority = 15)
    public void verifyProductPriceAndCartTotal()
            throws InterruptedException {

        navigateToCart();

        double productPrice =
                getProductPrice();

        int qty =
                getQuantity();

        double total =
                getTotalPrice();

        double expected =
                productPrice * qty;

        Assert.assertEquals(
                total,
                expected,
                0.01,
                "Product price and Cart total do not match"
        );

        System.out.println(
                "CART_VERIFICATION_15 - PASSED"
        );
    }


    // ============================================================
    // TEST 16
    // Verify Cart Navigation From Home
    // ============================================================

    @Test(priority = 16)
    public void verifyCartNavigationFromHome()
            throws InterruptedException {

        WebElement addCart =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                addToCartButton
                        )
                );

        addCart.click();

        Thread.sleep(700);

        WebElement cart =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                cartNav
                        )
                );

        cart.click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        cartSummary
                )
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/cart"),
                "Cart navigation failed"
        );

        System.out.println(
                "CART_VERIFICATION_16 - PASSED"
        );
    }


    // ============================================================
    // TEST 17
    // Verify Multiple Quantity And Total Items Together
    // ============================================================

    @Test(priority = 17)
    public void verifyQuantityAndTotalItemsTogether()
            throws InterruptedException {

        navigateToCart();

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

        int items =
                getTotalItems();

        Assert.assertEquals(
                qty,
                items,
                "Quantity and Total Items do not match"
        );

        System.out.println(
                "CART_VERIFICATION_17 - PASSED"
        );

        System.out.println(
                "Quantity: " + qty
        );

        System.out.println(
                "Total Items: " + items
        );
    }


    // ============================================================
    // TEST 18
    // Verify Quantity Does Not Become Zero
    // ============================================================

    @Test(priority = 18)
    public void verifyQuantityMinimumValue()
            throws InterruptedException {

        navigateToCart();

        int before =
                getQuantity();

        Assert.assertEquals(
                before,
                1,
                "Initial quantity should be 1"
        );

        /*
         * Click minus button at quantity 1.
         *
         * If the application prevents the quantity from
         * becoming zero, the quantity should remain 1.
         */

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        minusButton
                )
        ).click();

        Thread.sleep(500);

        /*
         * Check whether product is still present.
         */

        boolean productPresent;

        try {

            productPresent =
                    driver.findElement(
                            productCard
                    ).isDisplayed();

        } catch (Exception e) {

            productPresent = false;
        }

        if (productPresent) {

            int after =
                    getQuantity();

            Assert.assertTrue(
                    after >= 1,
                    "Quantity became less than 1"
            );

            System.out.println(
                    "Quantity after minus: " + after
            );

        } else {

            /*
             * Some applications remove the product when
             * quantity reaches zero.
             *
             * In that case the cart becomes empty.
             */

            String pageText =
                    driver.findElement(
                            By.tagName("body")
                    ).getText();

            Assert.assertTrue(
                    pageText.toLowerCase().contains("empty"),
                    "Unexpected Cart state after decreasing quantity"
            );

            System.out.println(
                    "Cart became empty when quantity was decreased from 1"
            );
        }

        System.out.println(
                "CART_VERIFICATION_18 - PASSED"
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

        System.out.println(
                "================================================"
        );

        System.out.println(
                "All 18 Cart Verification Tests completed"
        );

        System.out.println(
                "Same Chrome browser was used"
        );

        System.out.println(
                "Chrome browser closed successfully"
        );

        System.out.println(
                "================================================"
        );
    }
}