package org.fresh.pages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {


    protected AppiumDriver driver;

    public BasePage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

        this.driver = driver;
    }


    public void longPress(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 200));

    }

}
