package org.com.driver.statics;

import io.qameta.allure.Step;
import org.com.common.Constants;
import org.com.driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.function.Function;

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

    public static boolean isAlive() {
        return DriverManager.driver().isAlive();
    }

    public static <T> T takeScreenShot(OutputType<T> outputType) {
        T screenshot = DriverManager.driver().getDriver().screenshot(outputType);
        return screenshot;
    }
    public static void waitForPageLoad() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.driver().getDriver().getWebDriver(), Constants.DEFAULT_TIMEOUT);
            wait.until(new Function<WebDriver, Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    Boolean domIsComplete = (Boolean) (executor
                            .executeScript("return document.readyState == 'complete';"));
                    return domIsComplete;
                }
            });
            delay(2000);
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
