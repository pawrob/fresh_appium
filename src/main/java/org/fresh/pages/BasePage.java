package org.fresh.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class BasePage {


    protected AppiumDriver driver;

    private final Wait<AppiumDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofSeconds(3))
            .ignoring(NoSuchElementException.class)
            .ignoring(ElementClickInterceptedException.class)
            .ignoring(StaleElementReferenceException.class);

    public BasePage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

        this.driver = driver;
    }


    public void waitForElementAndClick(WebElement element) {

        wait.until(driver -> {
            if (element.isDisplayed() && element.isEnabled()) {
                element.click();
                return true;
            }
            return false;
        });
    }

    public void waitForElementAndInputText(WebElement element, String text) {
        wait.until(driver -> {
            if (element.isDisplayed() && element.isEnabled()) {
                element.clear();
                element.sendKeys(text);
                return true;
            }
            return false;
        });
    }

    public String waitForElementAndGetText(WebElement element) {

        return wait.until(driver -> {
            if (element.isDisplayed() && element.isEnabled()) {
                String text = element.getText();
                return (text != null && !text.trim().isEmpty()) ? text : null;
            }
            return null;
        });
    }

    public Boolean waitForElementToBeVisible(WebElement element) {
        return wait.until(driver -> {
            if (element.isDisplayed() && element.isEnabled()) {
                return true;
            }
            return false;
        });
    }


    public Boolean checkIfElementIsNotVisible(WebElement element) {
        try {
            return !element.isDisplayed();
        } catch (NoSuchElementException e) {
            return true; // Element is not present, hence not visible
        }
    }
}
