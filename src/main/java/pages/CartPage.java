package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    // Cart Page Header Elements
    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "app_logo")
    private WebElement appLogo;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    // Cart Action Buttons
    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    // Cart List Labels
    @FindBy(className = "cart_quantity_label")
    private WebElement cartQuantityLabel;

    @FindBy(className = "cart_desc_label")
    private WebElement cartDescLabel;

    // Cart Items - Individual product elements
    // Sauce Labs Backpack
    @FindBy(xpath = "//div[@class='cart_item' and .//div[text()='Sauce Labs Backpack']]")
    private WebElement backpackCartItem;

    @FindBy(xpath = "//div[text()='Sauce Labs Backpack']")
    private WebElement backpackName;

    @FindBy(xpath = "//div[text()='Sauce Labs Backpack']/../..//div[@class='inventory_item_desc']")
    private WebElement backpackDescription;

    @FindBy(xpath = "//div[text()='Sauce Labs Backpack']/../..//div[@class='inventory_item_price']")
    private WebElement backpackPrice;

    @FindBy(xpath = "//div[text()='Sauce Labs Backpack']/../../..//div[@class='cart_quantity']")
    private WebElement backpackQuantity;

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeBackpackButton;

    // Sauce Labs Bike Light
    @FindBy(xpath = "//div[@class='cart_item' and .//div[text()='Sauce Labs Bike Light']]")
    private WebElement bikeLightCartItem;

    @FindBy(xpath = "//div[text()='Sauce Labs Bike Light']")
    private WebElement bikeLightName;

    @FindBy(xpath = "//div[text()='Sauce Labs Bike Light']/../..//div[@class='inventory_item_desc']")
    private WebElement bikeLightDescription;

    @FindBy(xpath = "//div[text()='Sauce Labs Bike Light']/../..//div[@class='inventory_item_price']")
    private WebElement bikeLightPrice;

    @FindBy(xpath = "//div[text()='Sauce Labs Bike Light']/../../..//div[@class='cart_quantity']")
    private WebElement bikeLightQuantity;

    @FindBy(id = "remove-sauce-labs-bike-light")
    private WebElement removeBikeLightButton;

    // Sauce Labs Bolt T-Shirt
    @FindBy(xpath = "//div[@class='cart_item' and .//div[text()='Sauce Labs Bolt T-Shirt']]")
    private WebElement boltTShirtCartItem;

    @FindBy(xpath = "//div[text()='Sauce Labs Bolt T-Shirt']")
    private WebElement boltTShirtName;

    @FindBy(xpath = "//div[text()='Sauce Labs Bolt T-Shirt']/../..//div[@class='inventory_item_desc']")
    private WebElement boltTShirtDescription;

    @FindBy(xpath = "//div[text()='Sauce Labs Bolt T-Shirt']/../..//div[@class='inventory_item_price']")
    private WebElement boltTShirtPrice;

    @FindBy(xpath = "//div[text()='Sauce Labs Bolt T-Shirt']/../../..//div[@class='cart_quantity']")
    private WebElement boltTShirtQuantity;

    @FindBy(id = "remove-sauce-labs-bolt-t-shirt")
    private WebElement removeBoltTShirtButton;

    // Sauce Labs Fleece Jacket
    @FindBy(xpath = "//div[@class='cart_item' and .//div[text()='Sauce Labs Fleece Jacket']]")
    private WebElement fleeceJacketCartItem;

    @FindBy(xpath = "//div[text()='Sauce Labs Fleece Jacket']")
    private WebElement fleeceJacketName;

    @FindBy(xpath = "//div[text()='Sauce Labs Fleece Jacket']/../..//div[@class='inventory_item_desc']")
    private WebElement fleeceJacketDescription;

    @FindBy(xpath = "//div[text()='Sauce Labs Fleece Jacket']/../..//div[@class='inventory_item_price']")
    private WebElement fleeceJacketPrice;

    @FindBy(xpath = "//div[text()='Sauce Labs Fleece Jacket']/../../..//div[@class='cart_quantity']")
    private WebElement fleeceJacketQuantity;

    @FindBy(id = "remove-sauce-labs-fleece-jacket")
    private WebElement removeFleeceJacketButton;

    // Sauce Labs Onesie
    @FindBy(xpath = "//div[@class='cart_item' and .//div[text()='Sauce Labs Onesie']]")
    private WebElement onesieCartItem;

    @FindBy(xpath = "//div[text()='Sauce Labs Onesie']")
    private WebElement onesieName;

    @FindBy(xpath = "//div[text()='Sauce Labs Onesie']/../..//div[@class='inventory_item_desc']")
    private WebElement onesieDescription;

    @FindBy(xpath = "//div[text()='Sauce Labs Onesie']/../..//div[@class='inventory_item_price']")
    private WebElement onesiePrice;

    @FindBy(xpath = "//div[text()='Sauce Labs Onesie']/../../..//div[@class='cart_quantity']")
    private WebElement onesieQuantity;

    @FindBy(id = "remove-sauce-labs-onesie")
    private WebElement removeOnesieButton;

    // Test.allTheThings() T-Shirt (Red)
    @FindBy(xpath = "//div[@class='cart_item' and .//div[text()='Test.allTheThings() T-Shirt (Red)']]")
    private WebElement tShirtRedCartItem;

    @FindBy(xpath = "//div[text()='Test.allTheThings() T-Shirt (Red)']")
    private WebElement tShirtRedName;

    @FindBy(xpath = "//div[text()='Test.allTheThings() T-Shirt (Red)']/../..//div[@class='inventory_item_desc']")
    private WebElement tShirtRedDescription;

    @FindBy(xpath = "//div[text()='Test.allTheThings() T-Shirt (Red)']/../..//div[@class='inventory_item_price']")
    private WebElement tShirtRedPrice;

    @FindBy(xpath = "//div[text()='Test.allTheThings() T-Shirt (Red)']/../../..//div[@class='cart_quantity']")
    private WebElement tShirtRedQuantity;

    @FindBy(id = "remove-test.allthethings()-t-shirt-(red)")
    private WebElement removeTShirtRedButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Page Verification Methods
    public boolean isCartPageDisplayed() {
        return pageTitle.isDisplayed() && pageTitle.getText().equals("Your Cart");
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public boolean isAppLogoDisplayed() {
        return appLogo.isDisplayed();
    }

    public String getAppLogoText() {
        return appLogo.getText();
    }

    // Cart Badge Methods
    public int getCartBadgeCount() {
        try {
            return Integer.parseInt(shoppingCartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isCartBadgeDisplayed() {
        try {
            return shoppingCartBadge.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Navigation Methods
    public void clickContinueShopping() {
        click(continueShoppingButton);
    }

    public void clickCheckout() {
        click(checkoutButton);
    }

    public void clickShoppingCart() {
        click(shoppingCartLink);
    }

    // Cart Labels Verification
    public String getQuantityLabelText() {
        return cartQuantityLabel.getText();
    }

    public String getDescriptionLabelText() {
        return cartDescLabel.getText();
    }

    // Product Presence Verification Methods
    public boolean isProductInCart(String productName) {
        switch (productName.toLowerCase()) {
            case "sauce labs backpack":
                return isElementDisplayed(backpackCartItem);
            case "sauce labs bike light":
                return isElementDisplayed(bikeLightCartItem);
            case "sauce labs bolt t-shirt":
                return isElementDisplayed(boltTShirtCartItem);
            case "sauce labs fleece jacket":
                return isElementDisplayed(fleeceJacketCartItem);
            case "sauce labs onesie":
                return isElementDisplayed(onesieCartItem);
            case "test.allthethings() t-shirt (red)":
                return isElementDisplayed(tShirtRedCartItem);
            default:
                return false;
        }
    }

    // Product Name Methods
    public String getProductNameInCart(String productName) {
        switch (productName.toLowerCase()) {
            case "sauce labs backpack":
                return backpackName.getText();
            case "sauce labs bike light":
                return bikeLightName.getText();
            case "sauce labs bolt t-shirt":
                return boltTShirtName.getText();
            case "sauce labs fleece jacket":
                return fleeceJacketName.getText();
            case "sauce labs onesie":
                return onesieName.getText();
            case "test.allthethings() t-shirt (red)":
                return tShirtRedName.getText();
            default:
                return "Product not found";
        }
    }

    // Product Description Methods
    public String getProductDescriptionInCart(String productName) {
        switch (productName.toLowerCase()) {
            case "sauce labs backpack":
                return backpackDescription.getText();
            case "sauce labs bike light":
                return bikeLightDescription.getText();
            case "sauce labs bolt t-shirt":
                return boltTShirtDescription.getText();
            case "sauce labs fleece jacket":
                return fleeceJacketDescription.getText();
            case "sauce labs onesie":
                return onesieDescription.getText();
            case "test.allthethings() t-shirt (red)":
                return tShirtRedDescription.getText();
            default:
                return "Product not found";
        }
    }

    // Product Price Methods
    public String getProductPriceInCart(String productName) {
        switch (productName.toLowerCase()) {
            case "sauce labs backpack":
                return backpackPrice.getText();
            case "sauce labs bike light":
                return bikeLightPrice.getText();
            case "sauce labs bolt t-shirt":
                return boltTShirtPrice.getText();
            case "sauce labs fleece jacket":
                return fleeceJacketPrice.getText();
            case "sauce labs onesie":
                return onesiePrice.getText();
            case "test.allthethings() t-shirt (red)":
                return tShirtRedPrice.getText();
            default:
                return "Product not found";
        }
    }

    public double getProductPriceAsDouble(String productName) {
        String priceText = getProductPriceInCart(productName);
        if (!priceText.equals("Product not found")) {
            return Double.parseDouble(priceText.replace("$", ""));
        }
        return 0.0;
    }

    // Product Quantity Methods - SINGLE DEFINITION
    public int getProductQuantityInCart(String productName) {
        try {
            switch (productName.toLowerCase()) {
                case "sauce labs backpack":
                    return Integer.parseInt(backpackQuantity.getText());
                case "sauce labs bike light":
                    return Integer.parseInt(bikeLightQuantity.getText());
                case "sauce labs bolt t-shirt":
                    return Integer.parseInt(boltTShirtQuantity.getText());
                case "sauce labs fleece jacket":
                    return Integer.parseInt(fleeceJacketQuantity.getText());
                case "sauce labs onesie":
                    return Integer.parseInt(onesieQuantity.getText());
                case "test.allthethings() t-shirt (red)":
                    return Integer.parseInt(tShirtRedQuantity.getText());
                default:
                    return 1; // Default to 1 if product not found
            }
        } catch (Exception e) {
            return 1; // Default to 1 if element not found or parsing fails
        }
    }

    // Remove Product Methods
    public void removeProductFromCart(String productName) {
        switch (productName.toLowerCase()) {
            case "sauce labs backpack":
                click(removeBackpackButton);
                break;
            case "sauce labs bike light":
                click(removeBikeLightButton);
                break;
            case "sauce labs bolt t-shirt":
                click(removeBoltTShirtButton);
                break;
            case "sauce labs fleece jacket":
                click(removeFleeceJacketButton);
                break;
            case "sauce labs onesie":
                click(removeOnesieButton);
                break;
            case "test.allthethings() t-shirt (red)":
                click(removeTShirtRedButton);
                break;
            default:
                throw new IllegalArgumentException("Product not found in cart: " + productName);
        }
    }

    // Remove Button Verification
    public boolean isRemoveButtonDisplayed(String productName) {
        switch (productName.toLowerCase()) {
            case "sauce labs backpack":
                return isElementDisplayed(removeBackpackButton);
            case "sauce labs bike light":
                return isElementDisplayed(removeBikeLightButton);
            case "sauce labs bolt t-shirt":
                return isElementDisplayed(removeBoltTShirtButton);
            case "sauce labs fleece jacket":
                return isElementDisplayed(removeFleeceJacketButton);
            case "sauce labs onesie":
                return isElementDisplayed(removeOnesieButton);
            case "test.allthethings() t-shirt (red)":
                return isElementDisplayed(removeTShirtRedButton);
            default:
                return false;
        }
    }

    // Cart Total Calculation - FIXED VERSION
    public double calculateTotalPrice() {
        double total = 0.0;

        if (isProductInCart("Sauce Labs Backpack")) {
            total += getProductPriceAsDouble("Sauce Labs Backpack") * getProductQuantityInCart("Sauce Labs Backpack");
        }
        if (isProductInCart("Sauce Labs Bike Light")) {
            total += getProductPriceAsDouble("Sauce Labs Bike Light") * getProductQuantityInCart("Sauce Labs Bike Light");
        }
        if (isProductInCart("Sauce Labs Bolt T-Shirt")) {
            total += getProductPriceAsDouble("Sauce Labs Bolt T-Shirt") * getProductQuantityInCart("Sauce Labs Bolt T-Shirt");
        }
        if (isProductInCart("Sauce Labs Fleece Jacket")) {
            total += getProductPriceAsDouble("Sauce Labs Fleece Jacket") * getProductQuantityInCart("Sauce Labs Fleece Jacket");
        }
        if (isProductInCart("Sauce Labs Onesie")) {
            total += getProductPriceAsDouble("Sauce Labs Onesie") * getProductQuantityInCart("Sauce Labs Onesie");
        }
        if (isProductInCart("Test.allTheThings() T-Shirt (Red)")) {
            total += getProductPriceAsDouble("Test.allTheThings() T-Shirt (Red)") * getProductQuantityInCart("Test.allTheThings() T-Shirt (Red)");
        }

        return total;
    }

    // Cart Items Count
    public int getCartItemsCount() {
        int count = 0;
        if (isProductInCart("Sauce Labs Backpack")) count++;
        if (isProductInCart("Sauce Labs Bike Light")) count++;
        if (isProductInCart("Sauce Labs Bolt T-Shirt")) count++;
        if (isProductInCart("Sauce Labs Fleece Jacket")) count++;
        if (isProductInCart("Sauce Labs Onesie")) count++;
        if (isProductInCart("Test.allTheThings() T-Shirt (Red)")) count++;
        return count;
    }

    // Empty Cart Verification
    public boolean isCartEmpty() {
        return getCartItemsCount() == 0;
    }

    // Helper method to check if element is displayed safely
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Remove all items from cart
    public void removeAllItemsFromCart() {
        if (isProductInCart("Sauce Labs Backpack")) removeProductFromCart("Sauce Labs Backpack");
        if (isProductInCart("Sauce Labs Bike Light")) removeProductFromCart("Sauce Labs Bike Light");
        if (isProductInCart("Sauce Labs Bolt T-Shirt")) removeProductFromCart("Sauce Labs Bolt T-Shirt");
        if (isProductInCart("Sauce Labs Fleece Jacket")) removeProductFromCart("Sauce Labs Fleece Jacket");
        if (isProductInCart("Sauce Labs Onesie")) removeProductFromCart("Sauce Labs Onesie");
        if (isProductInCart("Test.allTheThings() T-Shirt (Red)")) removeProductFromCart("Test.allTheThings() T-Shirt (Red)");
    }

    // Debug method for cart contents
    public void debugCartContents() {
        System.out.println("=== CART DEBUG INFO ===");
        System.out.println("Cart Items Count: " + getCartItemsCount());

        String[] products = {
                "Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)"
        };

        for (String product : products) {
            if (isProductInCart(product)) {
                double price = getProductPriceAsDouble(product);
                int quantity = getProductQuantityInCart(product);
                double subtotal = price * quantity;
                System.out.println(product + ": $" + price + " x " + quantity + " = $" + subtotal);
            }
        }

        double calculatedTotal = calculateTotalPrice();
        System.out.println("Calculated Total: $" + calculatedTotal);
        System.out.println("=====================");
    }
}