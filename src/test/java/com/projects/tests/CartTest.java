package com.projects.tests;

import com.projects.base.BaseTest;
import com.projects.pages.LoginPage;
import com.projects.pages.ProductsPage;
import com.projects.pages.CartPage;
import com.projects.testdata.LoginTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void addedItemShouldAppearInCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        productsPage.addBackpackToCart();
        CartPage cartPage = productsPage.goToCart();

        Assert.assertTrue(cartPage.isOnPage(), "❌ Cart page did not load");
        Assert.assertTrue(cartPage.isBackpackInCart(), "❌ Backpack not found in cart");
    }

    @Test
    public void removeItemShouldEmptyCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        productsPage.addBackpackToCart();
        CartPage cartPage = productsPage.goToCart();
        cartPage.removeBackpackFromCart();

        Assert.assertTrue(cartPage.isCartEmpty(), "❌ Cart should be empty after removing item");
    }
}
