package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepOnePage extends BasePage {

    // Page Elements
    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(className = "error-message-container")
    private WebElement errorMessage;

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    // Page Verification
    public boolean isCheckoutStepOnePageDisplayed() {
        return pageTitle.isDisplayed() && pageTitle.getText().equals("Checkout: Your Information");
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    // Form Methods
    public void enterFirstName(String firstName) {
        waitForElementToBeVisible(firstNameInput);
        type(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        waitForElementToBeVisible(lastNameInput);
        type(lastNameInput, lastName);
    }

    public void enterPostalCode(String postalCode) {
        waitForElementToBeVisible(postalCodeInput);
        type(postalCodeInput, postalCode);
    }

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    // Button Methods
    public void clickContinue() {
        waitForElementToBeClickable(continueButton);
        click(continueButton);
    }

    public void clickCancel() {
        waitForElementToBeClickable(cancelButton);
        click(cancelButton);
    }

    // Error Handling
    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        if (isErrorMessageDisplayed()) {
            return errorMessage.getText();
        }
        return "";
    }

    // Complete Checkout Step One - UPDATED
    public void completeCheckoutStepOne(String firstName, String lastName, String postalCode) {
        // Wait for page to load completely
        waitForElementToBeVisible(firstNameInput);

        // Fill the form
        fillCheckoutInformation(firstName, lastName, postalCode);

        // Small delay to ensure form is filled
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Click continue
        clickContinue();

        // Wait for navigation to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}