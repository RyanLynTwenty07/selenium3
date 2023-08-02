package org.test.common;
import java.util.HashMap;
import java.util.Map;

import static org.example.driver.Platform.*;

public class Constants {
    public static final Map<String, String> CONFIG_FILES = new HashMap<>();

    static {
        CONFIG_FILES.put(String.valueOf(CHROME), "src/test/resources/configuration/chrome.json");
        CONFIG_FILES.put(String.valueOf(EDGE), "src/test/resources/configuration/edge.json");
    }
}
