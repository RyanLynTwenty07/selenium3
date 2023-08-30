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

public class Agoda_TC_01 extends TestBase {

    @BeforeMethod
    public void setUp() {
        searchData.setPlace("Da Nang");
        searchData.getNextFridayAsBookingDate();
        searchData.setCheckOutDate(3);
        searchData.setNumberOfRooms(2);
        searchData.setNumberOfPeople(4);
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

        Logger.info("Step 3: Sort By Price ");
        resultPage.selectSortByPrice();

        Assert.assertAll("completed test!");
    }

    HomePage homePage = new HomePage();
    SearchData searchData = new SearchData();
    ResultPage resultPage = new ResultPage();
    String destination = "Da Nang";
    List<Hotel> customSearchList;
    Hotel hotel;
}
