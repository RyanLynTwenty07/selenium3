package org.example.driver;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.example.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;

public class ConfigLoader {
    /**
     * Load configuration from json file. These properties will be override if system properties are set
     * {
     * "platform": "Chrome",
     * "headless": false,
     * "browserSize": "1366x768",
     * "startMaximized": false,
     * "pageLoadStrategy": "normal",
     * "capabilities": {
     * "caps": {}
     * },
     * "baseUrl": "http://localhost:8080",
     * "timeout": 4000,
     * "pollingInterval": 200,
     * "clickViaJs": false
     * }
     *
     * @param jsonFile
     * @return
     */
    public static Configuration fromJsonFile(String jsonFile) {
        Configuration config =  JsonUtils.fromFile(new File(Path.of(jsonFile).toUri()),Configuration.class);
        return config;
    }



}
