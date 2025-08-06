package org.fresh;

import org.fresh.helpers.LoginHelper;
import org.fresh.pages.shop.CartPage;
import org.fresh.pages.shop.ShopPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCartInteractions extends BaseTest {
    @Test(groups = {"android"}, dataProvider = "loginData")
    public void testUserCanRemoveProductFromCartFromShopPage(String username, String password) {

        LoginHelper loginHelper = new LoginHelper(driver);
        ShopPage shopPage = loginHelper.loginToShop(username, password);

        shopPage.addBackpackToCart();

        int itemsInCartAfterAddition = shopPage.getNumberOfProductsInCart();
        Assert.assertEquals(itemsInCartAfterAddition, 1, "Number of products in cart is not updated to 1 after adding a product");

        shopPage.removeBackpackFromCart();

        int itemsInCartAfterRemoval = shopPage.getNumberOfProductsInCart();
        Assert.assertEquals(itemsInCartAfterRemoval, 0, "Number of products in cart is not updated to 0 after removing a product");
    }

    @Test(groups = {"android"}, dataProvider = "loginData")
    public void testUserCanRemoveProductFromCartFromCartPage(String username, String password) {

        LoginHelper loginHelper = new LoginHelper(driver);
        ShopPage shopPage = loginHelper.loginToShop(username, password);

        shopPage.addBackpackToCart();


        int itemsInCartAfterAddition = shopPage.getNumberOfProductsInCart();
        Assert.assertEquals(itemsInCartAfterAddition, 1, "Number of products in cart is not updated to 1 after adding a product");
        CartPage cartPage = shopPage.openCart();

        Assert.assertEquals(cartPage.getFirstProductLabelText(), "Sauce Labs Backpack",
                "First product in cart is not 'Sauce Labs Backpack'");
        cartPage.removeFirstProductFromCart();
        cartPage.clickContinueShoppingButtonAndOpenShopPage();

        int itemsInCartAfterRemoval = shopPage.getNumberOfProductsInCart();
        Assert.assertEquals(itemsInCartAfterRemoval, 0, "Number of products in cart is not updated to 0 after removing a product");
    }
}
