package com.projects.pages;

import com.projects.base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Checkout' or @text='CHECKOUT: YOUR INFORMATION']")
    private WebElement checkoutTitle;

    @AndroidFindBy(accessibility = "test-First Name")
    private WebElement firstNameField;

    @AndroidFindBy(accessibility = "test-Last Name")
    private WebElement lastNameField;

    @AndroidFindBy(accessibility = "test-Zip/Postal Code")
    private WebElement postalCodeField;

    @AndroidFindBy(accessibility = "test-CONTINUE")
    private WebElement continueBtn;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Finish']")
    private WebElement finishBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']")
    private WebElement thankYouMsg;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Error') or contains(@text,'is required')]")
    private WebElement errorMessage;

    public CheckoutPage(AndroidDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return checkoutTitle.isDisplayed();
    }

    public CheckoutPage fillCustomerDetails(String firstName, String lastName, String postalCode) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(postalCodeField, postalCode);
        click(continueBtn);
        return this;
    }

    public CheckoutPage completeCheckout() {
        click(finishBtn);
        return this;
    }

    public boolean isOrderSuccessful() {
        return thankYouMsg.isDisplayed();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
