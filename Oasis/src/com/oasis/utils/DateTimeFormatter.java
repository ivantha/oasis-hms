package com.oasis.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeFormatter {
    private static final DateFormat DISPLAY_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

//    public static String DateToDisplayDate(Date date) {
//        return DISPLAY_DATE_FORMAT.format(date);
//    }

//    public static String DateToDATETIME(Date date) {
//        return new SimpleDateFormat("yyyy-MM-dd").format(date) + " 00:00:00";
//    }

//    public static String DATETIMEToDisplayDate(String DATETIME) throws ParseException {
//        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(DATETIME);
//        return DISPLAY_DATE_FORMAT.format(date);
//    }

    public static LocalDate DATETIMEToLocalDate(String DATETIME) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(DATETIME);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    public static LocalTime TIMEtoLocalTime(String TIME) throws ParseException {
        Time time = Time.valueOf(TIME);
        return time.toLocalTime();
    }

//    public static Date addDays(Date date, int days) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DATE, days);
//        return calendar.getTime();
//    }
}
