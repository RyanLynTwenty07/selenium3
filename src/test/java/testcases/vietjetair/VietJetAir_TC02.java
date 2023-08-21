package testcases.vietjetair;

import org.example.data.email.EmailData;
import org.example.page.dnmail.LoginPage;
import org.example.page.dnmail.MailBoxPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.TestBase;

public class VietJetAir_TC02 extends TestBase {

    @BeforeMethod
    public void setUp() {

    }

    @Test(description = "Compose and save draft email successfully")
    public void vietJetAir_TC02() {

    }

    LoginPage loginPage = new LoginPage();
    MailBoxPage mailBoxPage = new MailBoxPage();
    EmailData emailData = new EmailData();
    LoginPage.User user = new LoginPage.User();
}
