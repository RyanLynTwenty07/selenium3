package org.example.driver.statics;

import org.example.driver.Driver;
import org.example.driver.DriverManager;

import java.util.ArrayList;

public class DriverUtils {

    public static void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void switchToMain() {
        DriverManager.driver().getDriver().switchTo().defaultContent();
    }

    public static void switchToWindow(String window) {
        DriverManager.driver().getDriver().switchTo().window(window);
    }

    public static void switchToWindow(int window) {
        DriverManager.driver().getDriver().switchTo().window(window -1);
    }

    public static String getWindowHandle() {
        return DriverManager.driver().getDriver().getWebDriver().getWindowHandle();
    }

    public static ArrayList<String> getWindowHandles() {
        return new ArrayList<>(DriverManager.driver().getDriver().getWebDriver().getWindowHandles());
    }

    public static void open(String url) {
        DriverManager.driver().open(url);
    }
}
