package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.Config;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    public WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Initialize driver only once
        if (driver == null) {
            String browser = Config.getBrowser();
            System.out.println("Using browser: " + browser);

            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    System.out.println("Unknown browser: " + browser + ", defaulting to Chrome");
                    driver = new ChromeDriver();
            }

            driver.manage().window().maximize();
        }

        // Navigate to base URL before each test
        String baseUrl = Config.getBaseUrl();
        System.out.println("Navigating to: " + baseUrl);
        driver.get(baseUrl);

        // Reinitialize page objects
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }
    @Test(priority = 1)
    public void testSuccessfulLogin() {
        // Test data
        String username = "standard_user";
        String password = "secret_sauce";

        // Perform login
        loginPage.login(username, password);

        // Verify successful login
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page should be displayed after successful login");
    }

    @Test(priority = 2)
    public void testInvalidUsername() {
        // Test data
        String username = "invalid_user";
        String password = "secret_sauce";

        // Perform login
        loginPage.login(username, password);

        // Verify error message
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid username");
        String errorText = loginPage.getErrorMessage();
        Assert.assertTrue(errorText.contains("Username and password do not match"),
                "Error message should indicate invalid credentials");
    }

    @Test(priority = 3)
    public void testInvalidPassword() {
        // Test data
        String username = "standard_user";
        String password = "wrong_password";

        // Perform login
        loginPage.login(username, password);

        // Verify error message
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid password");
    }

    @Test(priority = 4)
    public void testEmptyCredentials() {
        // Perform login with empty credentials
        loginPage.clickLogin();

        // Verify error message
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for empty credentials");
    }

    @Test(priority = 5)
    public void testLockedOutUser() {
        // Test data for locked out user
        String username = "locked_out_user";
        String password = "secret_sauce";

        // Perform login
        loginPage.login(username, password);

        // Verify error message for locked out user
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for locked out user");
        String errorText = loginPage.getErrorMessage();
        Assert.assertTrue(errorText.contains("Sorry, this user has been locked out"),
                "Error message should indicate user is locked out");
    }

    @Test(priority = 6)
    public void testLoginPageElements() {
        // Verify login page elements are displayed
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be properly displayed");
        Assert.assertEquals(loginPage.getLoginLogoText(), "Swag Labs", "Logo text should match");

        // Verify credentials hints are present
        String usernames = loginPage.getAcceptedUsernames();
        String passwordHint = loginPage.getPasswordHint();

        Assert.assertTrue(usernames.contains("standard_user"), "Accepted usernames should be displayed");
        Assert.assertTrue(passwordHint.contains("secret_sauce"), "Password hint should be displayed");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            // Navigate back to login page instead of quitting
            driver.navigate().back();

            // Clear browser cookies to ensure clean state for next test
            driver.manage().deleteAllCookies();


        }
    }

    // Add AfterClass to quit driver after all tests
    @org.testng.annotations.AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
            driver = null; // Important: set to null to allow fresh start if class is run again
        }
    }
}


