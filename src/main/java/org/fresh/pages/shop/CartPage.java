package org.fresh.pages.shop;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.fresh.pages.BasePage;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {


    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Item']/android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[1]")
    private WebElement firstProductLabel;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='YOUR CART']")
    private WebElement cartPageHeader;

    @AndroidFindBy(accessibility = "test-CHECKOUT")
    private WebElement checkoutButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Price']/android.view.ViewGroup[@content-desc='test-REMOVE']")
    private WebElement removeFirstProductButton;

    @AndroidFindBy(accessibility = "test-CONTINUE SHOPPING")
    private WebElement continueShoppingButton;

    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    public String getFirstProductLabelText() {
        return waitForElementAndGetText(firstProductLabel);
    }

    public CheckoutPage clickCheckoutButtonAndOpenCheckoutPage() {
        waitForElementAndClick(checkoutButton);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.waitForCheckoutPageToOpen();
        return checkoutPage;
    }

    public void waitForCartPageToOpen() {
        waitForElementToBeVisible(cartPageHeader);
    }

    public void removeFirstProductFromCart() {
        waitForElementAndClick(removeFirstProductButton);
    }

    public ShopPage clickContinueShoppingButtonAndOpenShopPage() {
        waitForElementAndClick(continueShoppingButton);
        ShopPage shopPage = new ShopPage(driver);
        shopPage.waitForShopPageToOpen();
        return shopPage;
    }

}
