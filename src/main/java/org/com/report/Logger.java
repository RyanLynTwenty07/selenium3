package org.com.report;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

public class Logger {

    public static void info(String... steps) {
        Allure.step(steps[0], () -> {
            for (String step : steps) {
                info(step);
            }
        });
    }

    public static void info(String step, Status status) {
        Allure.step(step, status);
    }

    public static void info(String step) {
        Allure.step(step, Status.PASSED);
    }

}
