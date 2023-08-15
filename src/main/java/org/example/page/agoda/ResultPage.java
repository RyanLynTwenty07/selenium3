package org.example.page.agoda;

import io.qameta.allure.Step;
import org.com.driver.Driver;
import org.com.driver.statics.DriverUtils;
import org.com.element.BaseElement;
import org.example.data.agoda.HotelTitle;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultPage extends LandingPage {

    private void openSearchSortDropdown() {
        searchSortButton.click();
    }

    private boolean checkEachHotel(HotelTitle hotel, int index) {
        hotelLabel.set(index);
        hotelLabel.waitForExist(Duration.ofMinutes(1));
        hotelDestination.set(index);
        hotelLabel.waitForExist(Duration.ofMinutes(1));
        return hotelLabel.getText().equals(hotel.getHotelName()) && hotelDestination.getText().equals(hotel.getDestination());
    }

    public void waitForLoading() {
        loadingSpinner.waitForDisappear();
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

    @Step("Verify ")
    public boolean check5HotelsDisplayCorrectly(String destination) {
        DriverUtils.switchToWindow(DriverUtils.getWindowHandles().size());
        List<HotelTitle> hotels = getHotels().subList(0, 5);
        List<HotelTitle> inputs = hotels.stream().filter(element -> element.getDestination().contains(destination)).collect(Collectors.toList());
        return inputs.size() == 5;
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

    @Step("Get list hotels display after searching")
    public List<HotelTitle> getHotels() {
        List<HotelTitle> hotels = new ArrayList<>();
        DriverUtils.scrollToBot();
        List<String> name = hotelNameLabel.getAllText();
        int size = name.size() > 10 ? 10 : name.size();
        for (int i = 0; i < size; i++) {
            destination.set(name.get(i));
            HotelTitle hotel = new HotelTitle(name.get(i), destination.getText());
            hotels.add(hotel);
        }
        return hotels;
    }

    BaseElement loadingSpinner = new BaseElement("//div[@id='ModalLoadingSpinner']");
    BaseElement finalPriceLabel = new BaseElement("//div[@data-element-name='final-price']/span[@data-selenium='display-price']");
    BaseElement searchSortButton = new BaseElement("//button[@data-element-name='search-sort-dropdown']");
    BaseElement sortByPriceDropDownButton = new BaseElement("//li[@data-element-name='search-sort-price']/button");
    BaseElement sortByPriceButton = new BaseElement("//a[@data-element-name='search-sort-price']");
    BaseElement hotelLabel = new BaseElement("//li[@data-selenium='hotel-item'][%s]//h3[@data-selenium='hotel-name']");
    BaseElement hotelDestination = new BaseElement("//li[@data-selenium='hotel-item'][1]//span[@data-selenium='area-city-text']/span");
    BaseElement tourButton = new BaseElement("//*[@id='categories-tabs-tab-2']");
    BaseElement hotelNameLabel = new BaseElement("//div/h3[@data-selenium ='hotel-name']");
    BaseElement destination = new BaseElement("//div[h3[.='%s']]/following-sibling::div//span[@data-selenium='area-city-text']/span");
}