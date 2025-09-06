package com.projects.tests;

import com.projects.base.BaseTest;
import com.projects.pages.LoginPage;
import com.projects.pages.ProductsPage;
import com.projects.pages.MenuPage;
import com.projects.testdata.LoginTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MenuTest extends BaseTest {

    @Test
    public void menuShouldOpenAndShowOptions() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        MenuPage menuPage = productsPage.openMenu();
        Assert.assertTrue(menuPage.isOnPage(), "✅ Menu page opened");
        Assert.assertTrue(menuPage.hasLogoutOption(), "✅ Logout option found in menu");
    }

    @Test
    public void logoutShouldReturnToLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        MenuPage menuPage = productsPage.openMenu();
        LoginPage returnedLoginPage = menuPage.logout();

        Assert.assertTrue(returnedLoginPage.isOnPage(), "✅ Returned to login page after logout");
    }
}
