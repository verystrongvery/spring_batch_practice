package com.example.springbatch.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String localDateToString(LocalDate date, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return dateTimeFormatter.format(date);
    }

    public static LocalDate stringToLocalDate(String dateString, String format) {
        if (dateString.trim().isEmpty()) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(dateString, dateTimeFormatter);
    }
}
