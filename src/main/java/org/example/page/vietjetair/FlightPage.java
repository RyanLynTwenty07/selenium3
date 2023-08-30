package org.example.page.vietjetair;

import org.com.driver.statics.DriverUtils;
import org.com.element.BaseElement;
import org.example.data.Label;

import java.util.List;
import java.util.stream.Collectors;

import static org.com.utils.Common.language;

public class FlightPage extends HomePage {

    public void selectLowestFlightPrice() {
        BaseElement lowestPrice = new BaseElement(flightPrice.getLocator() + "[text()=%s]");
        DriverUtils.waitForPageLoad();
        DriverUtils.scrollToBot();
        List<String> tags = flightPrice.getAllText();
        List<Integer> priceTags = tags.stream().map(e -> Integer.valueOf(e.replace(",", ""))).collect(Collectors.toList());
        priceTags = priceTags.stream().sorted().collect(Collectors.toList());
        lowestPrice.set(priceTags.get(0));
        lowestPrice.click();
        clickContinue();
    }

    public void selectReturnLowestFlightPrice() {
        selectLowestFlightPrice();
        selectLowestFlightPrice();
    }

    public boolean isPassengerPageDisplayed() {
        DriverUtils.waitForPageLoad();
        if (language.equals("vi")) dynamicH3Label.set(Label.PASSENGER_INFORMATION.getVietnam());
        dynamicH3Label.set(Label.PASSENGER_INFORMATION.getVietnam());
        return dynamicH3Label.isDisplayed();
    }

    public boolean isFlightPageDisplayed() {
        DriverUtils.waitForPageLoad();
        return bossBusinessImg.isDisplayed();
    }

    public String getCurrencyDisplay() {
        BaseElement currency = new BaseElement("//p[contains(@class,'MuiTypography-body1 MuiTypography-colorTextPrimary')]");
        return currency.getText().trim().split(" ")[1];
    }

    public String getFromPlaceFlight() {
        flightPlaceTitle.set(1);
        return flightPlaceTitle.getText().split(" \\(")[0];
    }

    public String getToPlaceFlight() {
        flightPlaceTitle.set(2);
        return flightPlaceTitle.getText().split(" \\(")[0];
    }

    public String getReturnToPlaceFlight() {
        flightPlaceTitle.set(4);
        return flightPlaceTitle.getText().split(" \\(")[0];
    }

    public String getReturnFromPlaceFlight() {
        flightPlaceTitle.set(3);
        return flightPlaceTitle.getText().split(" \\(")[0];
    }

    public int getNumberAdults() {
        return Integer.valueOf(adultsNumberLabel.getText().split(" ")[0]);
    }

    BaseElement flightPrice = new BaseElement("//div[contains(@class,'MuiBox-root jss')]//p[contains(@class,'MuiTypography-h4 MuiTypography-colorTextPrimary') and not(contains(@variantlg,'h3'))]");
    BaseElement bossBusinessImg = new BaseElement("//img[contains(@src,'businesswhite')]");
    BaseElement flightPlaceTitle = new BaseElement("(//div[contains(@class,'MuiBox-root')]/h5[@variantlg='h4'])[%s]");
    BaseElement adultsNumberLabel = new BaseElement("//p[@variantmd='h3']/span[contains(text(),'Adults')]|//p[@variantmd='h3']/span[contains(text(),'Người lớn')]");
}
