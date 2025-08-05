package org.fresh;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class TestLogin extends BaseTest {

    @Test
    public void test() {

        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("standard_user");
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys("secret_sauce");
        driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();



    }
}
