package org.example.page.agoda;

import org.com.element.BaseElement;
import org.example.data.agoda.HotelTitle;

import java.time.Duration;

public class HotelDetailPage extends LandingPage {

    private void discardPromoMessage() {
        promoGotItButton.waitForVisible(Duration.ofMinutes(1));
        promoGotItButton.click();
    }

    private void waitForLoading() {
        loadingLabel.waitForDisappear();
    }

    public boolean checkHotelHeader(HotelTitle hotel) {
        return hotel.getHotelName().equals(hotelHeader.getText());
    }

    public boolean checkHotelAddress(String hotelAddress) {
        return hotelAddress.equals(hotelAddressLabel.getText());
    }

    public boolean checkSwimmingPoolAvailable() {
        return swimmingPoolLabel.isDisplayed();
    }

    BaseElement loadingLabel = new BaseElement("//div[@class='AboveTheFlowPlaceholder']");
    BaseElement promoGotItButton = new BaseElement("//span[text()='Got it']/ancestor::button");
    BaseElement hotelHeader = new BaseElement("//p[@data-selenium='hotel-header-name']");
    BaseElement hotelAddressLabel = new BaseElement("//span[@data-selenium='hotel-address-map']");
    BaseElement swimmingPoolLabel = new BaseElement("//span[text()='Swimming Pool']");
}