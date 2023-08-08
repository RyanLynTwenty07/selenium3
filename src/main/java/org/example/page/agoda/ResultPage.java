package org.example.page.agoda;

import io.qameta.allure.Step;
import org.com.element.BaseElement;
import org.example.data.agoda.HotelTitle;

import java.time.Duration;
import java.util.List;

public class ResultPage extends LandingPage {

    public void waitForLoading() {
        loadingSpinner.waitForDisappear();
    }

    private void openSearchSortDropdown() {
        searchSortButton.click();
    }

    @Step("Select sort by price")
    public void selectSortByPrice() {
        tourButton.switchNextTab();
        if (searchSortButton.isDisplayed()) {
            openSearchSortDropdown();
            sortByPriceDropDownButton.waitForClickable();
            sortByPriceDropDownButton.click();
        } else {
            sortByPriceButton.click();
        }
    }

    public void checkSearchResults(List<HotelTitle> hotels) {
        hotels.forEach(hotel -> checkEachHotel(hotel, hotels.indexOf(hotel) + 1));
    }

    private void checkEachHotel(HotelTitle hotel, int index) {
        hotelLabel.set(index);
        hotelLabel.waitForExist(Duration.ofMinutes(1));
        hotelLabel.checkText(hotel.getHotelName());
        hotelDestination.set(index);
        hotelLabel.waitForExist(Duration.ofMinutes(1));
        hotelDestination.checkText(hotel.getDestination());
    }

    protected BaseElement loadingSpinner = new BaseElement("//div[@id='ModalLoadingSpinner']");
    protected BaseElement finalPriceLabel = new BaseElement("//div[@data-element-name='final-price']/span[@data-selenium='display-price']");
    protected BaseElement searchSortButton = new BaseElement("//button[@data-element-name='search-sort-dropdown']");
    protected BaseElement sortByPriceDropDownButton = new BaseElement("//li[@data-element-name='search-sort-price']/button");
    protected BaseElement sortByPriceButton = new BaseElement("//a[@data-element-name='search-sort-price']");
    protected BaseElement hotelLabel = new BaseElement("//li[@data-selenium='hotel-item'][%s]//h3[@data-selenium='hotel-name']");
    protected BaseElement hotelDestination = new BaseElement("//li[@data-selenium='hotel-item'][1]//span[@data-selenium='area-city-text']/span");
    protected BaseElement tourButton = new BaseElement("//*[@id='categories-tabs-tab-2']");
}