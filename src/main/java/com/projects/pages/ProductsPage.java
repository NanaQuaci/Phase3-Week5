package com.projects.pages;

import com.projects.base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Products']")
    private WebElement title;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sauce Labs Backpack']")
    private WebElement productBackpack;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Add To Cart']")
    private WebElement addToCartBtn;

    @AndroidFindBy(accessibility = "Cart")
    private WebElement cartBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='test-Cart badge']")
    private WebElement cartBadge;

    @AndroidFindBy(accessibility = "test-Menu")
    private WebElement menuBtn;

    public ProductsPage(AndroidDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return title.isDisplayed();
    }

    public ProductsPage addBackpackToCart() {
        click(addToCartBtn);
        return this;
    }

    public boolean isBackpackVisible() {
        return productBackpack.isDisplayed();
    }

    public int getCartBadgeCount() {
        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0; // if badge is not visible
        }
    }

    public CartPage goToCart() {
        click(cartBtn);
        return new CartPage(driver);
    }

    public MenuPage openMenu() {
        click(menuBtn);
        return new MenuPage(driver);
    }
}
