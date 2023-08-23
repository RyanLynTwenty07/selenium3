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
import org.example.page.agoda.HotelDetailPage;
import org.example.page.agoda.ResultPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Collectors;

public class Agoda_TC_03 extends TestBase {

    @BeforeMethod
    public void setUp() {
        searchData.setPlace("Da Nang");
        searchData.getNextFridayAsBookingDate();
        searchData.setCheckOutDate(3);
        searchData.setNumberOfRooms(2);
        searchData.setNumberOfPeople(4);
        searchData.setFilters(List.of("Non-smoking"));
        reviewList = reviewList.stream().sorted().collect(Collectors.toList());
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

        Logger.info("Step 4: select Filter check box");
        resultPage.setFilterCheckBox(searchData.getFilters().get(0));
        customSearchList = resultPage.getHotels(5);

        Logger.info("Step 5: Hover on review Score");
        resultPage.hoverMouseOnHotelReviewScore(1);

        Assert.assertEquals(resultPage.getListReviewScore(), reviewList, "VP: Verify the review score display correctly");

        Logger.info("Step 6: Select the 1st hotel");
        resultPage.clickHotel(1);

        Assert.assertTrue(hotelDetailPage.checkHotelHeader(customSearchList.get(1)), "VP: Check Hotel Name display correct");
        Assert.assertTrue(hotelDetailPage.checkHotelAddress(customSearchList.get(1).getDestination()), "VP: Check Hotel Address display correct");
        Assert.assertTrue(hotelDetailPage.checkSwimmingPoolAvailable(), "VP: Check Swimming Pool Available");
        hotelDetailPage.hoverOnReviewScore();

        Assert.assertEquals(hotelDetailPage.getReviewScoreList(), reviewList, "VP: Verify the review score display correctly");
        Assert.assertAll("Complete Test case!");
    }

    HomePage homePage = new HomePage();
    SearchData searchData = new SearchData();
    ResultPage resultPage = new ResultPage();
    List<String> reviewList = List.of("Cleanliness", "Facilities", "Location", "Service", "Value for money");
    HotelDetailPage hotelDetailPage = new HotelDetailPage();
    List<Hotel> customSearchList;
    String destination = "Da Nang";
}
