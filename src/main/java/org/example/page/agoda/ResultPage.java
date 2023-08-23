package org.example.page.agoda;

import com.codeborne.selenide.SelenideElement;
import org.com.driver.statics.DriverUtils;
import org.com.element.BaseElement;
import org.com.report.Logger;
import org.example.data.agoda.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultPage extends HomePage {

    private void openSearchSortDropdown() {
        searchSortButton.click();
    }

    public void selectSortByPrice() {
        DriverUtils.switchToWindow(DriverUtils.getWindowHandles().size());
        DriverUtils.waitForPageLoad();
        if (searchSortButton.isDisplayed()) {
            openSearchSortDropdown();
            sortByPriceDropDownButton.waitForClickable();
            sortByPriceDropDownButton.click();
        } else {
            sortByPriceButton.click();
        }
        loadingSpinner.waitForDisappear();
        DriverUtils.waitForPageLoad();
    }

    public boolean check5HotelsDisplayCorrectly(String destination) {
        DriverUtils.switchToWindow(DriverUtils.getWindowHandles().size());
        List<Hotel> hotels = getHotels(5);
        int total = hotels.size();
        List<Hotel> _5hotels = total > 5 ? hotels.subList(0, 5) : hotels;
        List<Hotel> hotelsMatched = _5hotels.stream().filter(element -> element.getDestination().contains(destination)).collect(Collectors.toList());
        Logger.info("List hotels display after searching: " + hotels);
        Logger.info("List hotels meet with condition: " + hotelsMatched);
        return hotelsMatched.size() > 0 && _5hotels.equals(hotelsMatched);
    }

    public List<Hotel> getHotels(int numberHotels) {
        DriverUtils.switchToWindow(DriverUtils.getWindowHandles().size());
        List<Hotel> hotels = new ArrayList<>();
        DriverUtils.scrollToBot();
        List<String> name = hotelNameLabel.getAllText();
        DriverUtils.scrollToTop();
        int size = name.size() > numberHotels ? numberHotels : name.size();
        for (int i = 0; i < size; i++) {
            destination.set(name.get(i));
            finalPriceLabel.set(name.get(i));
            finalPriceSoldOutLabel.set(name.get(i));
            double hotelRating = getHotelRating(i);
            String priceLabel = finalPriceSoldOutLabel.isDisplayed() ? finalPriceSoldOutLabel.getText().replace("₫ ", "") : finalPriceLabel.getText();
            double price = Double.valueOf(priceLabel.replace(",", ""));
            Hotel hotel = new Hotel(name.get(i), destination.getText(), hotelRating, price);
            hotels.add(hotel);
        }
        Logger.info(String.format("Found %s hotels - %s", hotels.size(), hotels));
        return hotels;
    }

    public boolean checkHotel(Hotel hotel, int index) {
        return checkHotelTitle(hotel, index) && checkDestination(hotel, index);
    }

    private boolean checkHotelTitle(Hotel hotel, int index) {
        hotelLabel.set(index);
        hotelLabel.scrollToView();
        return hotelLabel.getText().equals(hotel.getHotelName());
    }

    private boolean checkDestination(Hotel hotel, int index) {
        destination.set(index);
        return destination.getText().equals(hotel.getDestination());
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

    private String getHotelTitle(int index) {
        hotelLabel.set(index);
        hotelLabel.scrollToView();
        return hotelLabel.getText();
    }

    private Hotel getHotelInfo(int index) {
        String hotelTitle = getHotelTitle(index);
        String hotelDestination = getHotelDestination(index);
        double hotelRating = getHotelRating(index);
        DriverUtils.scrollToTop();
        return new Hotel(hotelTitle, hotelDestination, hotelRating, 0);
    }

    public ArrayList<Hotel> getCurrentListSearchHotels(int hotels) {
        return IntStream.rangeClosed(1, hotels).mapToObj(this::getHotelInfo).collect(Collectors.toCollection(ArrayList::new));
    }

    private void setMinPrice(double price) {
        minPriceInput.scrollToView();
        minPriceInput.enter(String.valueOf(price), true);
    }

    private void setMaxPrice(double price) {
        maxPriceInput.scrollToView();
        maxPriceInput.enter(String.valueOf(price), true);
        loadingSpinner.waitForDisappear();
    }

    public void setPrice(double min, double max) {
        DriverUtils.scrollToTop();
        setMinPrice(min);
        setMaxPrice(max);
        maxPriceInput.pressEnter();
        loadingSpinner.waitForDisappear();
    }


    public void setFilterCheckBox(String option) {
        DriverUtils.waitForPageLoad();
        Logger.info("Check Option " + option);
        switch (option) {
            case "3 stars":
                filterCheckBox.set(" 3-Star rating ");
                break;
            case "Non-smoking":
                filterCheckBox.set(option);
                break;
        }
        filterCheckBox.scrollToView();
        filterCheckBox.click();
        DriverUtils.scrollToTop();
        loadingSpinner.waitForDisappear();
        DriverUtils.waitForPageLoad();
    }

    public void cleanAllFilterCheckBox() {
        cleanFilterBtn.click();
        loadingSpinner.waitForDisappear();
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
        reviewItems.set(label);
        return reviewItems.isDisplayed();
    }

    public List<String> getListReviewScore() {
        return reviewItems.getAllText().stream().sorted().collect(Collectors.toList());
    }

    public void clickHotel(int index) {
        hotelLabel.set(index);
        hotelLabel.scrollToView();
        hotelLabel.click();
        DriverUtils.switchToWindow(DriverUtils.getWindowHandles().size());
    }


    BaseElement finalPriceLabel = new BaseElement("//div[h3[.='%s']]/ancestor::li//div[@data-element-name='final-price']/span[@data-selenium='display-price']");
    BaseElement finalPriceSoldOutLabel = new BaseElement("//div[h3[.='%s']]/ancestor::li//div[@data-element-name='final-price']");

    BaseElement searchSortButton = new BaseElement("//button[@data-element-name='search-sort-dropdown']");
    BaseElement sortByPriceDropDownButton = new BaseElement("//li[@data-element-name='search-sort-price']/button");
    BaseElement sortByPriceButton = new BaseElement("//a[@data-element-name='search-sort-price']");
    BaseElement hotelLabel = new BaseElement("//li[@data-selenium='hotel-item'][%s]//h3[@data-selenium='hotel-name']");
    BaseElement hotelDestination = new BaseElement("//li[@data-selenium='hotel-item'][1]//span[@data-selenium='area-city-text']/span");
    BaseElement tourButton = new BaseElement("//*[@id='categories-tabs-tab-2']");
    BaseElement hotelNameLabel = new BaseElement("//div/h3[@data-selenium ='hotel-name']");
    BaseElement destination = new BaseElement("//div[h3[.='%s']]/following-sibling::div//span[@data-selenium='area-city-text']/span");

    BaseElement ratingLabel = new BaseElement("//li[@data-selenium='hotel-item'][%s]//div[@aria-label='rating']/*");
    BaseElement starItem = new BaseElement("//li[@data-selenium='hotel-item'][%s]//div[@aria-label='rating']/*[%s]/*[@href='%s']");
    BaseElement sortByBestMatchDropDownButton = new BaseElement("//li[@data-element-name='search-sort-recommended']/button");
    BaseElement sortByBestMatchButton = new BaseElement("//a[@data-element-name='search-sort-recommended']");
    BaseElement minPriceInput = new BaseElement("//*[@id='SideBarLocationFilters']/div[1]//input[@id='price_box_0']");
    BaseElement maxPriceInput = new BaseElement("//*[@id='SideBarLocationFilters']/div[1]//input[@id='price_box_1']");
    BaseElement filterCheckBox = new BaseElement("//*[@id='SideBarLocationFilters']//span[@aria-label='%s']");
    BaseElement cleanFilterBtn = new BaseElement("//*[@id='SideBarLocationFilters']//a[@aria-label='Clear filter Your filters']");
    BaseElement priceSliceMinSide = new BaseElement("//*[@id='SideBarLocationFilters']//*[@id='PriceFilterRange']//div[@role='slider'][1]");
    BaseElement priceSliceMaxSide = new BaseElement("//*[@id='SideBarLocationFilters']//*[@id='PriceFilterRange']//div[@role='slider'][2]");
    BaseElement detailReviewPopup = new BaseElement("//div[@data-selenium='demographics-review-container']");
    BaseElement reviewItems = new BaseElement("//span[@data-selenium='review-name']");
    BaseElement hotelReviewScoreLabel = new BaseElement("//li[@data-selenium='hotel-item'][%s]//div[@data-element-name='property-card-review']");
}