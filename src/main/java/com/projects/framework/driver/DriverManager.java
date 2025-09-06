package com.projects.framework.driver;

import com.projects.framework.config.Config;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URI;
import java.time.Duration;


public class DriverManager {
    private static final ThreadLocal<AndroidDriver> TL = new ThreadLocal<>();


    public static AndroidDriver get(){ return TL.get(); }


    public static void start(){
        UiAutomator2Options opts = CapabilitiesFactory.androidOptions();
        AndroidDriver driver;
        try {
            driver = new AndroidDriver(URI.create(Config.get("APPIUM_SERVER")).toURL(), opts);
        } catch (Exception e) { throw new RuntimeException("Failed to connect to Appium server", e); }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        TL.set(driver);
    }


    public static void stop(){
        AndroidDriver d = TL.get();
        if (d != null) { d.quit(); TL.remove(); }
    }
}
