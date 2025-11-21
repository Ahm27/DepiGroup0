package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {

    // Header Elements
    @FindBy(className = "app_logo")
    private WebElement appLogo;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItemsLink;

    @FindBy(id = "about_sidebar_link")
    private WebElement aboutLink;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppStateLink;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement closeMenuButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCart;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    // Sort Dropdown
    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    // Product Items - Individual product containers
    @FindBy(xpath = "//div[@class='inventory_item' and .//div[text()='Sauce Labs Backpack']]")
    private WebElement backpackProduct;

    @FindBy(xpath = "//div[@class='inventory_item' and .//div[text()='Sauce Labs Bike Light']]")
    private WebElement bikeLightProduct;

    @FindBy(xpath = "//div[@class='inventory_item' and .//div[text()='Sauce Labs Bolt T-Shirt']]")
    private WebElement boltTShirtProduct;

    @FindBy(xpath = "//div[@class='inventory_item' and .//div[text()='Sauce Labs Fleece Jacket']]")
    private WebElement fleeceJacketProduct;

    @FindBy(xpath = "//div[@class='inventory_item' and .//div[text()='Sauce Labs Onesie']]")
    private WebElement onesieProduct;

    @FindBy(xpath = "//div[@class='inventory_item' and .//div[text()='Test.allTheThings() T-Shirt (Red)']]")
    private WebElement tShirtRedProduct;

    // Add to Cart buttons for specific products
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackToCart;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement addBikeLightToCart;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    private WebElement addBoltTShirtToCart;

    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    private WebElement addFleeceJacketToCart;

    @FindBy(id = "add-to-cart-sauce-labs-onesie")
    private WebElement addOnesieToCart;

    @FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
    private WebElement addTShirtRedToCart;

    // Remove buttons (appear after adding to cart)
    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeBackpack;

    @FindBy(id = "remove-sauce-labs-bike-light")
    private WebElement removeBikeLight;

    @FindBy(id = "remove-sauce-labs-bolt-t-shirt")
    private WebElement removeBoltTShirt;

    @FindBy(id = "remove-sauce-labs-fleece-jacket")
    private WebElement removeFleeceJacket;

    @FindBy(id = "remove-sauce-labs-onesie")
    private WebElement removeOnesie;

    @FindBy(id = "remove-test.allthethings()-t-shirt-(red)")
    private WebElement removeTShirtRed;

    // Product names and prices for verification
    @FindBy(xpath = "//div[text()='Sauce Labs Backpack']")
    private WebElement backpackName;

    @FindBy(xpath = "//div[text()='Sauce Labs Bike Light']")
    private WebElement bikeLightName;

    @FindBy(xpath = "//div[text()='Sauce Labs Bolt T-Shirt']")
    private WebElement boltTShirtName;

    @FindBy(xpath = "//div[text()='Sauce Labs Fleece Jacket']")
    private WebElement fleeceJacketName;

    @FindBy(xpath = "//div[text()='Sauce Labs Onesie']")
    private WebElement onesieName;

    @FindBy(xpath = "//div[text()='Test.allTheThings() T-Shirt (Red)']")
    private WebElement tShirtRedName;

    @FindBy(xpath = "//div[text()='Sauce Labs Backpack']/../../..//div[@class='inventory_item_price']")
    private WebElement backpackPrice;

    @FindBy(xpath = "//div[text()='Sauce Labs Bike Light']/../../..//div[@class='inventory_item_price']")
    private WebElement bikeLightPrice;

    @FindBy(xpath = "//div[text()='Sauce Labs Bolt T-Shirt']/../../..//div[@class='inventory_item_price']")
    private WebElement boltTShirtPrice;

    @FindBy(xpath = "//div[text()='Sauce Labs Fleece Jacket']/../../..//div[@class='inventory_item_price']")
    private WebElement fleeceJacketPrice;

    @FindBy(xpath = "//div[text()='Sauce Labs Onesie']/../../..//div[@class='inventory_item_price']")
    private WebElement onesiePrice;

    @FindBy(xpath = "//div[text()='Test.allTheThings() T-Shirt (Red)']/../../..//div[@class='inventory_item_price']")
    private WebElement tShirtRedPrice;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Page Verification Methods
    public boolean isHomePageDisplayed() {
        return appLogo.isDisplayed() && shoppingCart.isDisplayed() && pageTitle.isDisplayed();
    }

    public String getPageTitleText() {
        return pageTitle.getText();
    }

    public String getAppLogoText() {
        return appLogo.getText();
    }

    // Shopping Cart Methods
    public void clickShoppingCart() {
        click(shoppingCart);
    }

    public int getCartItemCount() {
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

    // Menu Methods
    public void clickMenuButton() {
        click(menuButton);
    }

    public void clickLogout() {
        waitForElementToBeClickable(logoutLink);
        logoutLink.click();
    }

    public void clickAllItems() {
        waitForElementToBeClickable(allItemsLink);
        allItemsLink.click();
    }

    public void clickAbout() {
        waitForElementToBeClickable(aboutLink);
        aboutLink.click();
    }

    public void clickResetAppState() {
        waitForElementToBeClickable(resetAppStateLink);
        resetAppStateLink.click();
    }

    public void closeMenu() {
        click(closeMenuButton);
    }

    public void logout() {
        clickMenuButton();
        clickLogout();
    }

    // Sort Methods
    public void sortProducts(String sortOption) {
        Select sortSelect = new Select(sortDropdown);
        switch (sortOption.toLowerCase()) {
            case "az":
            case "name a to z":
                sortSelect.selectByValue("az");
                break;
            case "za":
            case "name z to a":
                sortSelect.selectByValue("za");
                break;
            case "lohi":
            case "price low to high":
                sortSelect.selectByValue("lohi");
                break;
            case "hilo":
            case "price high to low":
                sortSelect.selectByValue("hilo");
                break;
            default:
                sortSelect.selectByValue("az");
        }
    }

    public String getCurrentSortOption() {
        Select sortSelect = new Select(sortDropdown);
        return sortSelect.getFirstSelectedOption().getText();
    }

    // Product Verification Methods
    public boolean isProductDisplayed(String productName) {
        switch (productName.toLowerCase()) {
            case "sauce labs backpack":
                return backpackName.isDisplayed();
            case "sauce labs bike light":
                return bikeLightName.isDisplayed();
            case "sauce labs bolt t-shirt":
                return boltTShirtName.isDisplayed();
            case "sauce labs fleece jacket":
                return fleeceJacketName.isDisplayed();
            case "sauce labs onesie":
                return onesieName.isDisplayed();
            case "test.allthethings() t-shirt (red)":
                return tShirtRedName.isDisplayed();
            default:
                return false;
        }
    }

    public String getProductPrice(String productName) {
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

    // Add to Cart Methods
    public void addBackpackToCart() {
        click(addBackpackToCart);
    }

    public void addBikeLightToCart() {
        click(addBikeLightToCart);
    }

    public void addBoltTShirtToCart() {
        click(addBoltTShirtToCart);
    }

    public void addFleeceJacketToCart() {
        click(addFleeceJacketToCart);
    }

    public void addOnesieToCart() {
        click(addOnesieToCart);
    }

    public void addTShirtRedToCart() {
        click(addTShirtRedToCart);
    }

    public void addProductToCartByName(String productName) {
        switch (productName.toLowerCase()) {
            case "sauce labs backpack":
                addBackpackToCart();
                break;
            case "sauce labs bike light":
                addBikeLightToCart();
                break;
            case "sauce labs bolt t-shirt":
                addBoltTShirtToCart();
                break;
            case "sauce labs fleece jacket":
                addFleeceJacketToCart();
                break;
            case "sauce labs onesie":
                addOnesieToCart();
                break;
            case "test.allthethings() t-shirt (red)":
                addTShirtRedToCart();
                break;
            default:
                throw new IllegalArgumentException("Product not found: " + productName);
        }
    }

    // Remove from Cart Methods
    public void removeBackpackFromCart() {
        click(removeBackpack);
    }

    public void removeBikeLightFromCart() {
        click(removeBikeLight);
    }

    public void removeBoltTShirtFromCart() {
        click(removeBoltTShirt);
    }

    public void removeFleeceJacketFromCart() {
        click(removeFleeceJacket);
    }

    public void removeOnesieFromCart() {
        click(removeOnesie);
    }

    public void removeTShirtRedFromCart() {
        click(removeTShirtRed);
    }

    public void removeProductFromCartByName(String productName) {
        switch (productName.toLowerCase()) {
            case "sauce labs backpack":
                removeBackpackFromCart();
                break;
            case "sauce labs bike light":
                removeBikeLightFromCart();
                break;
            case "sauce labs bolt t-shirt":
                removeBoltTShirtFromCart();
                break;
            case "sauce labs fleece jacket":
                removeFleeceJacketFromCart();
                break;
            case "sauce labs onesie":
                removeOnesieFromCart();
                break;
            case "test.allthethings() t-shirt (red)":
                removeTShirtRedFromCart();
                break;
            default:
                throw new IllegalArgumentException("Product remove button not found: " + productName);
        }
    }

    // Check if remove button is displayed (item is in cart)
    public boolean isProductInCart(String productName) {
        switch (productName.toLowerCase()) {
            case "sauce labs backpack":
                return removeBackpack.isDisplayed();
            case "sauce labs bike light":
                return removeBikeLight.isDisplayed();
            case "sauce labs bolt t-shirt":
                return removeBoltTShirt.isDisplayed();
            case "sauce labs fleece jacket":
                return removeFleeceJacket.isDisplayed();
            case "sauce labs onesie":
                return removeOnesie.isDisplayed();
            case "test.allthethings() t-shirt (red)":
                return removeTShirtRed.isDisplayed();
            default:
                return false;
        }
    }
}