package org.example.page.vietjetair;

import io.qameta.allure.Step;
import org.com.driver.statics.DriverUtils;
import org.example.data.vj.SearchTicketData;
import org.com.element.BaseElement;
import org.example.page.general.IHomePage;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class HomePage implements IHomePage {

    @Override
    public void clickButton(String button) {

    }

    @Step("Search Ticket")
    public void searchTickets(SearchTicketData data) {
        DriverUtils.waitForPageLoad();
        if (data.getTicketType().equals("Return")) {
            ticketTypeButton.click("Return");
            selectDatePicker(data.getDepartureDate());
            selectDatePicker(data.getReturnDate());
            dropDownItem.click(data.getOrigination());
            dropDownItem.click(data.getDepartureDate());
        }
    }

    public void selectDatePicker(LocalDate date) {
        //div[@class='rdrMonth'][div[.='September 2023']]//button[not(contains(@class,'rdrDayPassive'))]/span[.='1']
        BaseElement currentMonth = new BaseElement("//div[@class='rdrMonthName' and text()='%s']");
        BaseElement monthTitle = new BaseElement("//div[@class='rdrMonthName']");
        BaseElement nextArrow = new BaseElement("class=rdrNextPrevButton rdrNextButton");
        BaseElement previousArrow = new BaseElement("class=rdrNextPrevButton rdrPprevButton");
        BaseElement day = new BaseElement("//div[@class='rdrMonth'][div[.='%s']]//button[not(contains(@class,'rdrDayPassive'))]/span[.='%s']");
        String title = date.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
        currentMonth.set(title);
        int dayNumber = date.getDayOfMonth();
        int inputMonth = date.getMonthValue();
        int inputYear = date.getYear();
        while (!currentMonth.isDisplayed()) {
            int calMonth = Month.valueOf(monthTitle.getText().split(" ")[0]).getValue();
            Integer calYear = Integer.valueOf(monthTitle.getText().split(" ")[1]);
            if (inputMonth != calMonth || inputYear != calYear) {
                if (inputMonth > calMonth) {
                    nextArrow.click();
                } else {
                    previousArrow.click();
                }
            }
        }
        day.click(title, dayNumber);
    }

    protected BaseElement ticketTypeButton = new BaseElement("(//span[.='%s']/preceding-sibling::span[1]//input)[1]");
    protected BaseElement toTextbox = new BaseElement("//input[@id='arrivalPlaceDesktop']");
    protected BaseElement fromTextbox = new BaseElement("//div[label[.='From']]/div/input");
    protected BaseElement continueButton = new BaseElement("//span[text()='Continue']");
    protected BaseElement dropDownItem = new BaseElement("//div[contains(@class,'MuiBox-root')]//div[.='%s']");
}
