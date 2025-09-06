package com.projects.tests;

import com.projects.base.BaseTest;
import com.projects.pages.LoginPage;
import com.projects.pages.ProductsPage;
import com.projects.pages.MenuPage;
import com.projects.testdata.LoginTestData;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Swag Labs Mobile Tests")
@Feature("Menu Functionality")
public class MenuTest extends BaseTest {

    @Test
    @Story("Open Menu")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the menu opens successfully and displays the expected options")
    public void menuShouldOpenAndShowOptions() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        MenuPage menuPage = productsPage.openMenu();
        Assert.assertTrue(menuPage.isOnPage(), "✅ Menu page opened");
        Assert.assertTrue(menuPage.hasLogoutOption(), "✅ Logout option found in menu");
    }

    @Test
    @Story("Logout from Menu")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that logging out from the menu returns the user to the login page")
    public void logoutShouldReturnToLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        MenuPage menuPage = productsPage.openMenu();
        LoginPage returnedLoginPage = menuPage.logout();

        Assert.assertTrue(returnedLoginPage.isOnPage(), "✅ Returned to login page after logout");
    }
}
