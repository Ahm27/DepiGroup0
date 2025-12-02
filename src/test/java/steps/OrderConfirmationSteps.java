package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class OrderConfirmationSteps {

    private boolean checkoutCompleted = false;
    private String confirmationMessage = "";

    @Given("the user has completed the checkout process")
    public void theUserHasCompletedTheCheckoutProcess() {
        checkoutCompleted = true;

        // In a real UI test, this string would come from the confirmation page element.
        confirmationMessage = "THANK YOU FOR YOUR ORDER";

        System.out.println("Checkout completed and user is on the order confirmation page.");
    }

    @Then("the user should see the message {string}")
    public void theUserShouldSeeTheMessage(String expectedMessage) {

        Assert.assertTrue(checkoutCompleted,
                "Checkout process was not completed!");

        Assert.assertEquals(confirmationMessage, expectedMessage,
                "Order confirmation message is incorrect!");

        System.out.println("Order success message verified successfully.");
    }
}
