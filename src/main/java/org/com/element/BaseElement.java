package org.com.element;

import com.codeborne.selenide.*;
import org.com.driver.Configuration;
import org.com.driver.DriverManager;
import org.com.driver.statics.DriverUtils;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BaseElement {

    protected String locator;
    protected By by;
    protected String locatorType;
    protected String locatorValue;
    protected String dynamicLocator;
    protected SelenideElement element;
    protected SelenideDriver selenideDriver;

    public BaseElement(String locator) {
        this.locator = locator;
        this.dynamicLocator = locator;
        this.by = by();
    }

    protected By by() {
        this.locatorValue = this.locator.replaceAll("[\\w\\s]*=(.*)", "$1").trim();
        String type = this.locator.replaceAll("([\\w\\s]*)=.*", "$1").trim();
        this.locatorType = type;
        switch (type) {
            case "css":
                return By.cssSelector(this.locatorValue);
            case "id":
                return By.id(this.locatorValue);
            case "link":
                return By.linkText(this.locatorValue);
            case "xpath":
                return By.xpath(this.locatorValue);
            case "text":
                return By.xpath(String.format("//*[contains(text(), '%s')]", this.locatorValue));
            case "name":
                return By.name(this.locatorValue);
            case "className":
                return By.className(this.locatorValue);
            default:
                this.locatorValue = locator;
                return By.xpath(this.locatorValue);
        }
    }

    protected SelenideDriver driver() {
        return DriverManager.driver().getDriver();
    }

    protected WebDriver webDriver() {
        return DriverManager.driver().getDriver().getWebDriver();
    }

    protected Duration timeout() {
        return Duration.ofMillis(DriverManager.driver().getConfig().getTimeOut());
    }

    private Configuration getConfig() {
        return DriverManager.driver().getConfig();
    }

    public SelenideElement findElement() {
        this.element = driver().$(by());
        return this.element;
    }

    public String getLocator() {
        return locator;
    }

    public void set(Object... args) {
        this.element = null;
        this.locator = String.format(this.dynamicLocator, args);
        this.by = by();
    }

    public List<SelenideElement> findElements() {
        return driver().$$(webDriver().findElements(by()));
    }

    public void click() {
        if (getConfig().isClickViaJs()) {
            click(findElement(), 0, 0);
        } else {
            findElement().click();
        }
    }

    public void click(int offsetX, int offsetY) {
        click(findElement(), offsetX, offsetY);
    }

    public void pressEnter() {
        findElement().sendKeys(Keys.ENTER);
    }

    public void click(SelenideElement element, int offsetX, int offsetY) {
        if (getConfig().isClickViaJs()) {
            clickViaJS(offsetX, offsetY);
        } else {
            DriverManager.driver().actions().moveToElement(element, offsetX, offsetY).click().build().perform();
        }
    }

    public void pressEscape() {
        findElement().sendKeys(Keys.ESCAPE);
    }

    public void pressTab() {
        findElement().sendKeys(Keys.TAB);
    }

    public void scrollTo() {
        Point location = findElement().getLocation();
        driver().executeJavaScript("window.scrollTo(" + location.getX() + ", " + location.getY() + ')');
    }

    public void scrollToTop() {
        driver().executeJavaScript("window.scrollBy(0,-1500)", "");
    }

    public boolean isChecked() {
        return findElement().is(Condition.checked);
    }

    public void clickViaJS(int offsetX, int offsetY) {
        waitForClickable();
        driver().executeJavaScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "arguments[0].dispatchEvent(new MouseEvent('click', {" +
                        " 'view': window," +
                        " 'bubbles': true," +
                        " 'cancelable': true," +
                        " 'clientX': rect.left + rect.width/2 + arguments[1]," +
                        " 'clientY': rect.top + rect.height/2 + arguments[2]" +
                        "}))", findElement(), offsetX, offsetY);
    }

    public void clickViaJS() {
        clickViaJS(0, 0);
    }

    public boolean isSelected() {
        return findElement().isSelected();
    }

    public boolean isDisplayed() {
        return findElement().isDisplayed();
    }

    public void check() {
        if (!isChecked()) {
            click();
        }
    }

    public void uncheck() {
        if (isChecked()) {
            click();
        }
    }

    public boolean isEnabled() {
        return findElement().isEnabled();
    }

    public String getValue() {
        return getAttribute("value");
    }

    public String getAttribute(String name) {
        return findElement().getAttribute(name);
    }

    public String getText() {
        return findElement().getText();
    }

    public void scrollToView() {
        driver().executeJavaScript("arguments[0].scrollIntoView(true);", findElement());
    }

    public void enter(CharSequence... value) {
        for (CharSequence v : value) {
            findElement().val(SetValueOptions.withText(v).sensitive());
        }
    }

    /**
     * Split text by characters, then entering char by char.
     *
     * @param fullString      - full text
     * @param delayMiliSecond -
     */
    public void enterCharByChar(String fullString, int delayMiliSecond) {
        String currStr;
        for (int i = 0; i < fullString.length(); i++) {
            currStr = Character.toString(fullString.charAt(i));
            findElement().sendKeys(currStr);
            DriverUtils.delay(delayMiliSecond);
        }
    }

    /**
     * Enter text into the the element.
     *
     * @param value
     * @param clear: Clear text before entering or not
     */
    public void enter(String value, boolean clear) {
        if (clear) {
            this.clear();
        }
        findElement().sendKeys(value);
    }

    public void switchToFrame() {
        driver().switchTo().frame(findElement());
    }

    public void clear() {
        findElement().clear();
    }

    public void select(String text) {
        findElement().selectOption(text);
    }

    public void select(int index) {
        findElement().selectOption(index);
    }

    public String getSelected() {
        return findElement().getSelectedOptionText();
    }

    public List<String> getSelectOptions() {
        return findElement().getSelectedOptions().texts();
    }

    public SelenideElement waitForClickable() {
        return waitForClickable(timeout());
    }

    public SelenideElement waitForClickable(Duration duration) {
        return findElement().should(Condition.and("Can be clickable", Condition.visible, Condition.enabled), duration);
    }

    public SelenideElement waitForVisible(Duration duration) {
        return findElement().should(Condition.visible, duration);
    }

    public SelenideElement waitForVisible() {
        return waitForVisible(timeout());
    }

    public SelenideElement waitForInvisible(Duration duration) {
        return findElement().should(Condition.disappear, duration);
    }

    public SelenideElement waitForInvisible() {
        return waitForInvisible(timeout());
    }

    public SelenideElement waitForExist(Duration duration) {
        return findElement().should(Condition.exist, duration);
    }

    public SelenideElement waitForExist() {
        return waitForExist(timeout());
    }

    public SelenideElement waitForDisappear(Duration duration) {
        return findElement().should(Condition.disappear, duration);
    }
    public SelenideElement waitForDisappear() {
        return waitForDisappear(timeout());
    }
}
