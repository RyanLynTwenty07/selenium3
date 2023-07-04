package testcases;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.data.EmailData;
import org.example.page.dnmail.LoginPage;
import org.example.data.PageName;
import org.example.page.dnmail.MailBoxPage;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeTest extends TestBase {

    @BeforeMethod
        public void setUp(){
        emailData.setTo("linh.nguyen@logigear.com");
        emailData.setSubject("Email is sent successfully!");
        emailData.setContent("Test email is send successfully!");
    }

    @Test
    public void userCanLoginByUsername() {
        loginPage.openPage(PageName.DN_LGG_MAIL);
        loginPage.login(System.getenv("username"), System.getenv("password"));
        mailBoxPage.composeEmail(emailData);
    }

    LoginPage loginPage = new LoginPage();
    MailBoxPage mailBoxPage = new MailBoxPage();
    EmailData emailData = new EmailData();
}
