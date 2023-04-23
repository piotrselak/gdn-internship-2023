package com.github.piotrselak.gdninternship2023.nbp.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator {
    private final static String dateFormat = "yyyy-mm-dd";

    public static boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}