package org.fresh;

import org.fresh.pages.onboarding.LoginPage;
import org.fresh.pages.shop.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLogin extends BaseTest {

    @Test(groups = {"android"}, dataProvider = "loginData")
    public void testUserCanLoginToApp(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        ShopPage shopPage = loginPage.clickLoginButtonAndOpenShopPage();
        shopPage.waitForShopPageToOpen();

    }

    @Test(groups = {"android"})
    public void testValidationMessageEmptyInputOnLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getLoginValidationErrorMessage(), "Username is required", "Validation message for empty username is incorrect");
        loginPage.typeUsername("invalidUser");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getLoginValidationErrorMessage(), "Password is required", "Validation message for empty password is incorrect");
    }

    @Test(groups = {"android"})
    public void testValidationMessageWrongCredentialsOnLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("invalidUser");
        loginPage.typePassword("invalidPass");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getLoginValidationErrorMessage(),
                "Username and password do not match any user in this service.",
                "Validation message for wrong credentials is incorrect");
    }

    @Test(groups = {"android"}, dataProvider = "blockedUserData")
    public void testValidationMessageBlockedUser(String username,String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getLoginValidationErrorMessage(),
                "Sorry, this user has been locked out.", "Validation message for blocked user is incorrect");
    }
}
