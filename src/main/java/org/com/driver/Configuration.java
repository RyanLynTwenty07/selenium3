package org.com.driver;

import com.codeborne.selenide.SelenideConfig;
import lombok.Data;
import org.com.utils.JsonUtils;

@Data
public class Configuration {

    private String browser;
    private boolean isHeadless;
    private String remote;
    private String browserSize;
    private String browserVersion;
    private String baseUrl;
    private long timeOut;
    private long pollingInterval;
    private long pageLoadTimeout;
    private boolean isMaximize;
    private boolean proxyEnabled;
    boolean clickViaJs;

    public SelenideConfig toJson() {
        return JsonUtils.fromJSON(JsonUtils.toJSON(this), SelenideConfig.class);
    }
}
