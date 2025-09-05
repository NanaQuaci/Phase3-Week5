package com.projects.tests;

import com.projects.base.BaseTest;
import com.projects.pages.LoginPage;
import com.projects.pages.ProductsPage;
import com.projects.testdata.LoginTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {

    @Test
    public void productsPageShouldDisplayItemsAfterLogin() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        Assert.assertTrue(productsPage.isOnPage(), "❌ Products page did not load");
        Assert.assertTrue(productsPage.isBackpackVisible(), "❌ Backpack product not visible");
    }

    @Test
    public void addItemToCartShouldIncreaseCartBadge() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        productsPage.addBackpackToCart();
        Assert.assertEquals(productsPage.getCartBadgeCount(), 1, "❌ Cart badge did not update correctly");
    }
}
