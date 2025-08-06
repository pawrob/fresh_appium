package org.fresh.pages.shop;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.fresh.pages.BasePage;
import org.openqa.selenium.WebElement;

public class ShopPage extends BasePage {


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
    private WebElement shopProductsHeader;

    public ShopPage(AppiumDriver driver) {
        super(driver);
    }

    public String getShopProductsHeaderText() {
        return getTextWithFluentWait(shopProductsHeader);
    }
}
