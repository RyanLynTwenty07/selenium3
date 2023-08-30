package testcases.vietjetair;

import base.TestBase;
import org.com.report.Logger;
import org.com.utils.Assert;
import org.com.utils.Common;
import org.example.data.Language;
import org.example.data.PageName;
import org.example.data.common.DataFilter;
import org.example.data.vj.SearchTicketData;
import org.example.page.vietjetair.FlightPage;
import org.example.page.vietjetair.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class VietJetAir_TC02 extends TestBase {

    @BeforeMethod(description = "Init data")
    public void setUp() {
        Logger.info("Get search Data from Json");
        searchTicket = DataFilter.getSearchTicketData("vi");
        searchTicket.setDepartureDate(LocalDate.now().plusMonths(3).minusDays(3));
        searchTicket.setReturnDate(LocalDate.now().plusMonths(3));
        destination = searchTicket.getDestination();
        origination = searchTicket.getOrigination();
        Common.language = Language.VIETNAM.getName();

        Logger.info("Search Ticket Data: " + searchTicket.toString());

    }

    @Test(description = "Search and choose cheapest tickets on next 3 months successfully")
    public void vietJetAir_TC02() {
        Logger.info("Step 1: Open Page");
        homePage.openPage(PageName.VIET_JET_AIR);

        Logger.info("Step 2: Search Tickets: " + searchTicket.toString());
        homePage.searchTickets(searchTicket);
        flightPage.clickContinue();

        Assert.assertTrue(flightPage.isFlightPageDisplayed(), "VP: Verify Select Travel Options page is displayed.");
        Assert.assertEquals(flightPage.getCurrencyDisplay(), "VND", "VP: Currency is display as VND");
        Assert.assertEquals(flightPage.getFromPlaceFlight(), origination, "VP: From Place is display as " + origination);
        Assert.assertEquals(flightPage.getToPlaceFlight(), destination, "VP: To Place is display as " + destination);
        Assert.assertEquals(flightPage.getReturnFromPlaceFlight(), destination, "VP: From Place is display as " + destination);
        Assert.assertEquals(flightPage.getReturnToPlaceFlight(), origination, "VP: To Place is display as " + origination);
        Assert.assertEquals(flightPage.getNumberAdults(), searchTicket.getPassenger().getAdults(), "VP: Number of passenger is correct ");

        Logger.info("Step 3: Select Lowest ticket price for Return Flight");
        flightPage.selectReturnLowestFlightPrice();

        Logger.info("VP: Verify Passenger Information page is displayed");
        Assert.assertTrue(flightPage.isPassengerPageDisplayed(), "VP: Verify Passenger page is displayed.");
        Assert.assertAll("Completed test!");
    }

    HomePage homePage = new HomePage();
    FlightPage flightPage = new FlightPage();
    SearchTicketData searchTicket = new SearchTicketData();
    String destination;
    String origination;
}
