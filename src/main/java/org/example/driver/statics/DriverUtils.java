package org.example.driver.statics;

import org.example.driver.Driver;
import org.example.driver.DriverManager;

public class DriverUtils {
    public static void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void open(String url){
        DriverManager.driver().open(url);
    }

}
