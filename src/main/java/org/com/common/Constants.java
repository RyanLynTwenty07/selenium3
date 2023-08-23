package org.com.common;
import org.com.driver.Browser;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final Map<String, String> CONFIG_FILES = new HashMap<>();
    public static final String CHROME = "chrome";
    public static final String EDGE = "edge";
    public static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);
    public static final String VJ_SEARCH_DATA = "src/test/resources/data/vietjetair/searchflight.json";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    static {
        CONFIG_FILES.put(String.valueOf(Browser.CHROME), "src/test/resources/configuration/chrome.json");
        CONFIG_FILES.put(String.valueOf(Browser.EDGE), "src/test/resources/configuration/edge.json");
    }
}
