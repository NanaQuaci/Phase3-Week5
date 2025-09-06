package com.projects.base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected AndroidDriver driver;
    private static final Duration TIMEOUT = Duration.ofSeconds(20);

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(20)), this);
    }

    protected void waitForVisibility(WebElement element) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    protected void type(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    public abstract boolean isOnPage();  // each page must define its "identity"

    public void scrollToText(String visibleText) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + visibleText + "\"))"
        ));
    }

}
