package testcases.agoda;

import base.TestBase;
import org.example.data.PageName;
import org.example.data.agoda.BookingData;
import org.example.data.agoda.HotelTitle;
import org.example.page.agoda.HomePage;
import org.example.page.agoda.LandingPage;
import org.example.page.agoda.ResultPage;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Agoda_TC_01 extends TestBase {

    @BeforeMethod
    public void setUp() {
        bookingData.setPlace("Da Nang");
        bookingData.getNextFridayAsBookingDate();
        bookingData.setCheckOutDate(3);
        bookingData.setNumberOfRooms(2);
        bookingData.setNumberOfPeople(4);
        lowestPricesList = hotelTitle.returnListHotelRec();
    }

    @Test(description = "Search and sort hotel successfully")
    public void userCanSearchAndSortHotel() {
        landingPage.openPage(PageName.AGODA);
        homePage.selectPlace(bookingData.getPlace());
        homePage.selectCheckInAndCheckOutDate(bookingData.getCheckInDate(), bookingData.getCheckOutDate());
        homePage.selectOccupancy(bookingData.getNumberOfPeople(), bookingData.getNumberOfRooms());
        homePage.searchHotels();
        resultPage.waitForLoading();
        resultPage.check5HotelsDisplayCorrectly(destination);
        resultPage.selectSortByPrice();
        Assert.assertTrue(resultPage.checkSearchResults(lowestPricesList));
    }

    LandingPage landingPage = new LandingPage();
    HomePage homePage = new HomePage();
    BookingData bookingData = new BookingData();
    ResultPage resultPage = new ResultPage();
    HotelTitle hotelTitle = new HotelTitle("The Grumpy House","Phước Mỹ, Da Nang - 2.1 km to center");
    List<HotelTitle> bestMatchList;
    List<HotelTitle> lowestPricesList;
    String destination = "Da Nang";
}
