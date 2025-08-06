package org.fresh.helpers;

import io.appium.java_client.AppiumDriver;
import org.fresh.pages.BasePage;
import org.fresh.pages.onboarding.LoginPage;
import org.fresh.pages.shop.ShopPage;

public class LoginHelper extends BasePage {

    public LoginHelper(AppiumDriver driver) {
        super(driver);
    }

    public ShopPage loginToShop(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        return loginPage.clickLoginButtonAndOpenShopPage();
    }
}
