package org.example.page.general;

import io.qameta.allure.Step;
import org.com.driver.statics.DriverUtils;
import org.example.data.PageName;

public interface IHomePage {

    @Step("Navigate to {page} page")
    default void openPage(PageName page) {
        DriverUtils.open(page.toString());
    }

    void clickButton(String button);
}
