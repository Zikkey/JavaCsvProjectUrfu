package com.zikkey.ulearnhelper.application.extensions;

public class StringExtensions {
    public static int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch(NumberFormatException e) {
            return -1;
        }
    }
}
