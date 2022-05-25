package com.system.fsoft.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public class Validator {

    private static final String DATE_VALID = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
    private static final String EMAIL_VALID = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@(fsoft.com.vn)$";
    private static final String PHONE_VALID = "^[0-9]{10}$";
    private static final String GRADUATION_RANK_VALID = "^[ABCD]{1}[+-]?$";

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
            return Date.valueOf(LocalDate.now());
        }
    }

    public static String checkInvalidEmail(String email) {
        try {
            if (email.matches(EMAIL_VALID)) {
                return email;
            } else {
                StringBuilder builder = new StringBuilder(UUID.randomUUID().toString());
                return builder.append("@fsoft.com.vn").toString();
            }
        } catch (Exception e) {
            StringBuilder builder = new StringBuilder(UUID.randomUUID().toString());
            return builder.append("@fsoft.com.vn").toString();
        }
    }

    public static int checkInputInt(String in) {
        try {
            int result = Integer.parseInt(in.trim());
            return result;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String checkPhone(String phone) {
        try {
            if (phone.matches(PHONE_VALID)) {
                return phone;
            } else {
                return "0000000000";
            }
        } catch (Exception e) {
            return "0000000000";
        }
    }

    public static String checkGraduationRank(String rank) {
        try {
            if (rank.matches(GRADUATION_RANK_VALID)) {
                return rank;
            } else {
                return "D-";
            }
        } catch (Exception e) {
            return "D-";
        }
    }
}