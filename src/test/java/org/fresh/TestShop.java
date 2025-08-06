package org.fresh;

import org.fresh.helpers.LoginHelper;
import org.fresh.pages.shop.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestShop extends BaseTest {


    @Test(groups = {"android"}, dataProvider = "loginData")
    public void testUserCanCompleteOrder(String username, String password) {

        LoginHelper loginHelper = new LoginHelper(driver);
        ShopPage shopPage = loginHelper.loginToShop(username, password);

        int itemsInCartAfterLogin = shopPage.getNumberOfProductsInCart();
        Assert.assertEquals(itemsInCartAfterLogin, 0, "Number of products in cart is not zero after login");

        shopPage.addBackpackToCart();
        Assert.assertTrue(shopPage.isBackpackProductDisplayed(), "Backpack product is not displayed after adding to cart");

        int itemsInCartAfterAddition = shopPage.getNumberOfProductsInCart();
        Assert.assertEquals(itemsInCartAfterAddition, 1, "Number of products in cart is not updated to 1 after adding a product");

        CartPage cartPage = shopPage.openCart();

        Assert.assertEquals(cartPage.getFirstProductLabelText(), "Sauce Labs Backpack",
                "First product in cart is not 'Sauce Labs Backpack'");

        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();

        checkoutPage.fillCheckoutForm("Paweł", "Bucki", "12345");
        PaymentPage paymentPage = checkoutPage.clickContinueButton();
        Assert.assertEquals(paymentPage.getFirstProductLabelText(), "Sauce Labs Backpack",
                "First product in payment page is not 'Sauce Labs Backpack'");
        Assert.assertEquals(paymentPage.getFirstProductPriceText(), "$29.99",
                "First product price in payment page is not '$29.99'");
        Assert.assertEquals(paymentPage.getPaymentInformationText(), "SauceCard #31337",
                "Payment information in payment page is not 'SauceCard #31337'");
        Assert.assertEquals(paymentPage.getShippingInformationText(), "FREE PONY EXPRESS DELIVERY!",
                "Shipping information in payment page is not 'FREE PONY EXPRESS DELIVERY!'");

        Assert.assertEquals(paymentPage.getItemTotalText(), "Item total: $29.99",
                "Item total in payment page is not 'Item total: $29.99'");


        CompletedCheckoutPage completedCheckoutPage = paymentPage.clickFinishButton();
        Assert.assertTrue(completedCheckoutPage.checkIfThankYouMessageIsPresent(),
                "Thank you message is not present after completing checkout");
        completedCheckoutPage.clickBackHomeButton();

    }

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
        cartPage.clickContinueShoppingButton();

        int itemsInCartAfterRemoval = shopPage.getNumberOfProductsInCart();
        Assert.assertEquals(itemsInCartAfterRemoval, 0, "Number of products in cart is not updated to 0 after removing a product");
    }

}
