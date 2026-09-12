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

public class CartValidationTest {

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

    By productImage = By.xpath(
            "//div[contains(@class,'bg-white') and .//h2][1]//img"
    );

    By productName = By.xpath(
            "//div[contains(@class,'bg-white') and .//h2][1]//h2"
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

        // Website opens only once
        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        addToCartButton
                )
        );

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "Chrome opened successfully"
        );

        System.out.println(
                "Home page opened successfully"
        );

        System.out.println(
                "Browser will be reused for all tests"
        );

        System.out.println(
                "=========================================="
        );
    }


    // ============================================================
    // BEFORE EACH TEST
    // SAME BROWSER - NO NEW CHROME
    // ============================================================

    @BeforeMethod
    public void resetBeforeTest()
            throws InterruptedException {

        // Navigate the existing browser back to Home
        driver.get(baseUrl);

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        addToCartButton
                )
        );

        Thread.sleep(500);

        System.out.println(
                "Application reset for next test"
        );
    }


    // ============================================================
    // COMMON METHOD
    // ADD PRODUCT AND NAVIGATE TO CART
    // ============================================================

    private void addProductAndNavigateToCart()
            throws InterruptedException {

        WebElement addCart = wait.until(
                ExpectedConditions.elementToBeClickable(
                        addToCartButton
                )
        );

        addCart.click();

        Thread.sleep(700);

        WebElement cart = wait.until(
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
                "Product added from Home page"
        );

        System.out.println(
                "Navigated to Cart"
        );
    }


    // ============================================================
    // GET PRODUCT PRICE
    // ============================================================

    private double getProductPrice() {

        String priceText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productPrice
                )
        ).getText();

        priceText = priceText.replaceAll(
                "[^0-9.]",
                ""
        );

        return Double.parseDouble(priceText);
    }


    // ============================================================
    // GET TOTAL PRICE
    // ============================================================

    private double getTotalPrice() {

        String totalText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        totalPrice
                )
        ).getText();

        totalText = totalText.replaceAll(
                "[^0-9.]",
                ""
        );

        return Double.parseDouble(totalText);
    }


    // ============================================================
    // GET QUANTITY
    // ============================================================

    private int getQuantity() {

        String quantityText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        quantity
                )
        ).getText();

        return Integer.parseInt(
                quantityText.trim()
        );
    }


    // ============================================================
    // GET TOTAL ITEMS
    // ============================================================

    private int getTotalItems() {

        String itemText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        totalItems
                )
        ).getText();

        return Integer.parseInt(
                itemText.trim()
        );
    }


    // ============================================================
    // CART_01
    // Verify Cart Page Opens
    // ============================================================

    @Test(priority = 1)
    public void verifyCartPageOpens()
            throws InterruptedException {

        addProductAndNavigateToCart();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/cart"),
                "Cart page did not open"
        );

        System.out.println(
                "CART_01 - PASSED"
        );
    }


    // ============================================================
    // CART_02
    // Verify Product Displayed
    // ============================================================

    @Test(priority = 2)
    public void verifyProductDisplayedInCart()
            throws InterruptedException {

        addProductAndNavigateToCart();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                productCard
                        )
                ).isDisplayed(),
                "Product is not displayed in Cart"
        );

        System.out.println(
                "CART_02 - PASSED"
        );
    }


    // ============================================================
    // CART_03
    // Verify Product Image Displayed
    // ============================================================

    @Test(priority = 3)
    public void verifyProductImageDisplayed()
            throws InterruptedException {

        addProductAndNavigateToCart();

        WebElement image = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productImage
                )
        );

        Assert.assertTrue(
                image.isDisplayed(),
                "Product image is not displayed"
        );

        System.out.println(
                "CART_03 - PASSED"
        );
    }


    // ============================================================
    // CART_04
    // Verify Product Name Displayed
    // ============================================================

    @Test(priority = 4)
    public void verifyProductNameDisplayed()
            throws InterruptedException {

        addProductAndNavigateToCart();

        String name = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productName
                )
        ).getText();

        Assert.assertFalse(
                name.trim().isEmpty(),
                "Product name is empty"
        );

        System.out.println(
                "CART_04 - Product Name: " + name
        );
    }


    // ============================================================
    // CART_05
    // Verify Product Price Displayed
    // ============================================================

    @Test(priority = 5)
    public void verifyProductPriceDisplayed()
            throws InterruptedException {

        addProductAndNavigateToCart();

        double price = getProductPrice();

        Assert.assertTrue(
                price > 0,
                "Product price is invalid"
        );

        System.out.println(
                "CART_05 - Product Price: $" + price
        );
    }


    // ============================================================
    // CART_06
    // Verify Minus Button Displayed
    // ============================================================

    @Test(priority = 6)
    public void verifyMinusButtonDisplayed()
            throws InterruptedException {

        addProductAndNavigateToCart();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                minusButton
                        )
                ).isDisplayed(),
                "Minus button is not displayed"
        );

        System.out.println(
                "CART_06 - PASSED"
        );
    }


    // ============================================================
    // CART_07
    // Verify Plus Button Displayed
    // ============================================================

    @Test(priority = 7)
    public void verifyPlusButtonDisplayed()
            throws InterruptedException {

        addProductAndNavigateToCart();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                plusButton
                        )
                ).isDisplayed(),
                "Plus button is not displayed"
        );

        System.out.println(
                "CART_07 - PASSED"
        );
    }


    // ============================================================
    // CART_08
    // Verify Initial Quantity
    // ============================================================

    @Test(priority = 8)
    public void verifyInitialQuantity()
            throws InterruptedException {

        addProductAndNavigateToCart();

        int actualQuantity = getQuantity();

        Assert.assertEquals(
                actualQuantity,
                1,
                "Initial quantity is not 1"
        );

        System.out.println(
                "CART_08 - Initial Quantity: "
                        + actualQuantity
        );
    }


    // ============================================================
    // CART_09
    // Verify Quantity Increase
    // ============================================================

    @Test(priority = 9)
    public void verifyQuantityIncrease()
            throws InterruptedException {

        addProductAndNavigateToCart();

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
                "Quantity did not increase correctly"
        );

        System.out.println(
                "CART_09 - Quantity increased from "
                        + before + " to " + after
        );
    }


    // ============================================================
    // CART_10
    // Verify Quantity Decrease
    // ============================================================

    @Test(priority = 10)
    public void verifyQuantityDecrease()
            throws InterruptedException {

        addProductAndNavigateToCart();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        int before = getQuantity();

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
                "Quantity did not decrease correctly"
        );

        System.out.println(
                "CART_10 - Quantity decreased from "
                        + before + " to " + after
        );
    }


    // ============================================================
    // CART_11
    // Verify Quantity Does Not Go Below One
    // ============================================================

    @Test(priority = 11)
    public void verifyQuantityDoesNotGoBelowOne()
            throws InterruptedException {

        addProductAndNavigateToCart();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        minusButton
                )
        ).click();

        Thread.sleep(500);

        int after = getQuantity();

        Assert.assertTrue(
                after >= 1,
                "Quantity went below 1"
        );

        System.out.println(
                "CART_11 - Quantity: " + after
        );
    }


    // ============================================================
    // CART_12
    // Verify Total Items
    // ============================================================

    @Test(priority = 12)
    public void verifyTotalItems()
            throws InterruptedException {

        addProductAndNavigateToCart();

        int items = getTotalItems();

        Assert.assertEquals(
                items,
                1,
                "Total Items is not 1"
        );

        System.out.println(
                "CART_12 - Total Items: " + items
        );
    }


    // ============================================================
    // CART_13
    // Verify Total Price
    // ============================================================

    @Test(priority = 13)
    public void verifyTotalPrice()
            throws InterruptedException {

        addProductAndNavigateToCart();

        double productPrice = getProductPrice();

        double total = getTotalPrice();

        Assert.assertEquals(
                total,
                productPrice,
                0.01,
                "Total Price is incorrect"
        );

        System.out.println(
                "CART_13 - Total Price: $" + total
        );
    }


    // ============================================================
    // CART_14
    // Verify Cart Summary
    // ============================================================

    @Test(priority = 14)
    public void verifyCartSummary()
            throws InterruptedException {

        addProductAndNavigateToCart();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                cartSummary
                        )
                ).isDisplayed(),
                "Cart Summary is not displayed"
        );

        System.out.println(
                "CART_14 - PASSED"
        );
    }


    // ============================================================
    // CART_15
    // Verify Total Price After Increase
    // ============================================================

    @Test(priority = 15)
    public void verifyTotalPriceAfterIncrease()
            throws InterruptedException {

        addProductAndNavigateToCart();

        double productPrice = getProductPrice();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        double actualTotal = getTotalPrice();

        double expectedTotal =
                productPrice * 2;

        Assert.assertEquals(
                actualTotal,
                expectedTotal,
                0.01,
                "Total Price did not update correctly"
        );

        System.out.println(
                "CART_15 - Expected Total: $"
                        + expectedTotal
        );

        System.out.println(
                "CART_15 - Actual Total: $"
                        + actualTotal
        );
    }


    // ============================================================
    // CART_16
    // Verify Total Price After Decrease
    // ============================================================

    @Test(priority = 16)
    public void verifyTotalPriceAfterDecrease()
            throws InterruptedException {

        addProductAndNavigateToCart();

        double productPrice = getProductPrice();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        minusButton
                )
        ).click();

        Thread.sleep(500);

        double actualTotal = getTotalPrice();

        Assert.assertEquals(
                actualTotal,
                productPrice,
                0.01,
                "Total Price did not return to original price"
        );

        System.out.println(
                "CART_16 - Expected: $" + productPrice
        );

        System.out.println(
                "CART_16 - Actual: $" + actualTotal
        );
    }


    // ============================================================
    // CART_17
    // Verify Total Items After Increase
    // ============================================================

    @Test(priority = 17)
    public void verifyTotalItemsAfterIncrease()
            throws InterruptedException {

        addProductAndNavigateToCart();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        int items = getTotalItems();

        Assert.assertEquals(
                items,
                2,
                "Total Items did not become 2"
        );

        System.out.println(
                "CART_17 - Total Items: " + items
        );
    }


    // ============================================================
    // CART_18
    // Verify Total Items After Decrease
    // ============================================================

    @Test(priority = 18)
    public void verifyTotalItemsAfterDecrease()
            throws InterruptedException {

        addProductAndNavigateToCart();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        minusButton
                )
        ).click();

        Thread.sleep(500);

        int items = getTotalItems();

        Assert.assertEquals(
                items,
                1,
                "Total Items did not return to 1"
        );

        System.out.println(
                "CART_18 - Total Items: " + items
        );
    }


    // ============================================================
    // CART_19
    // Verify Total Price For Quantity Three
    // ============================================================

    @Test(priority = 19)
    public void verifyTotalPriceForThreeQuantity()
            throws InterruptedException {

        addProductAndNavigateToCart();

        double price = getProductPrice();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(400);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        double actualTotal = getTotalPrice();

        double expectedTotal =
                price * 3;

        Assert.assertEquals(
                actualTotal,
                expectedTotal,
                0.01,
                "Total Price for quantity 3 is incorrect"
        );

        System.out.println(
                "CART_19 - Expected: $" + expectedTotal
        );

        System.out.println(
                "CART_19 - Actual: $" + actualTotal
        );
    }


    // ============================================================
    // CART_20
    // Verify Remove Button Displayed
    // ============================================================

    @Test(priority = 20)
    public void verifyRemoveButtonDisplayed()
            throws InterruptedException {

        addProductAndNavigateToCart();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                removeButton
                        )
                ).isDisplayed(),
                "Remove button is not displayed"
        );

        System.out.println(
                "CART_20 - PASSED"
        );
    }


    // ============================================================
    // CART_21
    // Verify Remove Product
    // ============================================================

    @Test(priority = 21)
    public void verifyRemoveProduct()
            throws InterruptedException {

        addProductAndNavigateToCart();

        WebElement remove = wait.until(
                ExpectedConditions.elementToBeClickable(
                        removeButton
                )
        );

        remove.click();

        Thread.sleep(700);

        boolean emptyCart =
                driver.getPageSource().contains(
                        "Your cart is empty"
                );

        Assert.assertTrue(
                emptyCart,
                "Product was not removed from Cart"
        );

        System.out.println(
                "CART_21 - Product removed successfully"
        );
    }


    // ============================================================
    // CART_22
    // Verify Checkout Button Displayed
    // ============================================================

    @Test(priority = 22)
    public void verifyCheckoutButtonDisplayed()
            throws InterruptedException {

        addProductAndNavigateToCart();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                checkoutButton
                        )
                ).isDisplayed(),
                "Proceed to Checkout button is not displayed"
        );

        System.out.println(
                "CART_22 - PASSED"
        );
    }


    // ============================================================
    // CART_23
    // Verify Checkout Button Clickable
    // ============================================================

    @Test(priority = 23)
    public void verifyCheckoutButtonClickable()
            throws InterruptedException {

        addProductAndNavigateToCart();

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                checkoutButton
                        )
                ).isEnabled(),
                "Proceed to Checkout button is not clickable"
        );

        System.out.println(
                "CART_23 - PASSED"
        );
    }


    // ============================================================
    // CART_24
    // Verify Price × Quantity Calculation
    // ============================================================

    @Test(priority = 24)
    public void verifyCartPriceAndQuantityCalculation()
            throws InterruptedException {

        addProductAndNavigateToCart();

        double price = getProductPrice();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        plusButton
                )
        ).click();

        Thread.sleep(500);

        int qty = getQuantity();

        double actualTotal = getTotalPrice();

        double expectedTotal =
                price * qty;

        Assert.assertEquals(
                actualTotal,
                expectedTotal,
                0.01,
                "Cart total calculation is incorrect"
        );

        System.out.println(
                "CART_24 - Price × Quantity calculation verified"
        );

        System.out.println(
                "Product Price: $" + price
        );

        System.out.println(
                "Quantity: " + qty
        );

        System.out.println(
                "Total Price: $" + actualTotal
        );
    }


    // ============================================================
    // CART_29
    // Verify Cart After Refresh
    // ============================================================

    @Test(priority = 29)
    public void verifyCartAfterRefresh()
            throws InterruptedException {

        addProductAndNavigateToCart();

        driver.navigate().refresh();

        Thread.sleep(1000);

        boolean cartDisplayed;

        try {

            cartDisplayed =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    cartSummary
                            )
                    ).isDisplayed();

        } catch (Exception e) {

            cartDisplayed = false;
        }

        /*
         * Expected:
         * Cart page should remain available after refresh.
         *
         * If the website shows Page Not Found,
         * this test will FAIL.
         */

        Assert.assertTrue(
                cartDisplayed,
                "Page Not Found displayed after Cart page refresh"
        );

        System.out.println(
                "CART_29 - Cart page available after refresh"
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
                "=========================================="
        );

        System.out.println(
                "All Cart Validation tests completed"
        );

        System.out.println(
                "Chrome browser closed"
        );

        System.out.println(
                "=========================================="
        );
    }
}