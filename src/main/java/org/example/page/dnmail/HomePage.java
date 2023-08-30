package org.example.page.dnmail;

import io.qameta.allure.Step;
import org.example.page.general.IHomePage;
import org.com.element.BaseElement;

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
        title.waitForVisible();
        return title.isDisplayed();
    }

    public boolean isAttachmentDisplayedInMail(String subject) {
        attachment.set(subject);
        attachment.waitForVisible();
        return attachment.isDisplayed();
    }

    @Step("Get To contact on email")
    public String getToEmail(String subject) {
        to.set(subject);
        to.waitForVisible();
        return to.getText();
    }

    protected BaseElement mailTree = new BaseElement("//div[@id='mailtree']//span[.='%s']");
    protected BaseElement title = new BaseElement("//div[@id='vr'][div[.='%s' and @id='divSubject']]");
    protected BaseElement attachment = new BaseElement("//div[@id='divItmPrtsScr'][div[.='%s']]//div/img[contains(@src,'attachment.ashx')]");
    protected BaseElement to = new BaseElement("//div[@id='divItmPrtsScr'][div[.='%s']]//div[@id='divTo']/span[@id='spnR']");
}
