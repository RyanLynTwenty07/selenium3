package org.example.page.dnmail;

import io.qameta.allure.Step;
import org.example.element.control.BaseElement;

public class LoginPage extends HomePage {

    @Step("Login with user name: {username}")
    public void login(String username, String password) {
        userNameTextBox.enter(username);
        passwordTextBox.enter(password);
        signInButton.click();
    }

    BaseElement userNameTextBox = new BaseElement("name=username");
    BaseElement passwordTextBox = new BaseElement("name=password");
    BaseElement signInButton = new BaseElement("//input[@value='Sign in']");
}
