package testcases.agoda;

import base.TestBase;
import org.com.report.Logger;
import org.com.utils.Assert;
import org.example.data.PageName;
import org.example.data.agoda.ReviewData;
import org.example.data.agoda.SearchData;
import org.example.data.agoda.Hotel;
import org.example.page.agoda.HomePage;
import org.example.page.agoda.HotelDetailPage;
import org.example.page.agoda.ResultPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        firstHotel = customSearchList.get(0);
        firstHotelName = firstHotel.getHotelName();

        Logger.info("Step 5: Get review point");
        reviewPoint = resultPage.getReviewPoint(firstHotelName);


        Logger.info("Step 6: Hover on review Score and get all review about 1st hotel");
        resultPage.hoverMouseOnHotelReviewScore(1);
        reviewHotel = resultPage.getReviewScore(reviewList);

        Assert.assertEquals(resultPage.getListReviewScore(), reviewList, "VP: Verify the review score display correctly");

        Logger.info("Step 7: Select the 1st hotel");
        resultPage.clickHotel(1);
        hotelDetailPage.discardPromoMessage();

        Assert.assertTrue(hotelDetailPage.checkHotelHeader(firstHotel), "VP: Check Hotel Name display correct");

        Logger.info("This checkpoint is failed by Bug: The address display on result page are display the same value for all hotels but in hotel detail page is different");
        Assert.assertTrue(hotelDetailPage.checkHotelAddress(firstHotel.getDestination()), "VP: Check Hotel Address display correct");
        Assert.assertTrue(hotelDetailPage.checkSwimmingPoolAvailable(), "VP: Check Swimming Pool Available");

        Logger.info("Step 8: Hover on Review Score panel");
        hotelDetailPage.hoverAndClickOnReviewScore();
        reviewHotelInDetail = hotelDetailPage.getReviewScore(reviewList);

        Assert.assertEquals(reviewHotelInDetail, reviewHotel, "VP: Verify the review score panel display the same value with search Page");
        Assert.assertAll("Complete Test case!");
    }

    HomePage homePage = new HomePage();
    SearchData searchData = new SearchData();
    ResultPage resultPage = new ResultPage();
    List<String> reviewList = List.of("Cleanliness", "Facilities", "Location", "Service", "Value for money");
    HotelDetailPage hotelDetailPage = new HotelDetailPage();
    List<ReviewData> reviewHotel;
    List<ReviewData> reviewHotelInDetail;
    List<Hotel> customSearchList;
    String destination = "Da Nang";
    String firstHotelName;
    Hotel firstHotel;
    double reviewPoint;
}
