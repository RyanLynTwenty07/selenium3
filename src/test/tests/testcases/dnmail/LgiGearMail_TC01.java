package testcases.dnmail;

import base.TestBase;
import org.apache.commons.lang3.RandomUtils;
import org.example.data.PageName;
import org.example.data.email.EmailData;
import org.com.driver.statics.DriverUtils;

import org.example.page.dnmail.LoginPage;
import org.example.page.dnmail.MailBoxPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LgiGearMail_TC01 extends TestBase {

    @BeforeMethod
    public void setUp() {
        emailData.setTo("linh.nguyen@logigear.com");
        emailData.setSubject(String.format("Email the [%d] is sent successfully!", RandomUtils.nextInt(1, 1000)));
        emailData.setContent("Test email is send successfully!");
        emailData.setImgPath("E:\\selenide_java\\src\\test\\resources\\data\\attachment\\ArivalReasonCodes.PNG");
        user.setUsername(System.getenv("username"));
        user.setPassword(System.getenv("password"));
    }

    @Test(description = "Compose and save draft email successfully")
    public void userCanLoginByUsername() {
        loginPage.openPage(PageName.DN_LGG_MAIL);
        loginPage.login(user);
        mailBoxPage.composeEmail(emailData);
        mailBoxPage.clickSave();
        DriverUtils.closeWindow(DriverUtils.getWindowHandles().size());
        mailBoxPage.selectMailFolder("Inbox");
        //enhance checkpoint
        mailBoxPage.checkMailDisplayInDraftFolder(emailData.getSubject());
    }

    LoginPage loginPage = new LoginPage();
    MailBoxPage mailBoxPage = new MailBoxPage();
    EmailData emailData = new EmailData();
    LoginPage.User user = new LoginPage.User();
}
