package testcases.agoda;

import base.TestBase;
import org.com.report.Logger;
import org.com.utils.Assert;
import org.example.data.PageName;
import org.example.data.agoda.Hotel;
import org.example.data.agoda.ReviewData;
import org.example.data.agoda.SearchData;
import org.example.data.agoda.UserData;
import org.example.page.agoda.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class Agoda_FinalExam_TC_02 extends TestBase {

    @BeforeMethod
    public void setUp() {
        searchData.setPlace(destination);
        searchData.getNextFridayAsBookingDate();
        searchData.setCheckOutDate(3);
        searchData.setNumberOfRooms(2);
        searchData.setNumberOfPeople(4);
        searchData.setFilters(List.of("Non-smoking"));
        reviewList = reviewList.stream().sorted().collect(Collectors.toList());
        user.setKey(System.getenv("key"));
    }

    @Test(description = "Add hotel into Favourite successfully")
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
        resultPage.checkFilterOption("Property facilities", "Swimming pool");
        customSearchList = resultPage.getHotels(5);
        firstHotel = customSearchList.get(0);
        firstHotelName = firstHotel.getHotelName();

        Logger.info("Step 5: Select the 1st hotel");
        resultPage.clickHotel(1);
        hotelDetailPage.discardPromoMessage();

        Assert.assertTrue(hotelDetailPage.checkHotelHeader(firstHotel), "VP: Check Hotel Name display correct");
        Assert.assertTrue(hotelDetailPage.checkHotelAddress(destination), "VP: Check Hotel Address display correct");
        Assert.assertTrue(hotelDetailPage.checkFacilitiesDisplayed("Swimming pool"), "VP: Check Swimming Pool Available");

        Logger.info("Step 6: Add Hotel to Save list");
        hotelDetailPage.addHotelToSaveList();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "VP: Verify Login page should display");

        Logger.info("Step 7: Login");
        loginPage.login(user);

        Logger.info("Step 8: Add Hotel to Save list");
        hotelDetailPage.addHotelToSaveList();

        Logger.info("Step 9: Select Menu > Saved properties list");
        hotelDetailPage.selectUserMenuItem("Saved properties list");

        Logger.info("Step 10: Click on hotel card");
        favouritePage.selectFavouriteHotels();

        Assert.assertTrue(favouritePage.checkHotelDisplayCorrectly(firstHotel), "VP: Verify Hotel should display correct info");

        Logger.info("Current behavior is out of date or Bug, so test case will fail at this step");
        Assert.assertTrue(favouritePage.checkDateInfoDisplayCorrectly(searchData.getCheckInDate(),
                        searchData.getCheckOutDate(), searchData.getNumberOfPeople(), searchData.getNumberOfRooms()),
                "VP: Verify Date and Quantity should display correct info");
        Assert.assertAll("Complete Test case!");
    }

    @AfterMethod(description = "Final - Clean up data", alwaysRun = true)
    public void cleanUp() {
        favouritePage.removeFavourite();
    }

    HomePage homePage = new HomePage();
    FavouritePage favouritePage = new FavouritePage();
    UserData user = new UserData();
    LoginPage loginPage = new LoginPage();
    SearchData searchData = new SearchData();
    ResultPage resultPage = new ResultPage();
    List<String> reviewList = List.of("Cleanliness", "Facilities", "Location", "Service", "Value for money");
    HotelDetailPage hotelDetailPage = new HotelDetailPage();
    List<ReviewData> reviewHotel;
    List<ReviewData> reviewHotelInDetail;
    List<Hotel> customSearchList;
    String destination = "Dalat";
    String firstHotelName;
    Hotel firstHotel;
    double reviewPoint;
}
