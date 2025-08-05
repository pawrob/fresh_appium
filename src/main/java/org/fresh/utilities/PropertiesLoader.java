package org.fresh.utilities;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesLoader {
    private Properties properties;
    private final String propertiesPath;

    public PropertiesLoader() {

        propertiesPath = System.getProperty("configFile");
        loadFromFile();
    }

    private void loadFromFile() {
        properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(propertiesPath)));
        } catch (Exception e) {
            System.out.printf("Failed to load '%s' file.%n", propertiesPath);
        }
    }

    public String getPlatform() {
        return properties.getProperty(EnvironmentVariables.PLATFORM.name());
    }

    public String getPlatformVersion() {
        return properties.getProperty(EnvironmentVariables.PLATFORM_VERSION.name());
    }

    public String getDeviceName() {
        return properties.getProperty(EnvironmentVariables.DEVICE_NAME.name());
    }

    public String getAndroidAutomationName() {
        return properties.getProperty(EnvironmentVariables.ANDROID_AUTOMATION_NAME.name());
    }

    public String getArtifactPath() {
        return properties.getProperty(EnvironmentVariables.ARTIFACT_PATH.name());
    }

    public String getHost() {
        return properties.getProperty(EnvironmentVariables.HOST.name());
    }

    public int getPort() {
        return Integer.parseInt(properties.getProperty(EnvironmentVariables.PORT.name()));
    }

    public String getAndroidAppPackage() {
        return properties.getProperty(EnvironmentVariables.ANDROID_APP_PACKAGE.name());
    }

    public String getAndroidAppActivity() {
        return properties.getProperty(EnvironmentVariables.ANDROID_APP_ACTIVITY.name());
    }
}
