package org.fresh.helpers;

import io.appium.java_client.AppiumDriver;
import org.fresh.pages.BasePage;
import org.fresh.pages.shop.CartPage;
import org.fresh.pages.shop.CheckoutPage;
import org.fresh.pages.shop.ShopPage;

public class OrderDetailsHelper extends BasePage {
    public OrderDetailsHelper(AppiumDriver driver) {
        super(driver);
    }


    public CheckoutPage goToCheckoutPage(String username,String password) {
        LoginHelper loginHelper = new LoginHelper(driver);
        ShopPage shopPage = loginHelper.loginToShop(username, password);
        CartPage cartPage = shopPage.openCart();
        return cartPage.clickCheckoutButtonAndOpenCheckoutPage();
    }
}
