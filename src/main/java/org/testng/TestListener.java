package org.testng;


import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;


public class TestListener implements ITestListener {
    String env = null;

    @Override
    @SneakyThrows
    public void onStart(ITestContext context) {
        log.info("Tests started on {}", context.getCurrentXmlTest().getParameter("browser"));
//        env = context.getCurrentXmlTest().getParameter("env").toLowerCase();
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test case \"{} - {}\" is started", result.getMethod().getMethodName(),
                result.getMethod().getDescription());
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    private static final Logger log = LoggerFactory.getLogger(TestListener.class);

}

