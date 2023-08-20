package agoda;

import base.TestBase;
import org.example.data.PageName;
import org.example.data.agoda.BookingData;
import org.example.data.agoda.HotelTitle;
import org.example.page.agoda.HomePage;
import org.example.page.agoda.LandingPage;
import org.example.page.agoda.ResultPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

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
        resultPage.waitForLoading();

        bestMatchList = resultPage.getCurrentListSearchHotels(5);
        resultPage.selectSortByPrice();
        resultPage.waitForLoading();
        lowestPricesList = resultPage.getCurrentListSearchHotels(5);

        resultPage.selectSortByBestMatch();
        resultPage.waitForLoading();
        for (int i = 0; i <= bestMatchList.size(); i++) {
            softassert.assertTrue(resultPage.checkHotel(bestMatchList.get(i), i));
        }
        softassert.assertAll();

        resultPage.selectSortByPrice();
        resultPage.waitForLoading();
        for (int i = 0; i <= lowestPricesList.size(); i++) {
            softassert.assertTrue(resultPage.checkHotel(lowestPricesList.get(i), i));
        }
        softassert.assertAll();
    }

    LandingPage landingPage = new LandingPage();
    HomePage homePage = new HomePage();
    BookingData bookingData = new BookingData();
    ResultPage resultPage = new ResultPage();
    List<HotelTitle> bestMatchList;
    List<HotelTitle> lowestPricesList;
    SoftAssert softassert = new SoftAssert();
}
