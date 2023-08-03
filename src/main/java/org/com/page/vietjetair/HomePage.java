package org.com.page.vietjetair;

import org.com.data.vj.SearchTicketData;
import org.com.element.control.BaseElement;
import org.com.page.general.IHomePage;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class HomePage implements IHomePage {

    @Override
    public void clickButton(String button) {

    }

    public void searchTickets(SearchTicketData data) {
        if (data.getTicketType().equals("Return")) {

        }
    }

    public void selectDatePicker(String date) {
        BaseElement currentMonth = new BaseElement("//div[@class='rdrMonthName' and text()='%s']");
        BaseElement monthTitle = new BaseElement("//div[@class='rdrMonthName']");
        BaseElement nextArrow = new BaseElement("");
        BaseElement previousArrow = new BaseElement("");
        BaseElement day = new BaseElement("");
        LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        currentMonth.set(ld.format(DateTimeFormatter.ofPattern("MMMM yyyy")));


        while (!currentMonth.isDisplayed()) {
            nextArrow.click();
            int month = ld.getMonthValue();
            int calMonth = Month.valueOf(monthTitle.getText().split(" ")[0]).getValue();
            Integer y = Integer.valueOf(monthTitle.getText().split(" ")[1]);
            if (month != calMonth) {

            }
        }

        String dayNumber;
    }

    protected BaseElement ticketType = new BaseElement("(//span[.='%s']/preceding-sibling::span[1]//input)[1]");
}
