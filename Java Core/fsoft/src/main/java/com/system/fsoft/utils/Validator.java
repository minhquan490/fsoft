package com.system.fsoft.utils;

import java.sql.Date;
import java.time.LocalDate;

public class Validator {

    private static final String DATE_VALID = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
    private static final String EMAIL_VALID = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@(fsoft.com.vn)$";

    public static boolean isNumberic(Object o) {
        if (o instanceof Integer || o instanceof Long || o instanceof Double) {
            return true;
        } else {
            return false;
        }
    }

    public static Date checkInvalidDate(String date) {
        try {
            int year = Integer.parseInt(date.substring(date.lastIndexOf("-") - 1, date.length()));
            String[] datetimeNow = LocalDate.now().toString().split("-");
            int now = Integer.valueOf(datetimeNow[0]);
            if (date.matches(DATE_VALID) && (year > 1900 && year <= now)) {
                return Date.valueOf(date);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean checkInvalidEmail(String email) {
        try {
            if (email.matches(EMAIL_VALID)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}