package org.com.driver.statics;

import io.qameta.allure.Step;
import org.com.driver.DriverManager;

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
        DriverManager.driver().getDriver().switchTo().window(window - 1);
    }

    @Step("Close window number {window}")
    public static void closeWindow(int window) {
        DriverManager.driver().getDriver().switchTo().window(window - 1).close();
        switchToWindow(getWindowHandles().size());
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

    public static void scrollToTop() {
        DriverManager.driver().executeJavaScript("window.scrollBy(0,-1500)", "");
    }

    public static void scrollToBot() {
        DriverManager.driver().executeJavaScript("window.scrollBy(0,1500)", "");
    }

}
