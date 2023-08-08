package testcases.agoda;

import base.TestBase;
import org.example.data.PageName;
import org.example.data.agoda.BookingData;

import org.example.page.agoda.HomePage;
import org.example.page.agoda.LandingPage;
import org.example.page.dnmail.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Agoda_TC_01 extends TestBase {

    @BeforeMethod
    public void setUp() {
        bookingData.setPlace("Da Nang");
        bookingData.getNextFridayAsBookingDate();
        bookingData.setCheckOutDate(3);
        bookingData.setNumberOfRooms(2);
        bookingData.setNumberOfPeople(4);
    }

    @Test(description = "Search and sort hotel successfully")
    public void userCanSearchAndSortHotel() {
        landingPage.openPage(PageName.AGODA);
        homePage.selectPlace(bookingData.getPlace());
        homePage.selectCheckInAndCheckOutDate(bookingData.getCheckInDate(), bookingData.getCheckOutDate());
        homePage.selectOccupancy(bookingData.getNumberOfPeople(), bookingData.getNumberOfRooms());
        homePage.searchHotels();
    }

    LandingPage landingPage = new LandingPage();
    HomePage homePage = new HomePage();
    BookingData bookingData = new BookingData();
    LoginPage.User user = new LoginPage.User();
}
