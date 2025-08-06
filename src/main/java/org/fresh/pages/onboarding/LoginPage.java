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

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']//android.widget.TextView[1]")
    private WebElement loginValidationErrorMessage;

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public ShopPage clickLoginButtonAndOpenShopPage() {
        waitForElementAndClick(loginButton);
        ShopPage shopPage = new ShopPage(driver);
        shopPage.waitForShopPageToOpen();
        return shopPage;
    }


    public void clickLoginButton() {
        waitForElementAndClick(loginButton);
    }

    public void typeUsername(String username) {
        waitForElementAndInputText(usernameInputField, username);
    }

    public void typePassword(String password) {
        waitForElementAndInputText(passwordInputField, password);
    }

    public String getLoginValidationErrorMessage() {
        return waitForElementAndGetText(loginValidationErrorMessage);
    }
}
