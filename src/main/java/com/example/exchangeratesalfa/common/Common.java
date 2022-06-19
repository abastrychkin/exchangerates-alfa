package com.example.exchangeratesalfa.common;

import org.springframework.beans.factory.annotation.Value;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Common {

    public static String getYesterdayFormattedString() {
        LocalDate yesterdayLocalDate = getYesterdayLocalDate();
        String formattedYesterday = yesterdayLocalDate.toString();
        return formattedYesterday;
    }

    private static LocalDate getYesterdayLocalDate(){
        LocalDate todayLocalDate = LocalDate.now();
        LocalDate yesterdayLocalDate = todayLocalDate.minusDays(1);
        return yesterdayLocalDate;
    }
}
