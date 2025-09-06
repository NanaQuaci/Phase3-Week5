package com.projects.tests;

import com.projects.base.BaseTest;
import com.projects.pages.LoginPage;
import com.projects.testdata.*;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Swag Labs Mobile Tests")
@Feature("Login Functionality")
public class LoginTest extends BaseTest {

    @Test
    @Story("Valid Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a valid login navigates the user to the Products page")
    public void validLoginShouldNavigateToProducts() {
        LoginPage login = new LoginPage(driver);
        login.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        Assert.assertTrue(login.isLoginSuccessful(),
                "Expected user to land on Products page after valid login.");
    }

    @Test
    @Story("Invalid Login - Wrong Credentials")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that using invalid credentials displays the appropriate error message")
    public void invalidLoginShouldShowErrorMessage() {
        LoginPage login = new LoginPage(driver);
        login.login(LoginTestData.INVALID_USERNAME, LoginTestData.INVALID_PASSWORD);

        Assert.assertTrue(login.getErrorMessage().contains("Username and password"),
                "Expected error message for invalid login.");
    }

    @Test
    @Story("Locked Out User Login")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that a locked-out user cannot log in and sees the correct error message")
    public void lockedOutUserShouldNotLogin() {
        LoginPage login = new LoginPage(driver);
        login.login(LoginTestData.INVALID_USERNAME, LoginTestData.VALID_PASSWORD);
        Assert.assertTrue(login.getErrorMessage().contains("Username and password do not match any user in this service"),
                "Expected locked-out error message.");
    }

}
