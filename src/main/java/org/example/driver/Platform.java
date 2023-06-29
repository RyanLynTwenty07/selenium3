package org.example.driver;

import lombok.AllArgsConstructor;
import lombok.Data;


public enum Platform {
    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge");

    Platform(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    private String text;
}
