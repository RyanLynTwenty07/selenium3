package testcases.agoda;

import base.TestBase;
import org.com.report.Logger;
import org.com.utils.Assert;
import org.example.data.PageName;
import org.example.data.agoda.Hotel;
import org.example.data.agoda.SearchData;
import org.example.page.agoda.HomePage;
import org.example.page.agoda.HotelDetailPage;
import org.example.page.agoda.ResultPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Agoda_FinalExam_TC_01 extends TestBase {

    @BeforeMethod
    public void setUp() {
        searchData.setPlace(destination);
        searchData.setCheckInDate(0);
        searchData.setCheckOutDate(3);
        searchData.setNumberOfRooms(1);
        searchData.setNumberOfPeople(2);
        searchData.setFilters(List.of("Breakfast included"));
    }

    @Test(description = "Search and filter hotels successfully")
    public void userCanSearchAndSortHotel() {
        Logger.info("Step 1: Open AGODA");
        homePage.openPage(PageName.AGODA);

        Logger.info("Step 2: Select filters and search Hotel: ");
        homePage.selectPlace(searchData.getPlace());
        homePage.selectCheckInAndCheckOutDate(searchData.getCheckInDate(), searchData.getCheckOutDate());
        homePage.selectOccupancy(searchData.getNumberOfPeople(), searchData.getNumberOfRooms());
        homePage.searchHotels();

        Logger.info("Step 3: Get Hotels: ");
        customSearchList = resultPage.getHotels(5);

        Assert.assertTrue(homePage.checkHotelDisplayCorrectDestination(customSearchList, destination), "VP: Verify Hotel searched display with correct destination");

        Logger.info("Step 4: Select Breakfast included check box and search");
        resultPage.checkFilterOption("Room offers", searchData.getFilters().get(0));
        customSearchList = resultPage.getHotels(5);
        firstHotel = customSearchList.get(0);
        firstHotelName = firstHotel.getHotelName();

        Assert.assertTrue(homePage.checkHotelsMeetRangePriceFiltered(customSearchList, minPrice, maxPrice), "VP: Verify Hotels displays meet range price filtered");

        Logger.info("Step 5: Select 1st Hotel and open detail");
        resultPage.clickHotel(1);

        Assert.assertTrue(hotelDetailPage.checkHotelHeader(firstHotel), "VP: Check Hotel Name display correct");

        Logger.info("This checkpoint is failed by Bug: The address display on result page are display the same value for all hotels but in hotel detail page is different");
        Assert.assertTrue(hotelDetailPage.checkHotelAddress(destination), "VP: Check Hotel Address display correct");
        Assert.assertTrue(hotelDetailPage.isRoomOptionDisplay("Breakfast included"), "VP: Check Breakfast included Available");

        Assert.assertAll("Completed Test case!");
    }

    HomePage homePage = new HomePage();
    SearchData searchData = new SearchData();
    ResultPage resultPage = new ResultPage();
    String destination = "Dalat";
    HotelDetailPage hotelDetailPage = new HotelDetailPage();
    List<Hotel> customSearchList;
    String firstHotelName;
    Hotel firstHotel;
    double maxPrice;
    double minPrice;
}
