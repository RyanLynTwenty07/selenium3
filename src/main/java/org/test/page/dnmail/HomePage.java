package org.test.page.dnmail;

import org.test.page.general.IHomePage;
import org.example.element.control.BaseElement;

public class HomePage implements IHomePage {

    @Override
    public void clickButton(String button) {

    }

    public void selectMailFolder(String... path) {
        mailTree.set(path);
        mailTree.click();
    }


    public boolean checkMailDisplayInDraftFolder(String subject) {
        title.set(subject);
        return title.isDisplayed();
    }

    protected BaseElement mailTree = new BaseElement("//div[@id='mailtree']//span[.='%s']");
    protected BaseElement title = new BaseElement("//div[@id='vr'][div[.='%s' and @id='divSubject']]");
}
