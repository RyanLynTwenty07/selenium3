package testcases.dnmail;

import base.TestBase;
import org.apache.commons.lang3.RandomUtils;
import org.com.driver.statics.DriverUtils;
import org.com.utils.Assert;
import org.example.data.PageName;
import org.example.data.email.EmailData;
import org.example.page.dnmail.LoginPage;
import org.example.page.dnmail.MailBoxPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogiGearMail_TC01_ComposeAndSaveDraftMailSuccessfully extends TestBase {

    @BeforeMethod
    public void setUp() {
        toContact = "Linh Nguyen";
        emailData.setTo("linh.nguyen@logigear.com");
        emailData.setSubject(String.format("Email the [%d]!", RandomUtils.nextInt(1, 1000)));
        emailData.setContent("Test email is saved successfully!");
        emailData.setImgPath("src/test/resources/data/dnmail/ArivalReasonCodes.PNG");
        user.setUsername(System.getenv("user"));
        user.setPassword(System.getenv("key"));
    }

    @Test(description = "Compose and save draft email successfully")
    public void logiGearMail_TC01_ComposeAndSaveDraftMailSuccessfully() {
        loginPage.openPage(PageName.DN_LGG_MAIL);
        loginPage.login(user);
        mailBoxPage.composeEmail(emailData);
        mailBoxPage.clickSave();
        DriverUtils.closeWindow(DriverUtils.getWindowHandles().size());
        mailBoxPage.selectMailFolder("Drafts");
        //enhance checkpoints
        Assert.assertTrue(mailBoxPage.checkMailDisplayInDraftFolder(emailData.getSubject()));
        Assert.assertTrue(mailBoxPage.isAttachmentDisplayedInMail(emailData.getSubject()));
        Assert.assertEquals(mailBoxPage.getToEmail(emailData.getSubject()), toContact);
        Assert.assertAll("Completed Test!");
    }

    LoginPage loginPage = new LoginPage();
    MailBoxPage mailBoxPage = new MailBoxPage();
    EmailData emailData = new EmailData();
    LoginPage.User user = new LoginPage.User();
    String toContact;
}
