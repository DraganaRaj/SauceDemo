package login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    /**
     * Logs in with the valid standard_user and verifies successful navigation to the Inventory page.
     */
    @Test
    public void loginAsStandardUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Standard user login failed.");
    }

    /**
     * Attempts login with locked_out_user and verifies that an error message is displayed.
     */
    @Test
    public void loginAsLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Locked out user should not be able to log in.");
    }

    /**
     * Logs in with problem_user and verifies successful access to the Inventory page.
     * Note: This user may exhibit visual bugs that require additional UI validation.
     */
    @Test
    public void loginAsProblemUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("problem_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Problem user login failed.");
    }

    /**
     * Logs in with performance_glitch_user and verifies successful login.
     * This user may have delayed responses due to simulated performance issues.
     */
    @Test
    public void loginAsPerformanceGlitchUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("performance_glitch_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Performance glitch user login failed.");
    }

    /**
     * Logs in with error_user and verifies access to the Inventory page.
     * This user may cause JavaScript or image loading errors.
     */
    @Test
    public void loginAsErrorUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("error_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Error user login failed.");
    }

    /**
     * Logs in with visual_user and verifies access to the Inventory page.
     * This user is typically used to test visual differences or layout issues.
     */
    @Test
    public void loginAsVisualUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("visual_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Visual user login failed.");
    }

    /**
     * Attempts login with standard_user and an incorrect password,
     * and verifies that an error message is displayed.
     */
    @Test
    public void loginWithIncorrectPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrong_password");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed for incorrect password.");
    }


}
