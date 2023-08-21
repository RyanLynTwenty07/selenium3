package testcases.vietjetair;

import org.apache.commons.lang3.RandomUtils;
import org.example.data.email.EmailData;
import org.example.data.PageName;
import org.com.driver.statics.DriverUtils;
import org.example.page.dnmail.LoginPage;
import org.example.page.dnmail.MailBoxPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.TestBase;

public class VietjetAir_TC01 extends TestBase {

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
    public void vietjetAir_TC01() {
        loginPage.openPage(PageName.VIET_JET_AIR);
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
