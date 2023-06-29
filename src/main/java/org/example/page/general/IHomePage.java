package org.example.page.general;

import org.example.driver.statics.DriverUtils;
import org.example.data.PageName;

public interface IHomePage {

    default void openPage(PageName page){
        DriverUtils.open(page.toString());
    }

}
