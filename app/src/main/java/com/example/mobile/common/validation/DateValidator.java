package com.example.mobile.common.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {

    public static boolean validate(String dateStr) {
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date date = df.parse(dateStr);
            return df.format(date).equals(dateStr);
        } catch (ParseException e) {
            return false;
        }
    }
}
