package org.example.data;


public enum PageName {

    DN_LGG_MAIL("https://dnmail.logigear.com/"),
    VIET_JET_AIR("https://www.vietjetair.com/%s/"),
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
