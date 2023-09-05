package org.example.page.agoda;

import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.com.driver.statics.DriverUtils;
import org.com.element.BaseElement;
import org.com.report.Logger;
import org.com.utils.Assert;
import org.com.utils.Common;
import org.com.utils.DateUtils;
import org.example.data.agoda.Hotel;
import org.example.page.general.IHomePage;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HomePage implements IHomePage {

    public void selectPlace(String place) {
        Logger.info("Select place: " + place);
        discardPromoMessage();
        placeBox.waitForExist();
        placeBox.click();
        placeTextBox.waitForVisible();
        placeTextBox.enter(place, true);
        placeOptionItem.set(place);
        placeOptionItem.waitForVisible();
        placeOptionItem.click();
        placeBox.pressEscape();
    }

    @Override
    public void clickButton(String button) {
        // custom action
    }

    public void selectDatePicker(LocalDate date) {
        BaseElement previousNarrowButton = new BaseElement("//button[@data-selenium='calendar-previous-month-button']");
        BaseElement nextNarrowButton = new BaseElement("//button[@data-selenium='calendar-next-month-button']");
        BaseElement title = new BaseElement("//div[@class='DayPicker-Caption DayPicker-Caption-Wide' and text()='%s']");
        BaseElement monthLabel = new BaseElement("(//div[@class='DayPicker-Caption DayPicker-Caption-Wide'])[1]");
        DriverUtils.waitForPageLoad();

        int month = date.getMonth().getValue();
        String header = date.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
        title.set(header);

        while (!title.isDisplayed()) {
            int dpMonth = Month.valueOf(monthLabel.getText().split(" ")[0].toUpperCase()).getValue();
            if (month < dpMonth) {
                previousNarrowButton.waitForExist();
                previousNarrowButton.click();
            } else {
                nextNarrowButton.waitForExist();
                nextNarrowButton.click();
            }
        }
        datePickerItem.set(date.format(DateTimeFormatter.ofPattern("EEE MMM dd YYYY")));
        datePickerItem.click();
    }

    public void selectCheckInAndCheckOutDate(LocalDate checkIn, LocalDate checkOut) {
        checkInBox.click();
        DriverUtils.waitForPageLoad();
        Logger.info("Select Check In Date: " + checkIn);
        selectDatePicker(checkIn);
        Logger.info("Select Check Out Date: " + checkOut);
        selectDatePicker(checkOut);
        checkInBox.pressEscape();
    }

    private void selectPeople(int people) {
        while (Integer.parseInt(occupancyPeopleLabel.getText()) < people) {
            occupancyPeoplePlusButton.click();
        }
    }

    private void selectRooms(int room) {
        while (Integer.parseInt(occupancyRoomLabel.getText()) < room) {
            occupancyRoomPlusButton.click();
        }
    }

    public void selectOccupancy(int people, int room) {
        occupancyBox.click();
        Logger.info("Select People Numbers: " + people);
        selectPeople(people);
        Logger.info("Select Room Numbers: " + room);
        selectRooms(room);
        occupancyBox.pressEscape();
    }

    public void searchHotels() {
        Logger.info("Click Search Hotels");
        searchButton.click();
        loadingSpinner.waitForDisappear();
    }

    public void discardPromoMessage() {
        promoNoThankButton.waitForVisible(Duration.ofMinutes(1));
        promoNoThankButton.click();
    }

    public boolean checkHotelDisplayCorrectDestination(List<Hotel> hotels, String destination) {
        for (int i = 0; i < hotels.size(); i++) {
            Hotel hotel = hotels.get(i);
            if (!hotel.getDestination().contains(destination)) {
                Logger.info("Hotel " + i + ": " + hotel.getDestination(), Status.FAILED);
                return false;
            }
            Logger.info("Hotel " + i + ": " + hotel.getDestination());
        }
        return true;
    }

    public boolean checkHotelsMeetRangePriceFiltered(List<Hotel> hotels, double min, double max) {
        for (int i = 0; i < hotels.size(); i++) {
            Hotel hotel = hotels.get(i);
            Logger.info("Hotel " + hotel.toString());
            boolean checked = hotel.getPrice() <= max || hotel.getPrice() >= min;
            if (!checked) {
                return false;
            }
        }
        return true;
    }

    public void selectUserMenuItem(String item){
        userIcon.click();
        userMenuItems.set(item);
        userMenuItems.waitForVisible();
        userMenuItems.click();
    }

    protected BaseElement loadingSpinner = new BaseElement("//div[@id='ModalLoadingSpinner']");
    protected BaseElement placeBox = new BaseElement("//div[@id='autocomplete-box']");
    protected BaseElement checkInBox = new BaseElement("//div[@id='check-in-box']");
    protected BaseElement occupancyBox = new BaseElement("//div[@id='occupancy-box']");
    protected BaseElement placeTextBox = new BaseElement("//div[@id='autocomplete-box']//input[@id='textInput']");
    protected BaseElement placeOptionItem = new BaseElement("(//span[@data-selenium='suggestion-text-highlight' and text()='%s'])[1]");
    protected BaseElement datePickerItem = new BaseElement("//div[@aria-label='%s']");
    protected BaseElement occupancyRoomLabel = new BaseElement("//div[@data-selenium='desktop-occ-room-value']/h3");
    protected BaseElement occupancyRoomPlusButton = new BaseElement("//div[@data-element-name='occupancy-selector-panel-rooms' and @data-selenium='plus']");
    protected BaseElement occupancyRoomMinusButton = new BaseElement("//div[@data-element-name='occupancy-selector-panel-rooms' and @data-selenium='minus']");
    protected BaseElement occupancyPeopleLabel = new BaseElement("//div[@data-selenium='desktop-occ-adult-value']/h3");
    protected BaseElement occupancyPeoplePlusButton = new BaseElement("//div[@data-element-name='occupancy-selector-panel-adult' and @data-selenium='plus']");
    protected BaseElement occupancyPeopleMinusButton = new BaseElement("//div[@data-element-name='occupancy-selector-panel-adult' and @data-selenium='minus']");
    protected BaseElement searchButton = new BaseElement("//button[@data-selenium='searchButton']");
    protected BaseElement promoNoThankButton = new BaseElement("//button[text()='No thanks']");
    protected BaseElement loadingBox = new BaseElement("//*[@id='ModalLoadingSpinner']");
    protected BaseElement userIcon = new BaseElement("//div[@data-element-name='user-menu']");
    protected BaseElement userMenuItems = new BaseElement("//a[normalize-space()='%s']");
}