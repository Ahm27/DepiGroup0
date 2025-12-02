package steps;

import io.cucumber.java.en.*;
import org.testng.Assert;

public class CartSteps {

    private boolean productsAdded = false;
    private boolean cartOpened = false;
    private boolean onCartPage = false;
    private boolean checkoutClicked = false;

    @Given("the user has added products to the cart")
    public void theUserHasAddedProductsToTheCart() {
        productsAdded = true;
        System.out.println("Products added to cart.");
    }

    @When("the user clicks the cart icon")
    public void theUserClicksTheCartIcon() {
        if (productsAdded) {
            cartOpened = true;
            onCartPage = true;
        }
        System.out.println("Cart icon clicked.");
    }

    @Then("the user should see all added products in the cart")
    public void theUserShouldSeeAllAddedProductsInTheCart() {
        Assert.assertTrue(cartOpened, "Cart is not opened!");
        Assert.assertTrue(productsAdded, "Products were not added to the cart!");
        System.out.println("Cart items displayed successfully.");
    }

    @Given("the user is on the cart page")
    public void theUserIsOnTheCartPage() {
        onCartPage = true;
        System.out.println("User is on the cart page.");
    }

    @When("the user clicks the checkout button")
    public void theUserClicksTheCheckoutButton() {
        if (onCartPage) {
            checkoutClicked = true;
        }
        System.out.println("Checkout button clicked.");
    }

    @Then("the user should be navigated to the checkout page")
    public void theUserShouldBeNavigatedToTheCheckoutPage() {
        Assert.assertTrue(checkoutClicked, "Checkout button was not clicked!");
        System.out.println("Navigated to checkout page successfully.");
    }
}
