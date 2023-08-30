package org.example.page.dnmail;

import io.qameta.allure.Step;
import lombok.Data;
import org.com.element.BaseElement;
import org.com.report.Logger;

public class LoginPage extends HomePage {

    public void login(User userData) {
        Logger.info("Login with user name/password");
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
