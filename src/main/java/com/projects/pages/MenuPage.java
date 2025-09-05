package com.projects.pages;

import com.projects.base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MenuPage extends BasePage {

    @AndroidFindBy(accessibility = "test-Menu")
    private WebElement menuButton;

    @AndroidFindBy(accessibility = "test-LOGOUT")
    private WebElement logoutButton;

    public MenuPage(AndroidDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        // You can check the menu button is visible as proof you’re on this page
        return menuButton.isDisplayed();
    }

    public boolean hasLogoutOption() {
        return logoutButton.isDisplayed();
    }

    public LoginPage logout() {
        click(logoutButton);
        return new LoginPage(driver);
    }
}
