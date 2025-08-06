package org.fresh;

import org.fresh.pages.onboarding.LoginPage;
import org.fresh.pages.shop.CartPage;
import org.fresh.pages.shop.CheckoutPage;
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


        int itemsInCartAfterLogin = shopPage.getNumberOfProductsInCart();
        Assert.assertEquals(itemsInCartAfterLogin, 0, "Number of products in cart is not zero after login");

        shopPage.addBackpackToCart();
        Assert.assertTrue(shopPage.isBackpackProductDisplayed(), "Backpack product is not displayed after adding to cart");

        int updatedNumber = shopPage.getNumberOfProductsInCart();
        Assert.assertEquals(updatedNumber, 1, "Number of products in cart is not updated to 1 after adding a product");

        CartPage cartPage = shopPage.openCart();

        Assert.assertEquals(cartPage.getFirstProductLabelText(), "Sauce Labs Backpack",
                "First product in cart is not 'Sauce Labs Backpack'");

        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();


    }
}
