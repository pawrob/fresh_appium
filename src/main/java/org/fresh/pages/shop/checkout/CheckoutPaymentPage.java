package org.fresh.pages.shop.checkout;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.fresh.pages.BasePage;
import org.openqa.selenium.WebElement;

public class CheckoutPaymentPage extends BasePage {

    @AndroidFindBy(accessibility = "test-FINISH")
    private WebElement finishButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: OVERVIEW']")
    private WebElement paymentPageHeader;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Description']//android.widget.TextView[1]")
    private WebElement firstItemName;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Price']//android.widget.TextView[1]")
    private WebElement firstItemPrice;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Payment Information:']/following-sibling::android.widget.TextView[1]")
    private WebElement paymentInformationText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Shipping Information:']/following-sibling::android.widget.TextView[1]")
    private WebElement shippingInformationText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Shipping Information:']/following-sibling::android.widget.TextView[2]")
    private WebElement itemTotalText;

    public CheckoutPaymentPage(AppiumDriver driver) {
        super(driver);
    }

    public void scrollToFinishButton() {
        scrollUntilTextIsVisible("FINISH");
    }

    public void waitForPaymentPageToOpen() {
        waitForElementToBeVisible(paymentPageHeader);
    }

    public CompletedCheckoutPage clickFinishButtonAndOpenCompletedCheckoutPage() {
        scrollToFinishButton();
        waitForElementAndClick(finishButton);
        CompletedCheckoutPage completedCheckoutPage = new CompletedCheckoutPage(driver);
        completedCheckoutPage.waitForCompletedCheckoutPageToOpen();
        return completedCheckoutPage;
    }

    public String getFirstProductLabelText() {
        return waitForElementAndGetText(firstItemName);
    }

    public String getFirstProductPriceText() {
        return waitForElementAndGetText(firstItemPrice);
    }

    public String getPaymentInformationText() {
        return waitForElementAndGetText(paymentInformationText);
    }

    public String getShippingInformationText() {
        return waitForElementAndGetText(shippingInformationText);
    }

    public String getItemTotalText() {
        return waitForElementAndGetText(itemTotalText);
    }
}
