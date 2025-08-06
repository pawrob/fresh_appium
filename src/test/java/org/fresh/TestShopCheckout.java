package org.fresh;

import org.fresh.helpers.LoginHelper;
import org.fresh.helpers.OrderDetailsHelper;
import org.fresh.pages.shop.*;
import org.fresh.pages.shop.checkout.CheckoutInformationPage;
import org.fresh.pages.shop.checkout.CompletedCheckoutPage;
import org.fresh.pages.shop.checkout.CheckoutPaymentPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestShopCheckout extends BaseTest {


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

        CheckoutInformationPage checkoutInformationPage = cartPage.clickCheckoutButtonAndOpenCheckoutPage();

        checkoutInformationPage.fillCheckoutForm("Paweł", "Bucki", "12345");
        CheckoutPaymentPage checkoutPaymentPage = checkoutInformationPage.clickContinueButtonAndOpenPaymentPage();
        Assert.assertEquals(checkoutPaymentPage.getFirstProductLabelText(), "Sauce Labs Backpack",
                "First product in payment page is not 'Sauce Labs Backpack'");
        Assert.assertEquals(checkoutPaymentPage.getFirstProductPriceText(), "$29.99",
                "First product price in payment page is not '$29.99'");
        Assert.assertEquals(checkoutPaymentPage.getPaymentInformationText(), "SauceCard #31337",
                "Payment information in payment page is not 'SauceCard #31337'");
        Assert.assertEquals(checkoutPaymentPage.getShippingInformationText(), "FREE PONY EXPRESS DELIVERY!",
                "Shipping information in payment page is not 'FREE PONY EXPRESS DELIVERY!'");

        Assert.assertEquals(checkoutPaymentPage.getItemTotalText(), "Item total: $29.99",
                "Item total in payment page is not 'Item total: $29.99'");


        CompletedCheckoutPage completedCheckoutPage = checkoutPaymentPage.clickFinishButtonAndOpenCompletedCheckoutPage();
        Assert.assertTrue(completedCheckoutPage.checkIfThankYouMessageIsPresent(),
                "Thank you message is not present after completing checkout");
        completedCheckoutPage.clickBackHomeButtonAndOpenShopPage();

    }



    @Test(groups = {"android"}, dataProvider = "loginData")
    public void testValidationMessagesOnCheckoutPage(String username, String password) {

        OrderDetailsHelper orderDetailsHelper = new OrderDetailsHelper(driver);
        CheckoutInformationPage checkoutInformationPage = orderDetailsHelper.goToCheckoutPage(username, password);

        checkoutInformationPage.clickContinueButton();
        Assert.assertEquals(checkoutInformationPage.getCheckoutValidationErrorMessage(), "First Name is required");
        checkoutInformationPage.typeFirstName("Paweł");
        checkoutInformationPage.clickContinueButton();
        Assert.assertEquals(checkoutInformationPage.getCheckoutValidationErrorMessage(), "Last Name is required");
        checkoutInformationPage.typeLastName("Bucki");
        checkoutInformationPage.clickContinueButton();
        Assert.assertEquals(checkoutInformationPage.getCheckoutValidationErrorMessage(), "Postal Code is required");
    }

}
