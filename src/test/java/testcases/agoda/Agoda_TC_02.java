package testcases.agoda;

import base.TestBase;
import io.qameta.allure.Allure;
import org.com.report.Logger;
import org.com.utils.Assert;
import org.com.utils.Common;
import org.example.data.PageName;
import org.example.data.agoda.SearchData;
import org.example.data.agoda.Hotel;
import org.example.page.agoda.HomePage;
import org.example.page.agoda.ResultPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Agoda_TC_02 extends TestBase {

    @BeforeMethod
    public void setUp() {
        maxPrice = 1000000;
        minPrice = 500000;
        searchData.setPlace("Da Nang");
        searchData.getNextFridayAsBookingDate();
        searchData.setCheckOutDate(3);
        searchData.setNumberOfRooms(2);
        searchData.setNumberOfPeople(4);
        searchData.setMinPrice(minPrice);
        searchData.setMaxPrice(maxPrice);
        searchData.setFilters(List.of("3 stars"));
    }

    @Test(description = "Search and sort hotel successfully")
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

        Logger.info("Step 4: Set Price and select Filter check box");
        resultPage.setPrice(searchData.getMinPrice(), searchData.getMaxPrice());
        resultPage.setFilterCheckBox(searchData.getFilters().get(0));
        customSearchList = resultPage.getHotels(5);

        Assert.assertTrue(homePage.checkHotelsMeetRangePriceFiltered(customSearchList, minPrice, maxPrice),"VP: Verify Hotels displays meet range price filtered");

        Logger.info("Step 5: Clean all check box");
        resultPage.cleanAllFilterCheckBox();

        Assert.assertTrue(resultPage.checkPriceSliceReset(), "VP: Verify all filter are reset");
        Assert.assertAll("Completed Test case!");
    }

    HomePage homePage = new HomePage();
    SearchData searchData = new SearchData();
    ResultPage resultPage = new ResultPage();
    String destination = "Da Nang";
    List<Hotel> customSearchList;
    Hotel hotel;
    double maxPrice;
    double minPrice;
}
