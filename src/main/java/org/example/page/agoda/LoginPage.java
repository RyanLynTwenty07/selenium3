package org.example.page.agoda;

import org.com.driver.statics.DriverUtils;
import org.com.element.BaseElement;
import org.example.data.agoda.UserData;

public class LoginPage {

    public boolean isLoginPageDisplayed() {
        iframe.switchToFrame();
        DriverUtils.waitForPageLoad();
        return title.isDisplayed();
    }

    public void login(UserData data) {
        userName.enter(data.getEmail(), true);
        password.enter(data.getKey(), true);
        signInButton.click();
        DriverUtils.switchToMain();
    }


    BaseElement title = new BaseElement("//h3[.='Sign in']");
    BaseElement userName = new BaseElement("id=email");
    BaseElement password = new BaseElement("id=password");
    BaseElement signInButton = new BaseElement("//span[.='Sign in']");
    BaseElement iframe = new BaseElement("//iframe[@title='Universal login']\n");

}
