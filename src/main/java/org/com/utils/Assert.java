package org.com.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

public class Assert {

    public static void assertTrue(boolean condition) {
        assertTrue(condition, "");
    }

    public static void assertTrue(boolean condition, String messages) {
        try {
            softAssertion.assertTrue(condition, messages);
        } catch (AssertionError ex) {
            throw ex;
        }
    }

    public static <T> void assertEquals(T actual, T expected, String message) {
        try {
            softAssertion.assertEquals(actual, expected, message);
        } catch (AssertionError ex) {
            throw ex;
        }
    }

    public static <T> void assertEquals(T actual, T expected) {
        assertEquals(actual, expected, "");
    }

    public static void assertEquals(String actual, String expected, String message) {
        try {
            softAssertion.assertEquals(actual, expected, message);
        } catch (AssertionError ex) {
            throw ex;
        }
    }

    public static void assertEquals(String actual, String expected) {
        assertEquals(actual, expected, "");
    }

    public static void assertFalse(boolean condition) {
        try {
            softAssertion.assertFalse(condition);
        } catch (AssertionError ex) {
            throw ex;
        }
    }

    public static <T> void assertNotEquals(T actual, T expected) {
        try {
            softAssertion.assertNotEquals(actual, expected);
        } catch (AssertionError ex) {
            throw ex;
        }
    }

    public static void assertAll(String message) {
        softAssertion.assertAll(message);
    }

    public static SoftAssertion softAssertion = new SoftAssertion();
}
