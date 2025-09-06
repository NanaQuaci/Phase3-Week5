package com.projects.pages;

import com.projects.base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Username']")
    private WebElement usernameField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Password']")
    private WebElement passwordField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='LOGIN']")
    private WebElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
    private WebElement productsTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Username and password')]")
    private WebElement errorMessage;

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public ProductsPage login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);

        return new ProductsPage(driver);
    }

    public void loginExpectingFailure(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }

    public boolean isOnPage() {
        return usernameField.isDisplayed();
    }

    public boolean isLoginSuccessful() {
        return productsTitle.isDisplayed();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
