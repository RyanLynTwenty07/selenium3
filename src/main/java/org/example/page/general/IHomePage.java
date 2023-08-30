package org.example.page.general;

import org.com.driver.statics.DriverUtils;
import org.com.report.Logger;
import org.com.utils.Common;
import org.example.data.PageName;

public interface IHomePage {

    default void openPage(PageName page) {
        String url;
        switch (page) {
            case VIET_JET_AIR:
                url = String.format(page.toString(), Common.language);
                break;
            default:
                url = page.toString();
                break;
        }
        Logger.info("Navigate to " + url);
        DriverUtils.open(url);
    }

    void clickButton(String button);
}
