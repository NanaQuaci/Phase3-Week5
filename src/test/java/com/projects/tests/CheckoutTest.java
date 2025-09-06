package com.projects.tests;

import com.projects.base.BaseTest;
import com.projects.pages.LoginPage;
import com.projects.pages.ProductsPage;
import com.projects.pages.CartPage;
import com.projects.pages.CheckoutPage;
import com.projects.testdata.LoginTestData;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Swag Labs Mobile Tests")
@Feature("Checkout Functionality")
public class CheckoutTest extends BaseTest {

    @Test
    @Story("Successful Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a valid checkout flow completes the order successfully")
    public void validCheckoutShouldCompleteOrder() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        productsPage.addBackpackToCart();
        CartPage cartPage = productsPage.goToCart();
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();

        checkoutPage.fillCustomerDetails(
                LoginTestData.VALID_FIRST_NAME,
                LoginTestData.VALID_LAST_NAME,
                LoginTestData.VALID_POSTAL_CODE
        );

        checkoutPage.completeCheckout();
        Assert.assertTrue(checkoutPage.isOrderSuccessful(), "✅ Order was successful");
    }

    @Test
    @Story("Checkout with Empty Fields")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that leaving required checkout fields empty displays an error message")
    public void emptyFieldsShouldShowError() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        productsPage.addBackpackToCart();
        CartPage cartPage = productsPage.goToCart();
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();

        checkoutPage.fillCustomerDetails(
                LoginTestData.EMPTY_FIRST_NAME,
                LoginTestData.EMPTY_LAST_NAME,
                LoginTestData.EMPTY_POSTAL_CODE
        );

        Assert.assertTrue(checkoutPage.getErrorMessage().contains("required"),
                "Expected error message for empty fields");
    }

    @Test
    @Story("Checkout Confirmation Message")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that the correct confirmation message is displayed after successful checkout")
    public void validCheckoutShouldShowCorrectMessage() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        productsPage.addBackpackToCart();
        CartPage cartPage = productsPage.goToCart();
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();

        checkoutPage.fillCustomerDetails(
                LoginTestData.VALID_FIRST_NAME,
                LoginTestData.VALID_LAST_NAME,
                LoginTestData.VALID_POSTAL_CODE
        );

        checkoutPage.completeCheckout();
        Assert.assertTrue(checkoutPage.isMessageCorrect(), "✅ Order was successful");
    }
}
