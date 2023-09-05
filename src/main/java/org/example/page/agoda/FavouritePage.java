package org.example.page.agoda;

import org.com.driver.statics.DriverUtils;
import org.com.element.BaseElement;
import org.example.data.agoda.Hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FavouritePage {

    public void selectFavouriteHotels() {
        DriverUtils.waitForPageLoad();
        favouriteCard.click();
    }

    public boolean checkHotelDisplayCorrectly(Hotel hotel) {
        DriverUtils.waitForPageLoad();
        BaseElement name = new BaseElement("//div[normalize-space()='%s']");
        name.set(hotel.getHotelName());
        name.waitForVisible();
        return name.isDisplayed();
    }

    public boolean checkDateInfoDisplayCorrectly(LocalDate in, LocalDate out, int personNum, int roomsNum) {
        return checkInDate.getText().equals(in.format(DateTimeFormatter.ofPattern("dd MMM yyyy")))
                && checkOutDate.getText().equals(out.format(DateTimeFormatter.ofPattern("dd MMM yyyy")))
                && numberPerson.getText().startsWith(String.valueOf(personNum))
                && numberRooms.getText().startsWith(String.valueOf(roomsNum));
    }


    BaseElement favouriteCard = new BaseElement("//div[@data-selenium='favorite-group-card']");
    BaseElement dynamicInfo = new BaseElement("//div[normalize-space()='%s']");
    BaseElement checkInDate = new BaseElement("//div[@data-selenium='checkInText']");
    BaseElement checkOutDate = new BaseElement("//div[@data-selenium='checkOutText']");
    BaseElement numberPerson = new BaseElement("//span[@data-selenium='adultValue']");
    BaseElement numberRooms = new BaseElement("//div[@data-selenium='roomValue']");
}
