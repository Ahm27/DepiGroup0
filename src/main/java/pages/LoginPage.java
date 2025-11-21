package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // Web Elements
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "login_logo")
    private WebElement loginLogo;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "login_credentials")
    private WebElement loginCredentials;

    @FindBy(className = "login_password")
    private WebElement loginPassword;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Page Methods
    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        waitForElementToBeVisible(errorMessage);
        return errorMessage.getText();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoginLogoText() {
        return loginLogo.getText();
    }

    public boolean isLoginPageDisplayed() {
        return loginLogo.isDisplayed() && usernameInput.isDisplayed();
    }

    public String getAcceptedUsernames() {
        return loginCredentials.getText();
    }

    public String getPasswordHint() {
        return loginPassword.getText();
    }
}