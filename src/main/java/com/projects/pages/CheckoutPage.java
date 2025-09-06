package com.projects.pages;

import com.projects.base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Checkout' or @text='CHECKOUT: YOUR INFORMATION']")
    private WebElement checkoutTitle;

    @AndroidFindBy(xpath = "//*[@content-desc='test-First Name']")
    private WebElement firstNameField;

    @AndroidFindBy(xpath = "//*[@content-desc='test-Last Name']")
    private WebElement lastNameField;

    @AndroidFindBy(xpath = "//*[@content-desc='test-Zip/Postal Code']")
    private WebElement postalCodeField;

    @AndroidFindBy(xpath = "//*[@content-desc='test-CONTINUE']")
    private WebElement continueBtn;

//    @AndroidFindBy(xpath = "//android.widget.Button[@text='FINISH']")
//    private WebElement finishBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='FINISH']")
    private WebElement finishBtn;


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']")
    private WebElement thankYouMsg;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='THANK YOU FOR YOUR ORDER']")
    private WebElement correctThankYouMsg;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'required') or contains(@text,'Error')]")
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
        scrollToText("FINISH");
        click(finishBtn);
        return this;
    }

    public boolean isOrderSuccessful() {
        return thankYouMsg.isDisplayed();
    }

    public boolean isMessageCorrect() {
        return correctThankYouMsg.isDisplayed();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
