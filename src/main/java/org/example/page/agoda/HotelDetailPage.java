package org.example.page.agoda;

import org.com.element.BaseElement;
import org.example.data.agoda.Hotel;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HotelDetailPage extends HomePage {

    public void discardPromoMessage() {
        promoGotItButton.waitForVisible(Duration.ofMinutes(1));
        promoGotItButton.click();
    }

    public boolean checkHotelHeader(Hotel hotel) {
        return hotel.getHotelName().equals(hotelHeader.getText());
    }

    public boolean checkHotelAddress(String hotelAddress) {
        return hotelAddress.equals(hotelAddressLabel.getText());
    }

    public boolean checkSwimmingPoolAvailable() {
        return swimmingPoolLabel.isDisplayed();
    }

    public List<String> getReviewScoreList() {
        return tooltipBoxItems.getAllText().stream().sorted().collect(Collectors.toList());
    }

    public void hoverOnReviewScore(){
        reviewNumberLabel.waitForExist();
        reviewNumberLabel.scrollToView();
        reviewNumberLabel.hover();
    }

    BaseElement loadingLabel = new BaseElement("//div[@class='AboveTheFlowPlaceholder']");
    BaseElement promoGotItButton = new BaseElement("//span[text()='Got it']/ancestor::button");
    BaseElement hotelHeader = new BaseElement("//p[@data-selenium='hotel-header-name']");
    BaseElement hotelAddressLabel = new BaseElement("//span[@data-selenium='hotel-address-map']");
    BaseElement swimmingPoolLabel = new BaseElement("//span[text()='Swimming Pool']");
    BaseElement tooltipBoxItems = new BaseElement("//div[@class='rc-tooltip-content']//div[contains(@class,'Review-travelerGrade')]//span");
    BaseElement reviewNumberLabel = new BaseElement(" //div[@class='ReviewScoreCompact__section']//div[@class='review-basedon']/span[@class='hover-text']");
}
