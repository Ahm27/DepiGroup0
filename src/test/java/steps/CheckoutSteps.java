package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverFactory;

public class CheckoutSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutStepTwoPage checkoutStepTwoPage;
    private CheckoutCompletePage checkoutCompletePage;

    @Given("the user is on the checkout page")
    public void the_user_is_on_the_checkout_page() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com");

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);

        // Login
        loginPage.login("standard_user", "secret_sauce");

        // Add item to access checkout page
        homePage.addBikeLightToCart();
        homePage.clickShoppingCart();
        cartPage.clickCheckout();

        Assert.assertTrue(checkoutStepOnePage.isCheckoutStepOnePageDisplayed(),
                "User is not on Checkout Step One page");
    }

    @When("the user enters first name {string}, last name {string}, and postal code {string}")
    public void the_user_enters_checkout_info(String firstName, String lastName, String postalCode) {
        checkoutStepOnePage.enterFirstName(firstName);
        checkoutStepOnePage.enterLastName(lastName);
        checkoutStepOnePage.enterPostalCode(postalCode);
    }

    @When("clicks continue")
    public void clicks_continue() {
        checkoutStepOnePage.clickContinue();
    }

    @Then("the user should be navigated to the overview page")
    public void the_user_should_be_navigated_to_the_overview_page() {
        Assert.assertTrue(checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed(),
                "User is not on Checkout Overview page");
    }

    // --- Second Scenario ---

    @Given("the user is on the overview page")
    public void the_user_is_on_the_overview_page() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com");

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);

        loginPage.login("standard_user", "secret_sauce");

        homePage.addBikeLightToCart();
        homePage.clickShoppingCart();
        cartPage.clickCheckout();
        checkoutStepOnePage.completeCheckoutStepOne("John", "Doe", "12345");

        Assert.assertTrue(checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed(),
                "User is not on Checkout Overview page");
    }

    @When("the user clicks finish")
    public void the_user_clicks_finish() {
        checkoutStepTwoPage.clickFinish();
    }

    @Then("the user should see the order confirmation page")
    public void the_user_should_see_the_order_confirmation_page() {
        Assert.assertTrue(checkoutCompletePage.isCheckoutCompletePageDisplayed(),
                "Order confirmation page was not displayed");
    }
}
