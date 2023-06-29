package org.example.page.dnmail;

import org.example.element.control.BaseElement;
import org.example.page.general.IHomePage;

public class LoginPage implements IHomePage {

    public void login(String username, String password) {
        userNameTextBox.findElement();
        userNameTextBox.enter(username);
        passwordTextBox.findElement();
        passwordTextBox.enter(password);
        signInButton.findElement();
        signInButton.click();
//        $(By.name("password")).setValue(System.getenv("password"));
//        $(By.xpath("//input[@value='Sign in']")).click();
    }

    BaseElement userNameTextBox = new BaseElement("name=username");
    BaseElement passwordTextBox = new BaseElement("name=password");
    BaseElement signInButton = new BaseElement("//input[@value='Sign in']");
}
