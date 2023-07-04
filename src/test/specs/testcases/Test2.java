package testcases;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.SelenideLog;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

public class Test2 {

    @Step("X func")
    private void x() {
        SelenideLog log = SelenideLogger.beginStep("?", "?");
        Allure.step("X");
        SelenideLogger.commitStep(log, LogEvent.EventStatus.PASS);
        System.out.println("He");
    }

    @Test
    public void t() {
        x();
    }

}
