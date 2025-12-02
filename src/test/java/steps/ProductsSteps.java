package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverFactory;

public class ProductsSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @Given("the user is logged in and on the products page")
    public void the_user_is_logged_in_and_on_the_products_page() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(
                homePage.isHomePageDisplayed(),
                "User should be on the Products page after login"
        );
    }

    @When("the user adds {string} to the cart")
    public void the_user_adds_product_to_the_cart(String productName) {
        homePage.addProductToCart(productName);
    }

    @Then("the cart icon should show {int} item(s)")
    public void the_cart_icon_should_show_items(Integer expectedCount) {
        Assert.assertTrue(
                homePage.isCartBadgeDisplayed(),
                "Cart badge should appear after adding items"
        );

        Assert.assertEquals(
                homePage.getCartItemCount(),
                expectedCount.intValue(),
                "Cart count is incorrect"
        );
    }
}
