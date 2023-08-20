package org.example.page.agoda;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.com.driver.statics.DriverUtils;
import org.com.element.BaseElement;
import org.example.data.agoda.HotelTitle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultPage extends LandingPage {

    public void waitForLoading() {
        tourButton.switchNextTab();
        loadingSpinner.waitForDisappear();
    }

    private void openSearchSortDropdown() {
        searchSortButton.click();
    }

    @Step("Select sort by price")
    public void selectSortByPrice() {
        DriverUtils.scrollToTop();
        if (searchSortButton.isDisplayed()) {
            openSearchSortDropdown();
            sortByPriceDropDownButton.waitForClickable();
            sortByPriceDropDownButton.click();
        } else {
            sortByPriceButton.click();
        }
    }

    @Step("Select sort by best match")
    public void selectSortByBestMatch() {
        DriverUtils.scrollToTop();
        if (searchSortButton.isDisplayed()) {
            openSearchSortDropdown();
            sortByBestMatchDropDownButton.waitForClickable();
            sortByBestMatchDropDownButton.click();
        } else {
            sortByBestMatchButton.click();
        }
    }

    @Step("Check result is sorted ")
    public boolean checkResultSortCorrect(String sortOption) {
        switch (sortOption) {
            case "Low price":
                break;
            case "Best Match":
            case "High price":
        }
        return true;
    }

    public boolean checkHotel(HotelTitle hotel, int index) {
        return checkHotelTitle(hotel, index) && checkDestination(hotel, index);
    }

    private boolean checkHotelTitle(HotelTitle hotel, int index) {
        hotelLabel.set(index);
        hotelLabel.scrollToView();
        return hotelLabel.getText().equals(hotel.getHotelName());
    }

    private boolean checkDestination(HotelTitle hotel, int index) {
        destination.set(index);
        return destination.getText().equals(hotel.getDestination());
    }

    private HotelTitle getHotelInfo(int index) {
        String hotelTitle = getHotelTitle(index);
        String hotelDestination = getHotelDestination(index);
        double hotelRating = getHotelRating(index);
        DriverUtils.scrollToTop();
        return new HotelTitle(hotelTitle, hotelDestination, hotelRating);
    }

    private String getHotelTitle(int index) {
        hotelLabel.set(index);
        hotelLabel.scrollToView();
        return hotelLabel.getText();
    }

    private String getHotelDestination(int index) {
        destination.set(index);
        return destination.getText();
    }

    private double getHotelRating(int index) {
        ratingLabel.set(index);
        List<SelenideElement> stars = ratingLabel.findElements();
        int count = stars.size();
        double starCount = 0;
        String fullStar = "#StarSymbolFillIcon";
        String halfStar = "#StarHalfSymbolFillIcon";
        for (int starNo = 1; starNo <= count; starNo++) {
            starItem.set(index, starNo, fullStar);
            if (starItem.isDisplayed()) {
                starCount += 1;
            }
            starItem.set(index, starNo, halfStar);
            if (starItem.isDisplayed()) {
                starCount += 0.5;
            }
        }
        return starCount;
    }

    public ArrayList<HotelTitle> getCurrentListSearchHotels(int hotels) {
        return IntStream.rangeClosed(1, hotels).mapToObj(this::getHotelInfo).collect(Collectors.toCollection(ArrayList::new));
    }

    private void setMinPrice(int price) {
        minPriceInput.enter(String.valueOf(price), true);
    }

    private void setMaxPrice(int price) {
        maxPriceInput.enter(String.valueOf(price), true);
    }

    public void setPrice(int min, int max) {
        setMinPrice(min);
        setMaxPrice(max);
        maxPriceInput.pressEnter();
    }

    public void setFilterCheckBox(String option) {
        switch (option) {
            case "3 stars":
                filterCheckBox.set(" 3-Star rating ");
                filterCheckBox.scrollToView();
                filterCheckBox.click();
                DriverUtils.scrollToTop();
                break;
            case "non smoking hotels":
                filterCheckBox.set("Non-smoking");
                filterCheckBox.scrollToView();
                filterCheckBox.click();
                DriverUtils.scrollToTop();
                break;
        }
    }

    public void cleanAllFilterCheckBox() {
        cleanFilterBtn.click();
    }

    public boolean checkPriceSliceReset() {
        return priceSliceMinSide.isDisplayed() && priceSliceMaxSide.isDisplayed();
    }

    public void hoverMouseOnHotelReviewScore(int index) {
        hotelReviewScoreLabel.set(index);
        hotelReviewScoreLabel.scrollToView();
        hotelReviewScoreLabel.hover();
    }

    public boolean checkReviewDetailPopupLabelDisplay(String label) {
        detailReviewPopup.waitForVisible();
        detailReviewPopupInfo.set(label);
        return detailReviewPopupInfo.isDisplayed();
    }

    public void clickHotel(int index) {
        hotelLabel.set(index);
        hotelLabel.scrollToView();
        hotelLabel.click();
        hotelLabel.switchNextTab();
    }

    BaseElement loadingSpinner = new BaseElement("//div[@data-selenium='loading-item']");
    BaseElement finalPriceLabel = new BaseElement("//div[@data-element-name='final-price']/span[@data-selenium='display-price']");
    BaseElement searchSortButton = new BaseElement("//button[@data-element-name='search-sort-dropdown']");
    BaseElement sortByPriceDropDownButton = new BaseElement("//li[@data-element-name='search-sort-price']/button");
    BaseElement sortByPriceButton = new BaseElement("//a[@data-element-name='search-sort-price']");
    BaseElement tourButton = new BaseElement("//*[@id='categories-tabs-tab-2']");
    BaseElement hotelNameLabel = new BaseElement("//div/h3[@data-selenium ='hotel-name']");
    BaseElement destination = new BaseElement("//div[h3[.='%s']]/following-sibling::div//span[@data-selenium='area-city-text']/span");
    BaseElement ratingLabel = new BaseElement("//li[@data-selenium='hotel-item'][%s]//div[@aria-label='rating']/*");
    BaseElement starItem = new BaseElement("//li[@data-selenium='hotel-item'][%s]//div[@aria-label='rating']/*[%s]/*[@href='%s']");
    BaseElement sortByBestMatchDropDownButton = new BaseElement("//li[@data-element-name='search-sort-recommended']/button");
    BaseElement sortByBestMatchButton = new BaseElement("//a[@data-element-name='search-sort-recommended']");
    BaseElement hotelLabel = new BaseElement("//li[@data-selenium='hotel-item'][%s]//h3[@data-selenium='hotel-name']");
    BaseElement minPriceInput = new BaseElement("//*[@id='SideBarLocationFilters']/div[1]//input[@id='price_box_0']");
    BaseElement maxPriceInput = new BaseElement("//*[@id='SideBarLocationFilters']/div[1]//input[@id='price_box_1']");
    BaseElement filterCheckBox = new BaseElement("//*[@id='SideBarLocationFilters']//span[@aria-label='%s']");
    BaseElement cleanFilterBtn = new BaseElement("//*[@id='SideBarLocationFilters']//a[@aria-label='Clear filter Your filters']");
    BaseElement priceSliceMinSide = new BaseElement("//*[@id='SideBarLocationFilters']//*[@id='PriceFilterRange']//div[@role='slider'][1]");
    BaseElement priceSliceMaxSide = new BaseElement("//*[@id='SideBarLocationFilters']//*[@id='PriceFilterRange']//div[@role='slider'][2]");
    BaseElement detailReviewPopup = new BaseElement("//div[@data-selenium='demographics-review-container']");
    BaseElement detailReviewPopupInfo = new BaseElement("//span[@data-selenium='review-name' and text()='%s']");
    BaseElement hotelReviewScoreLabel = new BaseElement("//li[@data-selenium='hotel-item'][%s]//div[@data-element-name='property-card-review']");
}