package testcases.vietjetair;

import org.apache.commons.lang3.RandomUtils;
import org.example.data.email.EmailData;
import org.example.data.PageName;
import org.com.driver.statics.DriverUtils;
import org.example.data.vj.SearchTicketData;
import org.example.page.dnmail.LoginPage;
import org.example.page.dnmail.MailBoxPage;
import org.example.page.vietjetair.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.TestBase;

import java.time.LocalDate;

public class VietjetAir_TC01 extends TestBase {

    @BeforeMethod
    public void setUp() {
        searchData.setDepartureDate(LocalDate.now().plusDays(1));
        searchData.setReturnDate(LocalDate.now().plusDays(2));
        searchData.setOrigination("Ho Chi Minh");
        searchData.setDestination("Ha Noi");
        searchData.setTicketType("Return");
        searchData.setPassenger(1);
    }

    @Test(description = "Compose and save draft email successfully")
    public void vietjetAir_TC01() {
        homePage.openPage(PageName.VIET_JET_AIR);
        homePage.searchTickets(searchData);
    }


    HomePage homePage = new HomePage();
    SearchTicketData searchData = new SearchTicketData();
}
