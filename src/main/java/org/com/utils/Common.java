package org.com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {

    public static String findStringWithRegex(String input, String regex) {
        Matcher m = Pattern.compile(regex).matcher(input);
        if (m.find()) {
            return m.group(0);
        }
        return "No match";
    }

    public static String language;
}
