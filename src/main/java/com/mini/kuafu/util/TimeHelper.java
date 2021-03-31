package com.mini.kuafu.util;

import com.google.common.base.Strings;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期时间工具类
 */
public final class TimeHelper {
    public static final ZoneId CST = ZoneId.of("Asia/Shanghai");
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
    public static final DateTimeFormatter PRECISE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    public static final DateTimeFormatter DATE_TIME_MINUTE = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm");
    public static final DateTimeFormatter DATE_TIME_MINUTE_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATE_TIME_MINUTE_NO_YEAR = DateTimeFormatter.ofPattern("MM月dd日 HH:mm");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private TimeHelper() {
    }

    public static String smart(LocalDateTime ldt, LocalDateTime now) {
        if (ldt == null) {
            return "";
        }

        if (ldt.getYear() == now.getYear()) {
            return DATE_TIME_MINUTE_NO_YEAR.format(ldt);
        }

        return DATE_TIME_MINUTE.format(ldt);
    }

    public static String smart(LocalDateTime ldt) {
        return smart(ldt, LocalDateTime.now());
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        if (Strings.isNullOrEmpty(dateTime)) {
            return null;
        }

        return parseDateTime(dateTime, DATE_TIME_FORMATTER);
    }

    public static LocalDateTime parseDateTime(String dateTime, DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static String formatDateTime(LocalDateTime ldt, DateTimeFormatter formatter) {
        if (ldt == null) {
            return "";
        }

        return ldt.format(formatter);
    }

    public static String formatDate(LocalDate ldt) {
        if (ldt == null) {
            return "";
        }

        return ldt.format(DATE_FORMATTER);
    }

    public static String formatDate(LocalDateTime dateTime) {
        return formatDate(dateTime.toLocalDate());
    }

    public static String formatDateTime(LocalDateTime ldt) {
        if (ldt == null) {
            return "";
        }

        return formatDateTime(ldt, DATE_TIME_FORMATTER);
    }

    public static Date parseDate(String datetime) {
        LocalDateTime localDateTime = parseDateTime(datetime);
        if (localDateTime != null) {
            Instant instant = localDateTime.atZone(CST).toInstant();

            return Date.from(instant);
        }

        return null;
    }

    public static LocalDate parseLocalDate(String date) {
        if (Strings.isNullOrEmpty(date)) {
            return null;
        }

        return LocalDate.parse(date, DATE_FORMATTER);
    }

    public static String formatDateTime(Date dt) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return sdf.format(dt);
    }

    public static LocalDateTime toLocalDateTime(Timestamp ts) {
        if (ts == null) {
            return null;
        }

        return ts.toLocalDateTime();
    }

    public static LocalDate convert(Timestamp ts) {
        if (ts == null) {
            return null;
        }

        return ts.toLocalDateTime().toLocalDate();
    }

    public static String longToDateStr(long longDatetime) {
        return longToDateTimeStr(longDatetime, "yyyy-MM-dd");
    }

    public static String longToDateTimeStr(long longDatetime, String pattern) {
        Date dt = new Date(longDatetime);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(dt);
    }
}
