package testcases;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.driver.ConfigLoader;
import org.example.driver.Configuration;
import org.example.driver.DriverManager;
import org.example.driver.Platform;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static org.example.common.Constants.CONFIG_FILES;

public class TestBase {

    private DriverManager driverManager;

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void beforeTest(@Optional String browser) {
        Configuration config =   ConfigLoader.fromJsonFile(CONFIG_FILES.get("chrome"));
        config.setPlatform(Platform.CHROME);
        driverManager = new DriverManager();
        driverManager.setConfig(config);
        driverManager.open(null);
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        driverManager.close();
    }
}
