package com.projects.tests;

import com.projects.base.BaseTest;
import com.projects.pages.LoginPage;
import com.projects.pages.ProductsPage;
import com.projects.testdata.LoginTestData;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Swag Labs Mobile Tests")
@Feature("Products Functionality")
public class ProductsTest extends BaseTest {

    @Test
    @Story("View Products After Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the Products page loads after login and displays available items")
    public void productsPageShouldDisplayItemsAfterLogin() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        Assert.assertTrue(productsPage.isOnPage(), "✅ Products page was loaded");
        Assert.assertTrue(productsPage.isBackpackVisible(), "✅ Backpack product was visible");
    }

    @Test
    @Story("Add Product to Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that adding a product updates the cart badge count correctly")
    public void addItemToCartShouldIncreaseCartBadge() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        productsPage.addBackpackToCart();
        Assert.assertEquals(productsPage.getCartBadgeCount(), 1, "❌ Cart badge did not update correctly");
    }
}
