package com.projects.tests;

import com.projects.base.BaseTest;
import com.projects.pages.LoginPage;
import com.projects.pages.ProductsPage;
import com.projects.pages.CartPage;
import com.projects.pages.CheckoutPage;
import com.projects.testdata.LoginTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsFlowTest extends BaseTest {

    @Test
    public void endToEndPurchaseFlow() {
        // Step 1: Login and land on Products page
        ProductsPage productsPage = new LoginPage(driver)
                .login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);
        Assert.assertTrue(productsPage.isOnPage(), "❌ Products page did not load");

        // Step 2: Add backpack to cart and navigate to cart
        CartPage cartPage = productsPage
                .addBackpackToCart()
                .goToCart();
        Assert.assertTrue(cartPage.isOnPage(), "❌ Cart page did not load");
        Assert.assertTrue(cartPage.isBackpackInCart(), "❌ Backpack not found in cart");

        // Step 3: Proceed to checkout and fill details
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        Assert.assertTrue(checkoutPage.isOnPage(), "❌ Checkout page did not load");

        checkoutPage.fillCustomerDetails(
                LoginTestData.VALID_FIRST_NAME,
                LoginTestData.VALID_LAST_NAME,
                LoginTestData.VALID_POSTAL_CODE
        );

        // Step 4: Complete checkout
        checkoutPage.completeCheckout();
        Assert.assertTrue(checkoutPage.isOrderSuccessful(), "❌ Order was not successful");
    }
}
