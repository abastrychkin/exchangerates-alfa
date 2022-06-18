package com.example.exchangeratesalfa.common;

import org.springframework.beans.factory.annotation.Value;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Common {
     private static final String DATE_FORMAT_STRING = "yyyy-MM-dd";

    public static String getYesterdayFormattedString() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();

        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
        String formattedYesterday = dateFormat.format(yesterday);

        return formattedYesterday;
    }
}
