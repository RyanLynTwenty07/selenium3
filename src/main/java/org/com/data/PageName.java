package org.com.data;

public enum PageName {

    DN_LGG_MAIL("https://dnmail.logigear.com/"),
    GOOGLE(""),
    VIET_JET_AIR("https://www.vietjetair.com/en/"),
    AGODA("https://www.agoda.com/");

    PageName(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    private String text;
}
