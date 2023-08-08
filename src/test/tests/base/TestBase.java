package base;

import org.com.driver.ConfigLoader;
import org.com.driver.Configuration;
import org.com.driver.DriverManager;
import org.com.driver.Platform;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static org.com.common.Constants.CONFIG_FILES;

public class TestBase {

    private DriverManager driverManager;

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void beforeTest(@Optional String browser) {
        Configuration config = ConfigLoader.fromJsonFile(CONFIG_FILES.get("chrome"));
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
