package org.fresh.pages.shop;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.fresh.pages.BasePage;
import org.openqa.selenium.WebElement;

public class PaymentPage extends BasePage {

    @AndroidFindBy(accessibility = "test-FINISH")
    private WebElement finishButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: OVERVIEW']")
    private WebElement paymentPageHeader;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Description']//android.widget.TextView[1]")
    private WebElement firstItemName;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Payment Information:']/following-sibling::android.widget.TextView[1]")
    private WebElement firstItemPrice;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Shipping Information:']/following-sibling::android.widget.TextView[1]" )
    private WebElement paymentInformation;


    private WebElement shippingInformation;

    public PaymentPage(AppiumDriver driver) {
        super(driver);
    }

    public void scrollToFinishButton() {
        scrollUntilTextIsVisible("FINISH");
    }

    public void waitForPaymentPageToOpen() {
        waitForElementToBeVisible(paymentPageHeader);
    }
    public void clickFinishButton() {
        scrollToFinishButton();
        waitForElementAndClick(finishButton);
    }
    public String getFirstProductLabelText() {
        return waitForElementAndGetText(firstItemName);
    }
    public String getFirstProductPriceText() {
        return waitForElementAndGetText(firstItemPrice);
    }

    public String getPaymentInformationText() {
        return waitForElementAndGetText(paymentInformation);
    }
    public String getShippingInformationText() {
        return waitForElementAndGetText(shippingInformation);
    }
}
