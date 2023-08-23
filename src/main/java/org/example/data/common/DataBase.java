package org.example.data.common;

import org.com.utils.JsonUtils;

public class DataBase {

    public DataBase(String jsonFile) {
        this.jsonFile = jsonFile;
    }

    public String getJsonFile() {
        return jsonFile;
    }

    public void setJsonFile(String jsonFile) {
        this.jsonFile = jsonFile;
    }

    public <T> T to() {
        return JsonUtils.to(jsonFile, this.getClass());
    }
    private String jsonFile = "";

}
