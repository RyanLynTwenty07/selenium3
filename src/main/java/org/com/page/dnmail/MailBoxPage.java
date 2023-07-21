package org.com.page.dnmail;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.com.data.email.EmailData;
import org.com.driver.statics.DriverUtils;
import org.com.element.control.BaseElement;
import org.openqa.selenium.Keys;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MailBoxPage extends HomePage {

    @Step("Compose email to send {data}")
    public void composeEmail(EmailData data) {
        newButton.click();
        DriverUtils.switchToWindow(DriverUtils.getWindowHandles().size());
        DriverUtils.delay(2_000);
        toTextBox.enter(data.getTo());
        if (data.getCc() != null)
            ccTextBox.enter(data.getCc());

        subjectTextBox.enter(data.getSubject());
        iframe.switchToFrame();
        contentTextArea.enter(data.getContent());
        contentTextArea.enter(Keys.ENTER);

        if (data.getImgPath() != null) {
            DriverUtils.switchToMain();
            insertImgButton.click();
            iframeDialog.switchToFrame();
            subIframeDialog.switchToFrame();
            chooseFileButton.enter(data.getImgPath());
            insertButton.click();
        }
        DriverUtils.switchToMain();
    }

    public void inputFile(String path) {
        inputFile.enter(path);
    }

    public void clickSave(){
        saveButton.click();
    }

    public void clickSend(){
        sendButton.click();
    }

    @SneakyThrows
    public void chooseFile() {
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_C);        // C
        r.keyRelease(KeyEvent.VK_C);
        r.keyPress(KeyEvent.VK_COLON);    // : (colon)
        r.keyRelease(KeyEvent.VK_COLON);
        r.keyPress(KeyEvent.VK_SLASH);    // / (slash)
        r.keyRelease(KeyEvent.VK_SLASH);
        r.keyPress(KeyEvent.VK_ENTER);    // confirm by pressing Enter in the end
        r.keyRelease(KeyEvent.VK_ENTER);
    }

    BaseElement newButton = new BaseElement("id=newmsgc");
    BaseElement toTextBox = new BaseElement("id=divTo");
    BaseElement ccTextBox = new BaseElement("id=divCc");
    BaseElement subjectTextBox = new BaseElement("id=txtSubj");
    BaseElement iframe = new BaseElement("id=ifBdy");
    BaseElement iframeDialog = new BaseElement("id=iFrameModalDlg");
    BaseElement subIframeDialog = new BaseElement("//iframe[@src='?ae=Dialog&t=AttachFileDialog&a=InsertImage']");

    BaseElement contentTextArea = new BaseElement("//body[@fpstyle=1]");
    BaseElement sendButton = new BaseElement("id=send");
    BaseElement saveButton = new BaseElement("id=save");
    BaseElement attachmentButton = new BaseElement("class=csimg csimgbg sprites-attach-png tbLh tbBefore tbAfter");
    BaseElement insertImgButton = new BaseElement("//img[@class='csimg csimgbg sprites-insertimage-png tbLh tbBefore tbAfter']");
    BaseElement chooseFileButton = new BaseElement("//div[not(contains(@style,'display: none'))]/input[@class='fileField']");
    BaseElement insertButton = new BaseElement("id=btnAttch");
    BaseElement inputFile = new BaseElement("name=uploadfile");




}
