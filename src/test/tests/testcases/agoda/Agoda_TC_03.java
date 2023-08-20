package agoda;

import base.TestBase;
import org.example.data.PageName;
import org.example.data.agoda.BookingData;
import org.example.data.agoda.HotelTitle;
import org.example.page.agoda.HomePage;
import org.example.page.agoda.HotelDetailPage;
import org.example.page.agoda.LandingPage;
import org.example.page.agoda.ResultPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Agoda_TC_03 extends TestBase {

    @BeforeMethod
    public void setUp() {
        bookingData.setPlace("Da Nang");
        bookingData.getNextFridayAsBookingDate();
        bookingData.setCheckOutDate(3);
        bookingData.setNumberOfRooms(2);
        bookingData.setNumberOfPeople(4);
        bookingData.setFilters(List.of("non smoking hotels"));
    }

    @Test(description = "Search and sort hotel successfully")
    public void userCanSearchAndSortHotel() {
        landingPage.openPage(PageName.AGODA);
        homePage.selectPlace(bookingData.getPlace());
        homePage.selectCheckInAndCheckOutDate(bookingData.getCheckInDate(), bookingData.getCheckOutDate());
        homePage.selectOccupancy(bookingData.getNumberOfPeople(), bookingData.getNumberOfRooms());
        homePage.searchHotels();
        resultPage.waitForLoading();

        resultPage.setFilterCheckBox(bookingData.getFilters().get(1));
        customSearchList = resultPage.getCurrentListSearchHotels(5);
        resultPage.waitForLoading();
        resultPage.hoverMouseOnHotelReviewScore(1);
        for (int i = 0; i <= reviewList.size(); i++) {
            softassert.assertTrue(resultPage.checkReviewDetailPopupLabelDisplay(reviewList.get(i)));
        }
        softassert.assertAll();

        resultPage.clickHotel(1);
        hotelDetailPage.checkHotelHeader(customSearchList.get(1));
        hotelDetailPage.checkHotelAddress("N/A");
        hotelDetailPage.checkSwimmingPoolAvailable();
    }

    LandingPage landingPage = new LandingPage();
    HomePage homePage = new HomePage();
    BookingData bookingData = new BookingData();
    ResultPage resultPage = new ResultPage();
    List<String> reviewList = List.of("Cleanliness", "Facilities", "Location", "Service", "Value for money");
    HotelDetailPage hotelDetailPage = new HotelDetailPage();
    List<HotelTitle> customSearchList;
    SoftAssert softassert = new SoftAssert();
}
