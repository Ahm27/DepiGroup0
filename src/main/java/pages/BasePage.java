package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void click(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    protected void type(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    // Add to BasePage.java
    @FindBy(how = How.CSS, using = "[data-test*='remove-']")
    private List<WebElement> removeButtons;

    // Helper method for dynamic data-test attributes
    protected WebElement findElementByDataTest(String dataTestValue) {
        return driver.findElement(By.cssSelector("[data-test='" + dataTestValue + "']"));
    }

    protected List<WebElement> findElementsByDataTest(String dataTestValue) {
        return driver.findElements(By.cssSelector("[data-test='" + dataTestValue + "']"));
    }
    // Add to BasePage.java
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }protected void waitForPageToLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}