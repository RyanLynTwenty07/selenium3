package base;

import org.com.driver.ConfigLoader;
import org.com.driver.Configuration;
import org.com.driver.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static org.com.common.Constants.CONFIG_FILES;

public class TestBase {

    private DriverManager driverManager;

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser","node"})
    public void beforeTest(String browser, @Optional String node) {
        Configuration config = ConfigLoader.fromJsonFile(CONFIG_FILES.get(browser));
        if (node != null) {
            config.setRemote(node);
        }
        driverManager = new DriverManager(config);
        driverManager.open();
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        driverManager.close();
    }
}
