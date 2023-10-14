package com.example.springbatch.common.utils;

public class StringUtils {
    public static String subStringUntil(String str, String delimiter) {
        int delimiterIndex = str.indexOf(delimiter);
        return (delimiterIndex == -1) ? str : str.substring(0, delimiterIndex);
    }
}
