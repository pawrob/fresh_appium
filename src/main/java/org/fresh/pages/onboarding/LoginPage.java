package org.fresh.pages.onboarding;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.fresh.pages.BasePage;
import org.fresh.pages.shop.ShopPage;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "test-Username")
    private WebElement usernameInputField;

    @AndroidFindBy(accessibility = "test-Password")
    private WebElement passwordInputField;

    @AndroidFindBy(accessibility = "test-LOGIN")
    private WebElement loginButton;

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public ShopPage clickLoginButton() {
        waitForElementAndClick(loginButton);
        ShopPage shopPage = new ShopPage(driver);
        shopPage.waitForShopPageToOpen();
        return shopPage;
    }

    public void typeUsername(String username) {
        waitForElementAndInputText(usernameInputField, username);
    }

    public void typePassword(String password) {
        waitForElementAndInputText(passwordInputField, password);
    }
}
