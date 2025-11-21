package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import utils.Config;

public class HomePageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        String browser = Config.getBrowser();

        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new org.openqa.selenium.firefox.FirefoxDriver();
                    break;
                case "edge":
                    driver = new org.openqa.selenium.edge.EdgeDriver();
                    break;
                default:
                    driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
        }

        driver.get(Config.getBaseUrl());
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);

        // Login first
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(priority = 1)
    public void testHomePageElements() {
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page should be displayed");
        Assert.assertEquals(homePage.getPageTitleText(), "Products", "Page title should be 'Products'");
        Assert.assertEquals(homePage.getAppLogoText(), "Swag Labs", "App logo should be 'Swag Labs'");

        // Verify some products are displayed
        Assert.assertTrue(homePage.isProductDisplayed("Sauce Labs Backpack"), "Backpack should be displayed");
        Assert.assertTrue(homePage.isProductDisplayed("Sauce Labs Bike Light"), "Bike Light should be displayed");
    }

    @Test(priority = 2)
    public void testAddSingleItemToCart() {
        // Add backpack to cart
        homePage.addBackpackToCart();

        // Verify cart badge shows 1 item
        Assert.assertTrue(homePage.isCartBadgeDisplayed(), "Cart badge should be displayed");
        Assert.assertEquals(homePage.getCartItemCount(), 1, "Cart should contain 1 item");

        // Verify remove button appears
        Assert.assertTrue(homePage.isProductInCart("Sauce Labs Backpack"), "Remove button should be displayed for backpack");
    }

    @Test(priority = 3)
    public void testAddMultipleItemsToCart() {
        // Add multiple items to cart
        homePage.addBackpackToCart();
        homePage.addBikeLightToCart();
        homePage.addBoltTShirtToCart();

        // Verify cart badge shows 3 items
        Assert.assertEquals(homePage.getCartItemCount(), 3, "Cart should contain 3 items");

        // Verify remove buttons appear for all added items
        Assert.assertTrue(homePage.isProductInCart("Sauce Labs Backpack"), "Remove button should be displayed for backpack");
        Assert.assertTrue(homePage.isProductInCart("Sauce Labs Bike Light"), "Remove button should be displayed for bike light");
        Assert.assertTrue(homePage.isProductInCart("Sauce Labs Bolt T-Shirt"), "Remove button should be displayed for bolt t-shirt");
    }

    @Test(priority = 4)
    public void testNavigateToCart() {
        // Add item and navigate to cart
        homePage.addBackpackToCart();
        homePage.clickShoppingCart();

        // Verify cart page is displayed
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page should be displayed");
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"), "Backpack should be in cart");
        Assert.assertEquals(cartPage.getCartItemsCount(), 1, "Cart should contain 1 item");
    }

    @Test(priority = 5)
    public void testSortProductsByNameAZ() {
        homePage.sortProducts("az");
        String currentSort = homePage.getCurrentSortOption();
        Assert.assertEquals(currentSort, "Name (A to Z)", "Sort should be Name (A to Z)");
    }

    @Test(priority = 6)
    public void testSortProductsByNameZA() {
        homePage.sortProducts("za");
        String currentSort = homePage.getCurrentSortOption();
        Assert.assertEquals(currentSort, "Name (Z to A)", "Sort should be Name (Z to A)");
    }

    @Test(priority = 7)
    public void testSortProductsByPriceLowHigh() {
        homePage.sortProducts("lohi");
        String currentSort = homePage.getCurrentSortOption();
        Assert.assertEquals(currentSort, "Price (low to high)", "Sort should be Price (low to high)");
    }

    @Test(priority = 8)
    public void testSortProductsByPriceHighLow() {
        homePage.sortProducts("hilo");
        String currentSort = homePage.getCurrentSortOption();
        Assert.assertEquals(currentSort, "Price (high to low)", "Sort should be Price (high to low)");
    }

    @Test(priority = 9)
    public void testAddRemoveItemFromCart() {
        // Add item
        homePage.addBackpackToCart();
        Assert.assertEquals(homePage.getCartItemCount(), 1, "Cart should contain 1 item after adding");
        Assert.assertTrue(homePage.isProductInCart("Sauce Labs Backpack"), "Remove button should be displayed");

        // Remove item
        homePage.removeBackpackFromCart();
        Assert.assertFalse(homePage.isCartBadgeDisplayed(), "Cart badge should not be displayed after removal");
    }

    @Test(priority = 10)
    public void testCartTotalCalculation() {
        // Add multiple items
        homePage.addBackpackToCart(); // $29.99
        homePage.addBikeLightToCart(); // $9.99
        homePage.addOnesieToCart(); // $7.99

        // Navigate to cart
        homePage.clickShoppingCart();

        // Debug output to see what's happening
        cartPage.debugCartContents();

        // Verify items are in cart
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"));
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Bike Light"));
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Onesie"));

        // Verify quantities are all 1 (default)
        Assert.assertEquals(cartPage.getProductQuantityInCart("Sauce Labs Backpack"), 1, "Backpack quantity should be 1");
        Assert.assertEquals(cartPage.getProductQuantityInCart("Sauce Labs Bike Light"), 1, "Bike Light quantity should be 1");
        Assert.assertEquals(cartPage.getProductQuantityInCart("Sauce Labs Onesie"), 1, "Onesie quantity should be 1");

        // Verify total calculation
        double expectedTotal = 29.99 + 9.99 + 7.99;
        double actualTotal = cartPage.calculateTotalPrice();

        System.out.println("Expected Total: $" + expectedTotal);
        System.out.println("Actual Total: $" + actualTotal);

        Assert.assertEquals(actualTotal, expectedTotal, 0.01, "Total price should be calculated correctly");
    }

    // Add a new test for multiple quantities
    @Test(priority = 11)
    public void testCartTotalCalculationWithMultipleQuantities() {
        // This test would require adding the same product multiple times
        // Note: Swag Labs typically doesn't allow adding same product multiple times in UI
        // So this is more of a theoretical test for the calculation logic

        homePage.addBackpackToCart(); // $29.99 x 1
        homePage.addBikeLightToCart(); // $9.99 x 1

        homePage.clickShoppingCart();

        // In a real scenario where multiple quantities are allowed:
        // total = (price1 * quantity1) + (price2 * quantity2) + ...
        double expectedTotal = 29.99 + 9.99;
        double actualTotal = cartPage.calculateTotalPrice();

        Assert.assertEquals(actualTotal, expectedTotal, 0.01, "Total should calculate correctly with single quantities");
    }
    @Test(priority = 12)
    public void testRemoveFromCartPage() {
        // Add items and go to cart
        homePage.addBackpackToCart();
        homePage.addBikeLightToCart();
        homePage.clickShoppingCart();

        // Remove one item from cart page
        cartPage.removeProductFromCart("Sauce Labs Backpack");

        // Verify remaining items
        Assert.assertFalse(cartPage.isProductInCart("Sauce Labs Backpack"), "Backpack should be removed from cart");
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Bike Light"), "Bike light should still be in cart");
        Assert.assertEquals(cartPage.getCartItemsCount(), 1, "Cart should contain 1 item after removal");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            // Navigate back to home page and reset state if needed
            driver.get(Config.getBaseUrl() + "inventory.html");

            // Clear cart by resetting app state through menu
            try {
                homePage.clickMenuButton();
                homePage.clickResetAppState();
                homePage.closeMenu();
            } catch (Exception e) {
                // If reset fails, navigate to home page
                driver.get(Config.getBaseUrl() + "inventory.html");
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