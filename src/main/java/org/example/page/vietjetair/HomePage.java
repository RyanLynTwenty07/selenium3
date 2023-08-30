package org.example.page.vietjetair;

import org.com.driver.statics.DriverUtils;
import org.com.element.BaseElement;
import org.example.data.Label;
import org.example.data.vj.Passenger;
import org.example.data.vj.SearchTicketData;
import org.example.page.general.IHomePage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

import static org.com.utils.Common.language;

public class HomePage implements IHomePage {

    protected BaseElement ticketType = new BaseElement("(//span[.='%s']/preceding-sibling::span[1]//input)[1]");
    protected BaseElement departureDate = new BaseElement("//p[.='%s']");
    protected BaseElement locationPanel = new BaseElement("//div[@id='panel1a-content']//div[.='%s']");
    protected BaseElement fromLabel = new BaseElement("//label[.='From']");
    protected BaseElement toLabel = new BaseElement("//label[.='To']");
    protected BaseElement passengerLabel = new BaseElement("//label[.='%s']");
    protected BaseElement adultPlusButton = new BaseElement("(//span[contains(@class,'MuiTypography-colorPrimary')])[1]/following-sibling::button");
    protected BaseElement childrenPlusButton = new BaseElement("(//span[contains(@class,'MuiTypography-colorPrimary')])[2]/following-sibling::button");
    protected BaseElement infantsPlusButton = new BaseElement("(//span[contains(@class,'MuiTypography-colorPrimary')])[3]/following-sibling::button");
    protected BaseElement adultMinusButton = new BaseElement("(//span[contains(@class,'MuiTypography-colorPrimary')])[1]/preceding-sibling::button");
    protected BaseElement childrenMinusButton = new BaseElement("(//span[contains(@class,'MuiTypography-colorPrimary')])[2]/preceding-sibling::button");
    protected BaseElement infantsMinusButton = new BaseElement("(//span[contains(@class,'MuiTypography-colorPrimary')])[3]/preceding-sibling::button");
    protected BaseElement letGoButton = new BaseElement("//button/span[@class='MuiButton-label']/span[text()=\"%s\"]");
    protected BaseElement notNowButton = new BaseElement("name=notnow");
    protected BaseElement acceptCookie = new BaseElement("//h5[.='%s']");
    protected BaseElement iframe = new BaseElement("//iframe[@class='__st_preview_frame_bpn']");
    protected BaseElement closeArmy = new BaseElement("//div[@class='osc-greeting-btn-close']");
    protected BaseElement findCheapestCheckBox = new BaseElement("class=MuiTypography-root pointer MuiTypography-h3 MuiTypography-colorTextPrimary");
    protected BaseElement dynamicButton = new BaseElement("//span[text()='%s']");
    protected BaseElement dynamicH3Label = new BaseElement("//h3[.='%s']");

    @Override
    public void clickButton(String button) {
        dynamicButton.set(button);
        dynamicButton.scrollToView();
        dynamicButton.click();
    }

    public void searchTickets(SearchTicketData data) {
        acceptAllPopup();
        if (data.getTicketType().equals("Return")) {
            clickDepartureDate();
            selectDatePicker(data.getDepartureDate());
            selectDatePicker(data.getReturnDate());
            selectLocation(data.getOrigination());
            selectLocation(data.getDestination());
        } else {

        }
        clickPassenger();
        selectPassenger(data.getPassenger());
        if (data.isLowestFare()) {
            findCheapestCheckBox.click();
        }
        clickLetsGoButton();
    }

    public void clickContinue() {
        if (language.equals("vi")) {
            clickButton(Label.CONTINUE.getVietnam());
        } else clickButton(Label.CONTINUE.getEnglish());
    }

    public void clickLetsGoButton() {
        if (language.equals("vi")) {
            letGoButton.set(Label.LETS_GO.getVietnam());
        } else letGoButton.set(Label.LETS_GO.getEnglish());

        letGoButton.waitForVisible();
        letGoButton.click();
    }

    public void selectDatePicker(LocalDate date) {
        Locale locale = new Locale("vi", "VN");
        BaseElement currentMonth = new BaseElement("//div[@class='rdrMonthName' and text()='%s']");
        BaseElement nextArrow = new BaseElement("class=rdrNextPrevButton rdrNextButton");
        BaseElement day = new BaseElement("//div[@class='rdrMonth'][div[text()='%s']]//button[not(contains(@tabindex,'-1'))]/span[.=%s]");

        String calMonth = language.equals("vi") ? date.format(DateTimeFormatter.ofPattern("MMMM yyyy", locale))
                : date.format(DateTimeFormatter.ofPattern("MMMM yyyy"));

        int dayNumber = date.getDayOfMonth();
        currentMonth.set(calMonth);
        while (!currentMonth.isDisplayed()) {
            nextArrow.click();
        }
        day.set(calMonth, dayNumber);
        day.click();
    }

    public void selectLocation(String location) {
        locationPanel.set(location);
        locationPanel.click();
    }

    public void selectPassenger(Passenger quantity) {
        adultPlusButton.click();
        if (quantity.getChildren() > 0) {
            childrenPlusButton.click(quantity.getChildren());
        }
        if (quantity.getInfants() > 0) {
            infantsPlusButton.click(quantity.getInfants());
        }
    }

    public void acceptAllPopup() {
        DriverUtils.waitForPageLoad();
        iframe.switchToFrame();
        notNowButton.click();
        DriverUtils.switchToMain();
        acceptCookie();
    }

    public void acceptCookie() {
        if (language.equals("vi")) {
            acceptCookie.set(Label.ACCEPT.getVietnam());
        } else acceptCookie.set(Label.ACCEPT.getEnglish());

        acceptCookie.click();
        closeArmy.click();
    }

    public void clickDepartureDate() {
        if (language.equals("vi")) departureDate.set(Label.DEPARTURE_DATE.getVietnam());
        else departureDate.set(Label.DEPARTURE_DATE.getEnglish());
        departureDate.click();
    }

    public void clickPassenger() {
        if (language.equals("vi")) passengerLabel.set(Label.PASSENGER.getVietnam());
        else passengerLabel.set(Label.PASSENGER.getEnglish());
        passengerLabel.click();
    }
}
