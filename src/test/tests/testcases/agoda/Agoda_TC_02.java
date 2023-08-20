package agoda;

import base.TestBase;
import org.example.data.PageName;
import org.example.data.agoda.BookingData;
import org.example.data.agoda.HotelTitle;
import org.example.page.agoda.HomePage;
import org.example.page.agoda.LandingPage;
import org.example.page.agoda.ResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Agoda_TC_02 extends TestBase {

    @BeforeMethod
    public void setUp() {
        bookingData.setPlace("Da Nang");
        bookingData.getNextFridayAsBookingDate();
        bookingData.setCheckOutDate(3);
        bookingData.setNumberOfRooms(2);
        bookingData.setNumberOfPeople(4);
        bookingData.setMinPrice(500000);
        bookingData.setMaxPrice(1000000);
        bookingData.setFilters(List.of("3 stars"));
    }

    @Test(description = "Search and sort hotel successfully")
    public void userCanSearchAndSortHotel() {
        landingPage.openPage(PageName.AGODA);
        homePage.selectPlace(bookingData.getPlace());
        homePage.selectCheckInAndCheckOutDate(bookingData.getCheckInDate(), bookingData.getCheckOutDate());
        homePage.selectOccupancy(bookingData.getNumberOfPeople(), bookingData.getNumberOfRooms());
        homePage.searchHotels();
        resultPage.waitForLoading();

        resultPage.setPrice(bookingData.getMinPrice(), bookingData.getMaxPrice());
        resultPage.waitForLoading();
        resultPage.setFilterCheckBox(bookingData.getFilters().get(1));
        resultPage.waitForLoading();
        customSearchList = resultPage.getCurrentListSearchHotels(5);

        resultPage.setPrice(defaultMinPrice, defaultMaxPrice);
        resultPage.waitForLoading();
        resultPage.cleanAllFilterCheckBox();
        resultPage.waitForLoading();
        Assert.assertTrue(resultPage.checkPriceSliceReset());

        resultPage.setPrice(bookingData.getMinPrice(), bookingData.getMaxPrice());
        resultPage.waitForLoading();
        resultPage.setFilterCheckBox(bookingData.getFilters().get(1));
        resultPage.waitForLoading();
        for (int i = 0; i <= customSearchList.size(); i++) {
            softassert.assertTrue(resultPage.checkHotel(customSearchList.get(i), i));
        }
        softassert.assertAll();
    }

    LandingPage landingPage = new LandingPage();
    HomePage homePage = new HomePage();
    BookingData bookingData = new BookingData();
    ResultPage resultPage = new ResultPage();
    List<HotelTitle> customSearchList;
    int defaultMinPrice = 0;
    int defaultMaxPrice = 78000000;
    SoftAssert softassert = new SoftAssert();
}
