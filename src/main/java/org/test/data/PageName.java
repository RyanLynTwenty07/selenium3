package org.test.data;

public enum PageName {

    DN_LGG_MAIL("https://dnmail.logigear.com/"),
    GOOGLE(""),
    VIET_JET_AIR("https://www.vietjetair.com/"),
    AGODA("");

    PageName(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    private String text;
}
