package org.com.page.dnmail;

import io.qameta.allure.Step;
import lombok.Data;
import org.com.element.control.BaseElement;

public class LoginPage extends HomePage {

    @Step("Login with user name: {userData}")
    public void login(User userData) {
        userNameTextBox.enter(userData.username);
        passwordTextBox.enter(userData.password);
        signInButton.click();
    }

    @Data
    public static class User {

        private String username;
        private String password;

        @Override
        public String toString() {
            return "Username: " + this.username + " - " + "Password: " + this.password.replaceAll("\\w", "*");
        }
    }

    BaseElement userNameTextBox = new BaseElement("name=username");
    BaseElement passwordTextBox = new BaseElement("name=password");
    BaseElement signInButton = new BaseElement("//input[@value='Sign in']");
}
