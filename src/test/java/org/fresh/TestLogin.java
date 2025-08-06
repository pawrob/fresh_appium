package org.fresh;

import org.fresh.pages.onboarding.LoginPage;
import org.fresh.pages.shop.CartPage;
import org.fresh.pages.shop.CheckoutPage;
import org.fresh.pages.shop.PaymentPage;
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



        paymentPage.clickFinishButton();


    }
}
