package org.fresh;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class TestLogin extends BaseTest {

    @Test(groups = {"android"}, dataProvider="loginData")
    public void testUserCanLogInToApp(String username, String password) {

        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys(username);
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys(password);
        driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();


    }
}
