package org.fresh;

import org.fresh.pages.onboarding.LoginPage;
import org.fresh.pages.shop.ShopPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLogin extends BaseTest {

    @Test(groups = {"android"}, dataProvider = "loginData")
    public void testUserCanLogInToApp(String username, String password) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        ShopPage shopPage = loginPage.clickLoginButton();
        Assert.assertEquals(shopPage.getShopProductsHeaderText(), "PRODUCTS");

    }
}
