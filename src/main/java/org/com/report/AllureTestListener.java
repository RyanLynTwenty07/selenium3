package org.com.report;

import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.com.driver.Driver;
import org.com.driver.statics.DriverUtils;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

public class AllureTestListener implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus().equals(Status.BROKEN) || result.getStatus().equals(Status.FAILED)) {
            log.info("Test case \"{}\" has been {}. Take a screenshot", result.getFullName(),
                    result.getStatus().value());
            try {
                if (DriverUtils.isAlive()) {
                    ByteArrayInputStream input = new ByteArrayInputStream(DriverUtils.takeScreenShot(OutputType.BYTES));
                    Allure.addAttachment("ScreenShot - " + result.getFullName(), input);
                }
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        }
    }

    private static final Logger log = LoggerFactory.getLogger(AllureTestListener.class);
}
