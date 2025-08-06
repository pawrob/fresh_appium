package org.fresh.pages.shop;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.fresh.pages.BasePage;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ShopPage extends BasePage {


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
    private WebElement shopProductsHeader;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc='test-ADD TO CART'])[1]")
    private WebElement addFirstProductToCartButton;

    @AndroidFindBy(accessibility = "test-Modal Selector Button")
    private WebElement sortingButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Price (low to high)']")
    private WebElement sortByPriceLowToHighButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Price (high to low)']")
    private WebElement sortByPriceHighToLowButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Name (A to Z)']")
    private WebElement sortByNameAToZButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Name (Z to A)']")
    private WebElement sortByNameZToAButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='test-Price']")
    private List<WebElement> productPrices;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='test-Item title']")
    private List<WebElement> productNames;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc='test-REMOVE'])[1]")
    private WebElement removeFirstItemFromCart;


    @AndroidFindBy(accessibility = "test-Cart")
    private WebElement cartButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup/android.widget.TextView")
    private WebElement numberOfProductsInCart;

    public ShopPage(AppiumDriver driver) {
        super(driver);
    }


    public void addFirstItemToCart() {
        waitForElementAndClick(addFirstProductToCartButton);
    }

    public void removeBackpackFromCart() {
        waitForElementAndClick(removeFirstItemFromCart);
    }

    public String getFirstProductName() {
        return waitForElementAndGetText(productNames.get(0));
    }

    public int getNumberOfProductsInCart() {
        if (checkIfElementIsNotVisible(numberOfProductsInCart)) {
            return 0;
        }
        String text = waitForElementAndGetText(numberOfProductsInCart);
        if (text != null && !text.isEmpty()) {
            return Integer.parseInt(text);
        }
        return 0;
    }

    public CartPage openCart() {
        waitForElementAndClick(cartButton);
        CartPage cartPage = new CartPage(driver);
        cartPage.waitForCartPageToOpen();
        return cartPage;
    }

    public void waitForShopPageToOpen() {
        waitForElementToBeVisible(shopProductsHeader);
    }

    public void clickSortingButtonAndOpenModal() {
        waitForElementAndClick(sortingButton);
    }

    public void clickSortByPriceLowToHigh() {
        waitForElementAndClick(sortByPriceLowToHighButton);
    }

    public void clickSortByPriceHighToLow() {
        waitForElementAndClick(sortByPriceHighToLowButton);
    }

    public List<Double> getPricesOfVisibleItems() {
        return productPrices.stream()
                .map(WebElement::getText)
                .map(text -> text.replace("$", ""))
                .map(Double::parseDouble).collect(Collectors.toList());
    }

    public void clickSortByNameAToZ() {
        waitForElementAndClick(sortByNameAToZButton);
    }

    public void clickSortByNameZToA() {
        waitForElementAndClick(sortByNameZToAButton);
    }

    public List<String> getNamesOfVisibleItems() {
        return productNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
