package com.projects.pages;

import com.projects.base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your Cart']")
    private WebElement cartTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sauce Labs Backpack']")
    private WebElement cartItemBackpack;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Remove']")
    private WebElement removeBackpackBtn;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Checkout']")
    private WebElement checkoutBtn;

    public CartPage(AndroidDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return cartTitle.isDisplayed();
    }

    public boolean isBackpackInCart() {
        try {
            return cartItemBackpack.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public CartPage removeBackpackFromCart() {
        click(removeBackpackBtn);
        return this;
    }

    public boolean isCartEmpty() {
        try {
            return !cartItemBackpack.isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }

    public CheckoutPage proceedToCheckout() {
        click(checkoutBtn);
        return new CheckoutPage(driver);
    }
}
