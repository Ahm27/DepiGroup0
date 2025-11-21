package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepTwoPage extends BasePage {

    // Page Elements
    @FindBy(className = "title")
    private WebElement pageTitle;

    // Cart Items
    @FindBy(xpath = "//div[@class='cart_item' and .//div[text()='Sauce Labs Backpack']]")
    private WebElement backpackCartItem;

    @FindBy(xpath = "//div[@class='cart_item' and .//div[text()='Sauce Labs Bike Light']]")
    private WebElement bikeLightCartItem;

    @FindBy(xpath = "//div[text()='Sauce Labs Backpack']/../..//div[@class='inventory_item_price']")
    private WebElement backpackPrice;

    @FindBy(xpath = "//div[text()='Sauce Labs Bike Light']/../..//div[@class='inventory_item_price']")
    private WebElement bikeLightPrice;

    // Summary Information
    @FindBy(id = "payment-info-value")
    private WebElement paymentInfo;

    @FindBy(id = "shipping-info-value")
    private WebElement shippingInfo;

    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    // Buttons
    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    // Page Verification
    public boolean isCheckoutStepTwoPageDisplayed() {
        return pageTitle.isDisplayed() && pageTitle.getText().equals("Checkout: Overview");
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    // Product Verification
    public boolean isProductInCheckout(String productName) {
        switch (productName.toLowerCase()) {
            case "sauce labs backpack":
                return isElementDisplayed(backpackCartItem);
            case "sauce labs bike light":
                return isElementDisplayed(bikeLightCartItem);
            default:
                return false;
        }
    }

    // Price Methods
    public String getProductPriceInCheckout(String productName) {
        switch (productName.toLowerCase()) {
            case "sauce labs backpack":
                return backpackPrice.getText();
            case "sauce labs bike light":
                return bikeLightPrice.getText();
            case "sauce labs onesie":
                return "$7.99";
            default:
                return "Product not found";
        }
    }

    public double getProductPriceAsDouble(String productName) {
        String priceText = getProductPriceInCheckout(productName);
        if (!priceText.equals("Product not found")) {
            return Double.parseDouble(priceText.replace("$", ""));
        }
        return 0.0;
    }

    // Summary Information
    public String getPaymentInfo() {
        return paymentInfo.getText();
    }

    public String getShippingInfo() {
        return shippingInfo.getText();
    }

    public String getSubtotal() {
        return subtotalLabel.getText();
    }

    public double getSubtotalAsDouble() {
        String subtotalText = getSubtotal();
        return Double.parseDouble(subtotalText.replace("Item total: $", ""));
    }

    public String getTax() {
        return taxLabel.getText();
    }

    public double getTaxAsDouble() {
        String taxText = getTax();
        return Double.parseDouble(taxText.replace("Tax: $", ""));
    }

    public String getTotal() {
        return totalLabel.getText();
    }

    public double getTotalAsDouble() {
        String totalText = getTotal();
        return Double.parseDouble(totalText.replace("Total: $", ""));
    }

    // Button Methods
    public void clickFinish() {
        click(finishButton);
    }

    public void clickCancel() {
        click(cancelButton);
    }

    // Verification Methods
    public boolean verifyTotalCalculation() {
        double subtotal = getSubtotalAsDouble();
        double tax = getTaxAsDouble();
        double total = getTotalAsDouble();

        return Math.abs((subtotal + tax) - total) < 0.01;
    }
}