package org.com.common;
import org.com.driver.Platform;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final Map<String, String> CONFIG_FILES = new HashMap<>();

    static {
        CONFIG_FILES.put(String.valueOf(Platform.CHROME), "src/test/resources/configuration/chrome.json");
        CONFIG_FILES.put(String.valueOf(Platform.EDGE), "src/test/resources/configuration/edge.json");
    }
}
