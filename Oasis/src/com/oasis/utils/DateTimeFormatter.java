package com.oasis.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeFormatter {
    private static final DateFormat STANDARD_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String DateToStandardDate(Date date) {
        return STANDARD_DATE_FORMAT.format(date);
    }

    public static String DateToDATETIME(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date) + " 00:00:00";
    }

    public static String DATETIMEToStandardDate(String DATETIME) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(DATETIME);
        return STANDARD_DATE_FORMAT.format(date);
    }

    public static Date DATETIMEToDate(String DATETIME) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(DATETIME);
        return date;
    }

    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
}
