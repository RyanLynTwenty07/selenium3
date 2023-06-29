package org.example.driver;

import com.codeborne.selenide.Browser;
import com.codeborne.selenide.SelenideConfig;
import lombok.Data;
import org.example.utils.JsonUtils;

@Data
public class Configuration {

    private Platform platform;
    private boolean isHeadless;
    private long pageLoadTimeOut;
    private String remote;
    private String browserSize;
    private String browserVersion;
    private String baseUrl;
    private long timeOut;
    private long pollingInterval;
    private boolean isMaximize;
    private boolean proxyEnabled;
    boolean clickViaJs;

    public Configuration(boolean initial) {
        this.platform = Platform.CHROME;
        this.isHeadless = false;
        this.pageLoadTimeOut = 60_000;
        this.isMaximize = true;
        this.browserVersion =  "114.0.5735.90";
    }

    public SelenideConfig toJson() {
        return JsonUtils.fromJSON(JsonUtils.toJSON(this), SelenideConfig.class);
    }
}
