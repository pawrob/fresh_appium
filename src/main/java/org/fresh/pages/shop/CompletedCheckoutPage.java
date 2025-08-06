package org.fresh.pages.shop;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.fresh.pages.BasePage;
import org.openqa.selenium.WebElement;

public class CompletedCheckoutPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: COMPLETE!']")
    private WebElement completedCheckoutHeader;

    @AndroidFindBy(accessibility = "test-BACK HOME")
    private WebElement backHomeButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']")
    private WebElement thankYouMessage;


    public CompletedCheckoutPage(AppiumDriver driver) {
        super(driver);
    }

    public void waitForCompletedCheckoutPageToOpen() {
        waitForElementToBeVisible(completedCheckoutHeader);
    }

    public Boolean checkIfThankYouMessageIsPresent() {
        return waitForElementToBeVisible(thankYouMessage);
    }

    public ShopPage clickBackHomeButton() {
        waitForElementAndClick(backHomeButton);
        ShopPage shopPage = new ShopPage(driver);
        shopPage.waitForShopPageToOpen();
        return shopPage;
    }
}
