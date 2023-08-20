package org.example.page.agoda;

import org.com.element.BaseElement;
import org.example.page.general.IHomePage;

public class LandingPage implements IHomePage {


    @Override
    public void clickButton(String button) {

    }

    public void waitForLoadingState() {
        loadingPopup.waitForDisappear();
    }

    BaseElement loadingPopup = new BaseElement("//*[@id='ModalLoadingSpinner']");
}
