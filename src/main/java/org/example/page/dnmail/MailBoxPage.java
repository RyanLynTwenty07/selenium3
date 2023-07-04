package org.example.page.dnmail;

import io.qameta.allure.Step;
import org.example.data.EmailData;
import org.example.driver.statics.DriverUtils;
import org.example.element.control.BaseElement;
import org.example.page.general.IHomePage;

public class MailBoxPage implements IHomePage {

    @Step("Compose email to send {data}")
    public void composeEmail(EmailData data) {
        newButton.click();
        DriverUtils.switchToWindow(DriverUtils.getWindowHandles().size());

        toTextBox.enter(data.getTo());
        if (data.getCc() != null)
            ccTextBox.enter(data.getCc());

        subjectTextBox.enter(data.getSubject());
        iframe.switchToFrame();
        contentTextArea.enter(data.getContent());
        DriverUtils.switchToMain();
        sendButton.click();

    }

    BaseElement newButton = new BaseElement("id=newmsgc");
    BaseElement toTextBox = new BaseElement("id=divTo");
    BaseElement ccTextBox = new BaseElement("id=divCc");
    BaseElement subjectTextBox = new BaseElement("id=txtSubj");
    BaseElement iframe = new BaseElement("id=ifBdy");
    BaseElement contentTextArea = new BaseElement("//body[@fpstyle=1]");
    BaseElement sendButton = new BaseElement("id=send");

}
