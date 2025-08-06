package org.fresh.pages.shop;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.fresh.pages.BasePage;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {


    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Item']/android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[1]")
    private WebElement firstProductLabel;

    @AndroidFindBy(accessibility = "test-CHECKOUT")
    private WebElement checkoutButton;

    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    public String getFirstProductLabelText() {
        return waitForElementAndGetText(firstProductLabel);
    }

    public CheckoutPage clickCheckoutButton() {
        waitForElementAndClick(checkoutButton);
        return new CheckoutPage(driver);
    }


}
