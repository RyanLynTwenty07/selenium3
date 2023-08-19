package org.com.report;

import io.qameta.allure.Allure;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.TestResult;
import org.com.driver.statics.DriverUtils;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.util.UUID;

public class AllureStepListener implements StepLifecycleListener {

    private static final Logger LOG = LoggerFactory.getLogger(AllureStepListener.class);

    @Override
    public void beforeStepStart(StepResult result) {
        LOG.info("[Step]: " + result.getName());
    }

    @Override
    public void afterStepUpdate(StepResult result) {
        if (result.getStatus().equals(Status.FAILED)) {
            if (DriverUtils.isAlive()) {
                ByteArrayInputStream input = new ByteArrayInputStream(DriverUtils.takeScreenShot(OutputType.BYTES));
                Allure.addAttachment("ScreenShot", input);
            }
        }
    }
}
