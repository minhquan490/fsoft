package com.system.fsoft.utils;

import java.sql.Date;

import com.system.fsoft.exception.BirthDateException;
import com.system.fsoft.exception.EmailException;
import com.system.fsoft.exception.PhoneException;

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
        if (!date.matches(DATE_VALID)) {
            throw new BirthDateException("You enter wrong format ! Birth day default will be apply");
        }
        String[] dateComponent = date.split("-");
        int year = Integer.parseInt(dateComponent[0]);
        int month = Integer.parseInt(dateComponent[1]);
        int day = Integer.parseInt(dateComponent[2]);

        if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                && day > 31) {
            throw new BirthDateException(
                    "You enter wrong " + month + " can not have " + day + " day. Default birth date will be apply");
        }
        if ((month == 2 || month == 4 || month == 6 || month == 9 || month == 11) && day >= 31) {
            throw new BirthDateException(
                    "You enter wrong " + month + " can not have " + day + " day. Default birth date will be apply");
        }
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            if (month == 2 && day > 29) {
                throw new BirthDateException(
                        "You enter wrong " + month + " can not have " + day + " day beacause " + year
                                + " is a leap year" + ". Default birth date will be apply");
            }
        }
        return Date.valueOf(date);
    }

    public static String checkInvalidEmail(String email) {
        if (!email.matches(EMAIL_VALID)) {
            throw new EmailException("You enter wrong email format ! Email default will be apply");
        }
        return email;
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
        if (!phone.matches(PHONE_VALID)) {
            throw new PhoneException("You enter wrong phone format ! The default phone will be apply");
        }
        return phone;
    }

    public static String checkGraduationRank(String rank) {
        if (!rank.matches(GRADUATION_RANK_VALID)) {
            throw new BirthDateException("Unknow rank ! Default value will be apply");
        }
        return rank;
    }
}