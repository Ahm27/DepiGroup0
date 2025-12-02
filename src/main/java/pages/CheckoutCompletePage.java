package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {

    // Page Elements
    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "pony_express")
    private WebElement ponyExpressImage;

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(className = "complete-text")
    private WebElement completeText;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    // Page Verification
    public boolean isCheckoutCompletePageDisplayed() {
        return pageTitle.isDisplayed() && pageTitle.getText().equals("Checkout: Complete!");
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    // Content Verification
    public boolean isPonyExpressImageDisplayed() {
        return ponyExpressImage.isDisplayed();
    }

    public String getCompleteHeader() {
        return completeHeader.getText();
    }

    public String getCompleteText() {
        return completeText.getText();
    }

    public boolean isBackHomeButtonDisplayed() {
        return backHomeButton.isDisplayed();
    }

    // Navigation
    public void clickBackHome() {
        click(backHomeButton);
    }

    // Complete Page Verification
    public boolean verifyCompletePageContent() {
        return isCheckoutCompletePageDisplayed() &&
                isPonyExpressImageDisplayed() &&
                getCompleteHeader().equals("Thank you for your order!") &&
                getCompleteText().contains("Your order has been dispatched") &&
                isBackHomeButtonDisplayed();
    }
}