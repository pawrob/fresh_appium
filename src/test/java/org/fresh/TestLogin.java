package org.fresh;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class TestLogin extends BaseTest {

    @Test
    public void test() {

        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("");
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys("");
        driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();


    }
}
