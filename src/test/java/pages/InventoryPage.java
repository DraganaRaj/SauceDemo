package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {

    private WebDriver driver;

    private final By pageTitle = By.className("title");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean isProductVisible(String productName) {
        return driver.findElements(By.xpath("//div[text()='" + productName + "']")).size() > 0;
    }

    public void addToCart(String productName) {
        WebElement addButton = driver.findElement(By.xpath(
                "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button[contains(text(),'Add to cart')]"));
        addButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
    }

    public void removeFromCart(String productName) {
        WebElement removeButton = driver.findElement(By.xpath(
                "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button[contains(text(),'Remove')]"));
        removeButton.click();
    }

    public int getCartBadgeCount() {
        try {
            return Integer.parseInt(driver.findElement(cartBadge).getText());
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    public void openCart() {
        driver.findElement(cartIcon).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("cart.html"));
    }

    public void logout() {
        WebElement menu = driver.findElement(menuButton);
        menu.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public int getProductCount() {
        return driver.findElements(By.className("inventory_item")).size();
    }
}
