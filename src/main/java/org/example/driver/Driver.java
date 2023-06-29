package org.example.driver;

import com.codeborne.selenide.SelenideDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

public class Driver {

    private Configuration config;
    private SelenideDriver driver;

    public Driver(Configuration config) {
        this.config = config;
        this.driver = new SelenideDriver(config.toJson());
    }

    public void open(String url) {
        this.driver.open(url);
        if (config.isMaximize()) {
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
}
