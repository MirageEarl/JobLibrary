package com.tangbo.test.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

    public static String getCurrentDateTime(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.now().format(formatter);
    }

    public static String getDateTime(LocalDate localDate, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);
    }

    public static LocalDate getLocalDateTime(String dateTimeStr, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format, Locale.CHINA);
        return LocalDate.parse(dateTimeStr, df);
    }
}
