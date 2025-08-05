package org.fresh;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.fresh.utilities.PropertiesLoader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public int ServerTimeout = 60;

    @BeforeClass
    public void ConfigureAppium() throws MalformedURLException {
        final PropertiesLoader propertiesLoader = new PropertiesLoader();
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("//opt//homebrew//lib//node_modules//appium"))
                .withIPAddress(propertiesLoader.getHost()).usingPort(propertiesLoader.getPort()).build();
        service.start();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(propertiesLoader.getDeviceName());
        options.setPlatformName(propertiesLoader.getPlatform());
        options.setPlatformVersion(propertiesLoader.getPlatformVersion());
        options.setAutomationName(propertiesLoader.getAndroidAutomationName());
        options.setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(ServerTimeout));
        options.setUiautomator2ServerInstallTimeout(Duration.ofSeconds(ServerTimeout));
        options.setAppWaitActivity(propertiesLoader.getAndroidAppActivity());
        options.setApp(System.getProperty("user.dir") + propertiesLoader.getArtifactPath());

        driver = new AndroidDriver(new URL("http://" + propertiesLoader.getHost() + ":" + propertiesLoader.getPort() + "/"), options);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
}
