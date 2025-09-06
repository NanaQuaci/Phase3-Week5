package com.projects.tests;

import com.projects.base.BaseTest;
import com.projects.pages.LoginPage;
import com.projects.testdata.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginShouldNavigateToProducts() {
        LoginPage login = new LoginPage(driver);
        login.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        Assert.assertTrue(login.isLoginSuccessful(),
                "Expected user to land on Products page after valid login.");
    }

    @Test
    public void invalidLoginShouldShowErrorMessage() {
        LoginPage login = new LoginPage(driver);
        login.login(LoginTestData.INVALID_USERNAME, LoginTestData.INVALID_PASSWORD);

        Assert.assertTrue(login.getErrorMessage().contains("Username and password"),
                "Expected error message for invalid login.");
    }

    @Test
    public void lockedOutUserShouldNotLogin() {
        LoginPage login = new LoginPage(driver);
        login.login(LoginTestData.INVALID_USERNAME, LoginTestData.VALID_PASSWORD);
        Assert.assertTrue(login.getErrorMessage().contains("Username and password do not match any user in this service"),
                "Expected locked-out error message.");
    }

}
