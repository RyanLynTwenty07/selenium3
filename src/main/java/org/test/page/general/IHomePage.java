package org.test.page.general;

import io.qameta.allure.Step;
import org.example.driver.statics.DriverUtils;
import org.test.data.PageName;

public interface IHomePage {

    @Step("Navigate to {page} page")
    default void openPage(PageName page) {
        DriverUtils.open(page.toString());
    }

    void clickButton(String button);
}
