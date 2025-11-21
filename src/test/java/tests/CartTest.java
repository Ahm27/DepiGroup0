package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class CartTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutStepTwoPage checkoutStepTwoPage;
    private CheckoutCompletePage checkoutCompletePage;

    // Test Data
    private final String USERNAME = "standard_user";
    private final String PASSWORD = "secret_sauce";
    private final String FIRST_NAME = "John";
    private final String LAST_NAME = "Doe";
    private final String POSTAL_CODE = "12345";

    @BeforeMethod
    public void setUp() {
        String browser = "firefox"; // You can get this from Config

        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }

        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);

        // Login first
        loginPage.login(USERNAME, PASSWORD);
    }

    @Test(priority = 1)
    public void testCompleteCheckoutFlowSingleItem() {
        // Step 1: Add item to cart
        homePage.addBikeLightToCart();
        Assert.assertEquals(homePage.getCartItemCount(), 1, "Cart should contain 1 item");

        // Wait for cart update
       // try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // Step 2: Navigate to cart
        homePage.clickShoppingCart();
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page should be displayed");
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Bike Light"), "Bike Light should be in cart");

        // Wait for cart page to load
//try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // Step 3: Proceed to checkout
        cartPage.clickCheckout();
        Assert.assertTrue(checkoutStepOnePage.isCheckoutStepOnePageDisplayed(), "Checkout Step One page should be displayed");

        // Wait for checkout page to load
       // try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // Step 4: Fill checkout information
        checkoutStepOnePage.completeCheckoutStepOne(FIRST_NAME, LAST_NAME, POSTAL_CODE);

        // Wait for navigation to step two
       //try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // Check if we're on step two - if not, debug why
        if (!checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed()) {
            System.out.println("Still on Checkout Step One. Current page title: " + checkoutStepOnePage.getPageTitle());
            System.out.println("Error message displayed: " + checkoutStepOnePage.isErrorMessageDisplayed());
            if (checkoutStepOnePage.isErrorMessageDisplayed()) {
                System.out.println("Error message: " + checkoutStepOnePage.getErrorMessage());
            }
            Assert.fail("Failed to navigate to Checkout Step Two");
        }

        Assert.assertTrue(checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed(), "Checkout Step Two page should be displayed");

        // Step 5: Verify checkout overview
        Assert.assertTrue(checkoutStepTwoPage.isProductInCheckout("Sauce Labs Bike Light"), "Bike Light should be in checkout");
        Assert.assertEquals(checkoutStepTwoPage.getProductPriceInCheckout("Sauce Labs Bike Light"), "$9.99", "Price should match");
        Assert.assertTrue(checkoutStepTwoPage.verifyTotalCalculation(), "Total calculation should be correct");

        // Step 6: Complete checkout
        checkoutStepTwoPage.clickFinish();

        // Wait for completion page
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        Assert.assertTrue(checkoutCompletePage.isCheckoutCompletePageDisplayed(), "Checkout Complete page should be displayed");

        // Step 7: Verify completion
        Assert.assertTrue(checkoutCompletePage.verifyCompletePageContent(), "Complete page content should be correct");

        // Step 8: Return to home
        checkoutCompletePage.clickBackHome();

        // Wait for home page
       // try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        Assert.assertTrue(homePage.isHomePageDisplayed(), "Should return to home page");
        Assert.assertFalse(homePage.isCartBadgeDisplayed(), "Cart should be empty after purchase");
    }

    @Test(priority = 2)
    public void testCompleteCheckoutFlowMultipleItems() {
        // Step 1: Add multiple items to cart
        homePage.addBackpackToCart();
        homePage.addBikeLightToCart();
        homePage.addBoltTShirtToCart();
        Assert.assertEquals(homePage.getCartItemCount(), 3, "Cart should contain 3 items");

        // Step 2: Navigate to cart
        homePage.clickShoppingCart();
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page should be displayed");
        Assert.assertEquals(cartPage.getCartItemsCount(), 3, "Cart should have 3 items");

        // Step 3: Proceed to checkout
        cartPage.clickCheckout();
        Assert.assertTrue(checkoutStepOnePage.isCheckoutStepOnePageDisplayed(), "Checkout Step One page should be displayed");

        // Step 4: Fill checkout information
        checkoutStepOnePage.completeCheckoutStepOne(FIRST_NAME, LAST_NAME, POSTAL_CODE);
        Assert.assertTrue(checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed(), "Checkout Step Two page should be displayed");

        // Step 5: Verify checkout overview with multiple items
        Assert.assertTrue(checkoutStepTwoPage.isProductInCheckout("Sauce Labs Backpack"), "Backpack should be in checkout");
        Assert.assertTrue(checkoutStepTwoPage.isProductInCheckout("Sauce Labs Bike Light"), "Bike Light should be in checkout");
        Assert.assertTrue(checkoutStepTwoPage.verifyTotalCalculation(), "Total calculation should be correct");

        // Step 6: Complete checkout
        checkoutStepTwoPage.clickFinish();
        Assert.assertTrue(checkoutCompletePage.isCheckoutCompletePageDisplayed(), "Checkout Complete page should be displayed");

        // Step 7: Verify completion
        Assert.assertTrue(checkoutCompletePage.verifyCompletePageContent(), "Complete page content should be correct");
    }

    @Test(priority = 3)
    public void testCheckoutStepOneValidation() {
        // Add item and go to checkout
        homePage.addBikeLightToCart();
        homePage.clickShoppingCart();
        cartPage.clickCheckout();

        // Try to continue without filling information
        checkoutStepOnePage.clickContinue();
        Assert.assertTrue(checkoutStepOnePage.isErrorMessageDisplayed(), "Error message should be displayed for empty form");

        // Fill only first name
        checkoutStepOnePage.enterFirstName(FIRST_NAME);
        checkoutStepOnePage.clickContinue();
        Assert.assertTrue(checkoutStepOnePage.isErrorMessageDisplayed(), "Error message should be displayed for incomplete form");

        // Fill first and last name
        checkoutStepOnePage.enterLastName(LAST_NAME);
        checkoutStepOnePage.clickContinue();
        Assert.assertTrue(checkoutStepOnePage.isErrorMessageDisplayed(), "Error message should be displayed for missing postal code");

        // Fill all required fields
        checkoutStepOnePage.enterPostalCode(POSTAL_CODE);
        checkoutStepOnePage.clickContinue();
        Assert.assertTrue(checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed(), "Should proceed to step two with valid information");
    }

    @Test(priority = 4)
    public void testCheckoutCancelFromStepOne() {
        // Add item and go to checkout
        homePage.addBikeLightToCart();
        homePage.clickShoppingCart();
        cartPage.clickCheckout();

        // Cancel from step one
        checkoutStepOnePage.clickCancel();
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Should return to cart page");
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Bike Light"), "Item should still be in cart");
    }

    @Test(priority = 5)
    public void testCheckoutCancelFromStepTwo() {
        // Add item and go to checkout step two
        homePage.addBikeLightToCart();
        homePage.clickShoppingCart();
        cartPage.clickCheckout();
        checkoutStepOnePage.completeCheckoutStepOne(FIRST_NAME, LAST_NAME, POSTAL_CODE);

        // Cancel from step two
        checkoutStepTwoPage.clickCancel();
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Should return to home page");
        Assert.assertTrue(homePage.isCartBadgeDisplayed(), "Cart should be empty after cancellation");
    }

    @Test(priority = 6)
    public void testCartItemRemovalDuringCheckout() {
        // Add multiple items
        homePage.addBackpackToCart();
        homePage.addBikeLightToCart();
        homePage.clickShoppingCart();

        // Remove one item from cart
        cartPage.removeProductFromCart("Sauce Labs Backpack");
        Assert.assertEquals(cartPage.getCartItemsCount(), 1, "Cart should have 1 item after removal");
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Bike Light"), "Bike Light should remain in cart");

        // Proceed with remaining item
        cartPage.clickCheckout();
        checkoutStepOnePage.completeCheckoutStepOne(FIRST_NAME, LAST_NAME, POSTAL_CODE);
        checkoutStepTwoPage.clickFinish();

        Assert.assertTrue(checkoutCompletePage.isCheckoutCompletePageDisplayed(), "Checkout should complete successfully");
    }

    @Test(priority = 7)
    public void testEmptyCartCheckout() {
        // Go to cart without items
        homePage.clickShoppingCart();
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page should be displayed");
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty");

        // Try to checkout with empty cart
        cartPage.clickCheckout();
        Assert.assertTrue(checkoutStepOnePage.isCheckoutStepOnePageDisplayed(), "Should still go to checkout step one");

        // Fill information
        checkoutStepOnePage.completeCheckoutStepOne(FIRST_NAME, LAST_NAME, POSTAL_CODE);
        Assert.assertTrue(checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed(), "Should go to checkout step two");

        // Complete checkout with empty cart
        checkoutStepTwoPage.clickFinish();
        Assert.assertFalse(checkoutCompletePage.isCheckoutCompletePageDisplayed(), "Checkout shouldn't complete with empty cart");
    }

    @Test(priority = 8)
    public void testCheckoutPriceVerification() {
        // Add specific items with known prices
        homePage.addBackpackToCart(); // $29.99
        homePage.addOnesieToCart();   // $7.99
        homePage.clickShoppingCart();
        cartPage.clickCheckout();
        checkoutStepOnePage.completeCheckoutStepOne(FIRST_NAME, LAST_NAME, POSTAL_CODE);

        // Verify prices in checkout overview
        Assert.assertEquals(checkoutStepTwoPage.getProductPriceInCheckout("Sauce Labs Backpack"), "$29.99");
        Assert.assertEquals(checkoutStepTwoPage.getProductPriceInCheckout("Sauce Labs Onesie"), "$7.99");

        // Verify calculations
        double subtotal = checkoutStepTwoPage.getSubtotalAsDouble();
        double tax = checkoutStepTwoPage.getTaxAsDouble();
        double total = checkoutStepTwoPage.getTotalAsDouble();

        Assert.assertEquals(subtotal, 37.98, 0.01, "Subtotal should be sum of item prices");
        Assert.assertEquals(tax, 3.04, 0.01, "Tax should be 8% of subtotal");
        Assert.assertEquals(total, 41.02, 0.01, "Total should be subtotal + tax");
        Assert.assertTrue(checkoutStepTwoPage.verifyTotalCalculation(), "Total calculation should be verified");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            // Reset app state and navigate to home page
            driver.get("https://www.saucedemo.com/inventory.html");
            try {
                homePage.clickMenuButton();
                homePage.clickResetAppState();
                homePage.closeMenu();
            } catch (Exception e) {
                // If reset fails, just navigate to home page
                driver.get("https://www.saucedemo.com/inventory.html");
            }
        }
    }

    @org.testng.annotations.AfterClass
    public void afterClass() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}