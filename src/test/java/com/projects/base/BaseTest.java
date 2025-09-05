package com.projects.base;

import com.projects.framework.driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverManager.start();
        driver = DriverManager.get();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.stop();
    }
}
