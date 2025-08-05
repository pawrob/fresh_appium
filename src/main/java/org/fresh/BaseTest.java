package org.fresh;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fresh.utilities.PropertiesLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners(org.fresh.utilities.TestListener.class)
public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public int ServerTimeout = 60;
    final PropertiesLoader propertiesLoader = new PropertiesLoader();
    public String platformName = propertiesLoader.getPlatform();
    public String deviceName = propertiesLoader.getDeviceName();

    private static final Logger log = LogManager.getLogger(Logger.class.getName());

    @BeforeClass
    public void ConfigureAppium() throws MalformedURLException {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("//opt//homebrew//lib//node_modules//appium"))
                .withIPAddress(propertiesLoader.getHost()).usingPort(propertiesLoader.getPort()).build();
        service.clearOutPutStreams();
        service.start();

        log.info(String.format("Starting test for platform: %s", platformName));
        log.info(String.format("Running on device: %s", deviceName));
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setPlatformName(platformName);
        options.setPlatformVersion(propertiesLoader.getPlatformVersion());
        options.setAutomationName(propertiesLoader.getAndroidAutomationName());
        options.setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(ServerTimeout));
        options.setUiautomator2ServerInstallTimeout(Duration.ofSeconds(ServerTimeout));
        options.setAppWaitActivity(propertiesLoader.getAndroidAppActivity());
        options.setApp(System.getProperty("user.dir") + propertiesLoader.getArtifactPath());
        options.setFullReset(false);
        options.setNoReset(false);
        options.setNewCommandTimeout(Duration.ofSeconds(ServerTimeout));
        options.setSkipUnlock(true);

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

    @DataProvider(name = "loginData")
    public Object[][] getDataFromJson() throws Exception {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser
                .parse(new FileReader(System.getProperty("user.dir") + "//src//test//java//testData//loginData.json"));

        Object[][] data = new Object[jsonArray.size()][2];
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject user = (JSONObject) jsonArray.get(i);
            data[i][0] = user.get("username");
            data[i][1] = user.get("password");
        }
        return data;
    }
}
