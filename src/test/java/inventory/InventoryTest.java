package inventory;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTest extends BaseTest {

    InventoryPage inventoryPage;

    /**
     * Logs in as standard_user before each test.
     */
    @BeforeMethod
    public void loginFirst() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
    }

    /**
     * Verifies that the Inventory Page title is "Products".
     */
    @Test
    public void verifyTitleIsCorrect() {
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Incorrect inventory page title.");
    }

    /**
     * Checks that the first product (Sauce Labs Backpack) is displayed on the page.
     */
    @Test
    public void verifyFirstProductVisible() {
        Assert.assertTrue(inventoryPage.isProductVisible("Sauce Labs Backpack"), "Product is not visible.");
    }

    /**
     * Adds a product to the cart and verifies that the cart badge shows 1 item.
     */
    @Test
    public void addProductToCartShowsBadge() {
        inventoryPage.addToCart("Sauce Labs Backpack");
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 1, "Cart badge count is incorrect.");
    }

    /**
     * Adds and then removes a product from the cart and verifies that the cart badge is removed.
     */
    @Test
    public void removeProductFromCartRemovesBadge() {
        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.removeFromCart("Sauce Labs Backpack");
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 0, "Cart badge should be removed.");
    }

    /**
     * Navigates to the cart from the inventory page and checks the URL contains "cart".
     */
    @Test
    public void navigateToCartFromInventory() {
        inventoryPage.openCart();
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("cart"), "Not navigated to cart page.");
    }

    /**
     * Logs out from the inventory page and checks that the user is redirected to the login page.
     */
    @Test
    public void logoutShouldReturnToLoginPage() {
        inventoryPage.logout();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("saucedemo.com"), "User is not logged out.");
    }

    /**
     * Verifies that exactly 6 products are displayed on the inventory page.
     */
    @Test
    public void verifyNumberOfProductsDisplayed() {
        int count = inventoryPage.getProductCount();
        Assert.assertEquals(count, 6, "Unexpected number of products on the page.");
    }
}
