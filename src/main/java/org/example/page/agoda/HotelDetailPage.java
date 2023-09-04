package org.example.page.agoda;

import org.com.driver.statics.DriverUtils;
import org.com.element.BaseElement;
import org.com.report.Logger;
import org.example.data.agoda.Hotel;
import org.example.data.agoda.ReviewData;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelDetailPage extends HomePage {

    public void discardPromoMessage() {
        promoGotItButton.waitForVisible(Duration.ofMinutes(1));
        promoGotItButton.click();
        DriverUtils.waitForPageLoad();
    }

    public boolean checkHotelHeader(Hotel hotel) {
        Logger.info("Hotel Name: " + hotel.getHotelName() + " - Hotel Header after search: " + hotelHeader.getText());
        return hotel.getHotelName().equals(hotelHeader.getText());
    }

    public boolean checkHotelAddress(String hotelAddress) {
        Logger.info("Hotel Address: " + hotelAddress + " - Hotel Address after search: " + hotelAddressLabel.getText());
        return hotelAddress.equals(hotelAddressLabel.getText());
    }

    public boolean checkSwimmingPoolAvailable() {
        return swimmingPoolLabel.isDisplayed();
    }

    public List<String> getReviewScoreList() {
        return tooltipBoxItems.getAllText().stream().sorted().collect(Collectors.toList());
    }

    public void hoverAndClickOnReviewScore() {
        reviewNumberLabel.waitForExist();
        DriverUtils.scrollToTop();
        reviewNumberLabel.hover();
        reviewNumberLabel.click();
    }

    public List<ReviewData> getReviewScore(List<String> reviewName) {
        List<ReviewData> reviewDataList = new ArrayList<>();
        for (String rv : reviewName) {
            ReviewData review = new ReviewData();
            tooltipBoxItemsScore.set(rv);
            tooltipBoxItemsScore.waitForVisible();
            review.setReviewCriteria(rv);
            double score = tooltipBoxItemsScore.getText().trim().isEmpty() ? 0 :
                    Double.valueOf(tooltipBoxItemsScore.getText().trim());
            review.setScore(score);
            reviewDataList.add(review);
        }
        Logger.info("List Review and Score In Detail Page: " + reviewDataList);
        return reviewDataList;
    }

    BaseElement loadingLabel = new BaseElement("//div[@class='AboveTheFlowPlaceholder']");
    BaseElement promoGotItButton = new BaseElement("//span[text()='Got it']/ancestor::button");
    BaseElement hotelHeader = new BaseElement("//p[@data-selenium='hotel-header-name']");
    BaseElement hotelAddressLabel = new BaseElement("//span[@data-selenium='hotel-address-map']");
    BaseElement swimmingPoolLabel = new BaseElement("//span[text()='Swimming Pool']");
    BaseElement tooltipBoxItems = new BaseElement("//div[@class='rc-tooltip-content']//div[contains(@class,'Review-travelerGrade')]//span");
    BaseElement tooltipBoxItemsScore = new BaseElement("//div[@data-selenium='review-card-popup']//span[@class='Review-travelerGradeCategory' and text()='%s']/following-sibling::span");
    BaseElement reviewNumberLabel = new BaseElement("(//div[@class='ReviewScoreCompact__score']//p)[1]");
}
