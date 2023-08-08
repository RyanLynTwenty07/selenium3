package org.example.page.agoda;

import org.com.element.BaseElement;
import org.example.page.general.IHomePage;

public class LandingPage implements IHomePage {


    @Override
    public void clickButton(String button) {

    }

    public void waitForLoadingState() {
        loadingBox.waitForDisappear();
    }

    protected BaseElement loadingBox = new BaseElement("//*[@id='ModalLoadingSpinner']");
}
