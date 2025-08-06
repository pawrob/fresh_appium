package org.fresh.pages.shop;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.fresh.pages.BasePage;
import org.openqa.selenium.WebElement;

public class ShopPage extends BasePage {


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
    private WebElement shopProductsHeader;

    // @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc='test-ADD TO CART'])[1]")
    // it can be used if the product name is dynamic
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sauce Labs Backpack']//parent::*/android.view.ViewGroup[last()]")
    private WebElement addBackpackToCartButton;

    private WebElement removeBackpackFromCartButton = addBackpackToCartButton; // Assuming the remove button is the same element

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sauce Labs Backpack']")
    private WebElement backpackProductLabel;

    @AndroidFindBy(accessibility = "test-Cart")
    private WebElement cartButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup/android.widget.TextView")
    private WebElement numberOfProductsInCart;

    public ShopPage(AppiumDriver driver) {
        super(driver);
    }


    public void addBackpackToCart() {
        waitForElementAndClick(addBackpackToCartButton);
    }

    public void removeBackpackFromCart() {
        waitForElementAndClick(removeBackpackFromCartButton);
    }

    public boolean isBackpackProductDisplayed() {
        return waitForElementToBeVisible(backpackProductLabel);
    }

    public int getNumberOfProductsInCart() {
        if (checkIfElementIsNotVisible(numberOfProductsInCart)) {
            return 0;
        }
        String text = waitForElementAndGetText(numberOfProductsInCart);
        if (text != null && !text.isEmpty()) {
            return Integer.parseInt(text);
        }
        return 0;
    }

    public CartPage openCart() {
        waitForElementAndClick(cartButton);
        CartPage cartPage= new CartPage(driver);
        cartPage.waitForCartPageToOpen();
        return cartPage;
    }

    public void waitForShopPageToOpen() {
        waitForElementToBeVisible(shopProductsHeader);
    }

}
