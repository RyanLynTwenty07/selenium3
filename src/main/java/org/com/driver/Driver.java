package org.com.driver;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideDriver;
import lombok.SneakyThrows;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Driver {

    private Configuration config;
    private SelenideDriver driver;
    private boolean isAlive;

    public Driver(Configuration config) {
        this.config = config;
        this.driver = new SelenideDriver(config.toJson());
    }

    @SneakyThrows
    public void open(String url) {
        this.driver.open(url);
        this.driver.getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofMillis(config.getPageLoadTimeout()));
        if (config.isMaximize()) {
            Thread.sleep(2_000);
            this.driver.getWebDriver().manage().window().maximize();
        }
    }

    public void close() {
        this.driver.close();
    }

    public Configuration getConfig() {
        return this.config;
    }

    public SelenideDriver getDriver() {
        return this.driver;
    }

    public Actions actions() {
        return new Actions(getDriver().getWebDriver());
    }

    public <T> T executeJavaScript(String jsCode, Object... arguments) {
        return (T) ((JavascriptExecutor) getDriver().getWebDriver()).executeScript(jsCode, arguments);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
