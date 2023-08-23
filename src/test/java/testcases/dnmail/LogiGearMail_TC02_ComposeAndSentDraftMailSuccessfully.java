package testcases.dnmail;

import base.TestBase;
import org.com.driver.statics.DriverUtils;
import org.com.report.Logger;
import org.com.utils.Assert;
import org.example.data.email.EmailData;
import org.example.data.PageName;
import org.example.page.dnmail.LoginPage;
import org.example.page.dnmail.MailBoxPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogiGearMail_TC02_ComposeAndSentDraftMailSuccessfully extends TestBase {

    @BeforeMethod
        public void setUp(){
        emailData.setTo("linh.nguyen@logigear.com");
        emailData.setSubject("Email is sent successfully!");
        emailData.setContent("Test email is send successfully!");
        emailData.setImgPath("src/test/resources/data/dnmail/ArivalReasonCodes.PNG");
        user.setUsername(System.getenv("user"));
        user.setPassword(System.getenv("key"));
    }

    @Test(description = "Compose and send email successfully")
    public void lgiGearMail_TC02() {
        Logger.info("Step 1: Open DN Mail and Login");
        loginPage.openPage(PageName.DN_LGG_MAIL);
        loginPage.login(user);

        Logger.info("Step 2: Compose Email");
        mailBoxPage.composeEmail(emailData);

        Logger.info("Step 3: Click Save and close Pop-up");
        mailBoxPage.clickSend();
        DriverUtils.closeWindow(DriverUtils.getWindowHandles().size());

        Logger.info("Step 4: Select Folder Inbox");
        mailBoxPage.selectMailFolder("Inbox");

        Assert.assertTrue(mailBoxPage.checkMailDisplayInDraftFolder(emailData.getSubject()),"VP: Verify email display in Inbox box");
        Assert.assertTrue(mailBoxPage.isAttachmentDisplayedInMail(emailData.getSubject()),"VP: Verify Attachment is display in email");
        Assert.assertEquals(mailBoxPage.getToEmail(emailData.getSubject()), toContact,"VP: Verify receive address correct");
        Assert.assertAll("Completed Test!");
    }

    LoginPage loginPage = new LoginPage();
    MailBoxPage mailBoxPage = new MailBoxPage();
    EmailData emailData = new EmailData();
    LoginPage.User user = new LoginPage.User();
    String toContact;
}
