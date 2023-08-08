package org.example.page.agoda;

import io.qameta.allure.Step;
import org.com.element.BaseElement;

public class ResultPage extends LandingPage {

    public void openSearchSortDropdown() {
        searchSortButton.click();
    }

    @Step("Select sort by price")
    public void selectSortByPrice() {
        openSearchSortDropdown();
        sortByPriceDropDownButton.waitForClickable();
        sortByPriceDropDownButton.click();
    }

    protected BaseElement finalPriceLabel = new BaseElement("//div[@data-element-name='final-price']/span[@data-selenium='display-price']");
    protected BaseElement searchSortButton = new BaseElement("//button[@data-element-name='search-sort-dropdown']");
    protected BaseElement sortByPriceDropDownButton = new BaseElement("//li[@data-element-name='search-sort-price']/button");
}