package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By title = By.className("title");
    private final By sortDropdown = By.className("product_sort_container");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By inventoryItems = By.className("inventory_item");

    public String getPageTitle() {
        return driver.findElement(title).getText();
    }

    public boolean isProductVisible(String productName) {
        return driver.findElement(By.xpath("//div[text()='" + productName + "']")).isDisplayed();
    }

    public void addToCart(String productName) {
        driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button")).click();
    }

    public void removeFromCart(String productName) {
        driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button")).click();
    }

    public int getCartBadgeCount() {
        try {
            return Integer.parseInt(driver.findElement(cartBadge).getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    public void openMenu() {
        driver.findElement(menuButton).click();
    }

    public void logout() {
        openMenu();
        driver.findElement(logoutLink).click();
    }

    public int getProductCount() {
        return driver.findElements(inventoryItems).size();
    }
}

