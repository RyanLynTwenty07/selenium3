package org.com.driver;


public enum Browser {
    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge");

    Browser(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    private String text;
}
