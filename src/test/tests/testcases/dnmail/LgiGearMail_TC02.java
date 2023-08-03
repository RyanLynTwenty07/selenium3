package testcases.dnmail;

import base.TestBase;
import org.example.data.email.EmailData;
import org.example.data.PageName;
import org.example.page.dnmail.LoginPage;
import org.example.page.dnmail.MailBoxPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LgiGearMail_TC02 extends TestBase {

    @BeforeMethod
        public void setUp(){
        emailData.setTo("linh.nguyen@logigear.com");
        emailData.setSubject("Email is sent successfully!");
        emailData.setContent("Test email is send successfully!");
        emailData.setImgPath("E:\\selenide_java\\src\\test\\resources\\data\\attachment\\ArivalReasonCodes.PNG");
        user.setUsername(System.getenv("username"));
        user.setPassword(System.getenv("password"));
    }

    @Test(description = "Compose and send email successfully")
    public void lgiGearMail_TC02() {
        loginPage.openPage(PageName.DN_LGG_MAIL);
        loginPage.login(user);
        mailBoxPage.composeEmail(emailData);
        mailBoxPage.clickSend();
    }

    LoginPage loginPage = new LoginPage();
    MailBoxPage mailBoxPage = new MailBoxPage();
    EmailData emailData = new EmailData();
    LoginPage.User user = new LoginPage.User();
}
