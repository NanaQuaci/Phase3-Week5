package com.projects.tests;

import com.projects.base.BaseTest;
import com.projects.pages.LoginPage;
import com.projects.pages.ProductsPage;
import com.projects.pages.CartPage;
import com.projects.testdata.LoginTestData;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Swag Labs Mobile Tests")
@Feature("Cart Functionality")
public class CartTest extends BaseTest {

    @Test
    @Story("Add Item to Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that when a user adds an item, it correctly appears in the cart")
    public void addedItemShouldAppearInCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        productsPage.addBackpackToCart();
        CartPage cartPage = productsPage.goToCart();

        Assert.assertTrue(cartPage.isOnPage(), "✅ Cart page loads");
        Assert.assertTrue(cartPage.isBackpackInCart(), "✅ Backpack found in cart");
    }

    @Test
    @Story("Remove Item from Cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that removing an item from the cart makes it empty")
    public void removeItemShouldEmptyCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        productsPage.addBackpackToCart();
        CartPage cartPage = productsPage.goToCart();
        cartPage.removeBackpackFromCart();

        Assert.assertTrue(cartPage.isCartEmpty(), "✅ Cart should be empty after removing item");
    }
}
