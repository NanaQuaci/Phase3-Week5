package com.projects.framework.driver;

import com.projects.framework.config.Config;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.time.Duration;

public class CapabilitiesFactory {
    public static UiAutomator2Options androidOptions() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(Config.get("PLATFORM_NAME"))
                .setPlatformVersion(Config.get("PLATFORM_VERSION"))
                .setDeviceName(Config.get("DEVICE_NAME"))
                .setAutomationName(Config.get("AUTOMATION_NAME"))
                .setApp(Config.get("APP_PATH"))
                .setAppPackage(Config.get("APP_PACKAGE"))
                .setAppActivity(Config.get("APP_ACTIVITY"))
                .setNewCommandTimeout(Duration.ofSeconds(Long.parseLong(Config.get("NEW_COMMAND_TIMEOUT"))));

        // Optional extra timeouts
        if (Config.contains("UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT")) {
            options.setUiautomator2ServerLaunchTimeout(Duration.ofMillis(
                    Long.parseLong(Config.get("UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT"))));
        }
        if (Config.contains("UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT")) {
            options.setUiautomator2ServerInstallTimeout(Duration.ofMillis(
                    Long.parseLong(Config.get("UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT"))));
        }
        if (Config.contains("ADB_EXEC_TIMEOUT")) {
            options.setAdbExecTimeout(Duration.ofMillis(
                    Long.parseLong(Config.get("ADB_EXEC_TIMEOUT"))));
        }

        return options;
    }
}
