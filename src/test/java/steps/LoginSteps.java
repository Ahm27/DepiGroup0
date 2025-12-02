package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @Given("the user is on the SauceDemo login page")
    public void user_is_on_login_page() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @When("the user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("clicks the login button")
    public void clicks_login_button() {
        loginPage.clickLogin();
    }

    @Then("the user should see the products page")
    public void user_should_see_products_page() {
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Products page should be visible");
    }

    @Then("the user should see an error message {string}")
    public void user_should_see_error_message(String expectedMessage) {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedMessage),
                "Expected error message not found");
    }
}
