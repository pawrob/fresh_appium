package org.fresh.pages.shop.checkout;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.fresh.pages.BasePage;
import org.openqa.selenium.WebElement;

public class CheckoutInformationPage extends BasePage {

    @AndroidFindBy(accessibility = "test-First Name")
    private WebElement firstNameInputField;

    @AndroidFindBy(accessibility = "test-Last Name")
    private WebElement lastNameInputField;

    @AndroidFindBy(accessibility = "test-Zip/Postal Code")
    private WebElement zipCodeInputField;

    @AndroidFindBy(accessibility = "test-CONTINUE")
    private WebElement continueButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: INFORMATION']")
    private WebElement checkoutPageHeader;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']//android.widget.TextView[1]")
    private WebElement checkoutValidationErrorMessage;

    public CheckoutInformationPage(AppiumDriver driver) {
        super(driver);
    }

    public void typeFirstName(String firstName) {
        waitForElementAndInputText(firstNameInputField, firstName);
    }

    public void typeLastName(String lastName) {
        waitForElementAndInputText(lastNameInputField, lastName);
    }

    public void typeZipCode(String zipCode) {
        waitForElementAndInputText(zipCodeInputField, zipCode);
    }

    public void fillCheckoutForm(String firstName, String lastName, String zipCode) {
        typeFirstName(firstName);
        typeLastName(lastName);
        typeZipCode(zipCode);
    }

    public CheckoutPaymentPage clickContinueButtonAndOpenPaymentPage() {
        waitForElementAndClick(continueButton);
        CheckoutPaymentPage checkoutPaymentPage = new CheckoutPaymentPage(driver);
        checkoutPaymentPage.waitForPaymentPageToOpen();

        return checkoutPaymentPage;
    }

    public void clickContinueButton() {
        waitForElementAndClick(continueButton);
    }

    public void waitForCheckoutPageToOpen() {
        waitForElementToBeVisible(checkoutPageHeader);
    }

    public String getCheckoutValidationErrorMessage() {
        return checkoutValidationErrorMessage.getText();
    }
}
