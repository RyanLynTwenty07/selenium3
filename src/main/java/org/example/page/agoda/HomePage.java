package org.example.page.agoda;

import io.qameta.allure.Step;
import org.com.element.BaseElement;
import org.com.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;

public class HomePage extends LandingPage {

    @Step("Select place: {place}")
    public void selectPlace(String place) {
        discardPromoMessage();
        placeBox.waitForExist();
        placeBox.click();
        placeTextBox.waitForVisible();
        placeTextBox.enter(place, true);
        placeOptionItem.set(place);
        placeOptionItem.click();
        placeBox.pressEscape();
    }

    private void selectCheckInDate(String checkInDate) {
        datePickerItem.set(checkInDate);
        datePickerItem.click();
    }

    private void selectCheckOutDate(String checkOutDate) {
        datePickerItem.set(checkOutDate);
        datePickerItem.click();
    }

    @Step("Select check in date: {checkIn} and check out date: {checkOut}")
    public void selectCheckInAndCheckOutDate(LocalDate checkIn, LocalDate checkOut) {
        checkInBox.click();
        SimpleDateFormat datePickerFormat = new SimpleDateFormat("EEE MMM dd YYYY");
        String checkInDate = datePickerFormat.format(DateUtils.asDate(checkIn));
        String checkOutDate = datePickerFormat.format(DateUtils.asDate(checkOut));
        selectCheckInDate(checkInDate);
        selectCheckOutDate(checkOutDate);
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

    @Step("Select: {people} people and {room} rooms")
    public void selectOccupancy(int people, int room) {
        occupancyBox.click();
        selectPeople(people);
        selectRooms(room);
        occupancyBox.pressEscape();
    }

    @Step("Search hotels")
    public void searchHotels() {
        searchButton.click();
    }

    private void discardPromoMessage() {
        promoNoThankButton.waitForVisible(Duration.ofMinutes(1));
        promoNoThankButton.click();
    }

    protected BaseElement placeBox = new BaseElement("//div[@id='autocomplete-box']");
    protected BaseElement checkInBox = new BaseElement("//div[@id='check-in-box']");
    protected BaseElement occupancyBox = new BaseElement("//div[@id='occupancy-box']");
    protected BaseElement placeTextBox = new BaseElement("//div[@id='autocomplete-box']//input[@id='textInput']");
    protected BaseElement placeOptionItem = new BaseElement("//li[@class='Suggestion Suggestion__categoryName' and @data-text='%s']");
    protected BaseElement datePickerItem = new BaseElement("//div[@aria-label='%s']");
    protected BaseElement occupancyRoomLabel = new BaseElement("//div[@data-selenium='desktop-occ-room-value']/h3");
    protected BaseElement occupancyRoomPlusButton = new BaseElement("//div[@data-element-name='occupancy-selector-panel-rooms' and @data-selenium='plus']");
    protected BaseElement occupancyRoomMinusButton = new BaseElement("//div[@data-element-name='occupancy-selector-panel-rooms' and @data-selenium='minus']");
    protected BaseElement occupancyPeopleLabel = new BaseElement("//div[@data-selenium='desktop-occ-adult-value']/h3");
    protected BaseElement occupancyPeoplePlusButton = new BaseElement("//div[@data-element-name='occupancy-selector-panel-adult' and @data-selenium='plus']");
    protected BaseElement occupancyPeopleMinusButton = new BaseElement("//div[@data-element-name='occupancy-selector-panel-adult' and @data-selenium='minus']");
    protected BaseElement searchButton = new BaseElement("//button[@data-selenium='searchButton']");
    protected BaseElement promoNoThankButton = new BaseElement("//button[text()='No thanks']");
}