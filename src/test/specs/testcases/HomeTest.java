package testcases;

import org.example.page.dnmail.LoginPage;
import org.example.data.PageName;
import org.testng.annotations.Test;

public class HomeTest extends TestBase {

    @Test
    public void userCanLoginByUsername() {
        loginPage.openPage(PageName.DN_LGG_MAIL);
        loginPage.login("linh.nguyen", System.getenv("password"));
    }
    LoginPage loginPage = new LoginPage();
}
