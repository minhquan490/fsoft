package com.system.fsoft.utils;

import java.sql.Date;

public class Validator {

    private static final String DATE_VALID = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";

    public static boolean isNumberic(Object o) {
        if (o instanceof Integer || o instanceof Long || o instanceof Double) {
            return true;
        } else {
            return false;
        }
    }

    public static Date convertStringToDate(String date) {
        if (date.matches(DATE_VALID)) {
            return Date.valueOf(date);
        }
        return null;
    }

}