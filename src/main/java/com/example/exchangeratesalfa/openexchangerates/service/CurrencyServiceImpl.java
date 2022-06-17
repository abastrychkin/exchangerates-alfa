package com.example.exchangeratesalfa.openexchangerates.service;

import com.example.exchangeratesalfa.openexchangerates.domain.Currency;
import com.example.exchangeratesalfa.openexchangerates.feignclient.CurrencyFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    private CurrencyFeignClient currencyFeignClient;

    @Override
    public Currency getLatestCurrency(String currentCurrency) {
        return currencyFeignClient.getLatestCurrency(currentCurrency);
    }

    @Override
    public Currency getYesterdayCurrency(String currentCurrency) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedYesterday = dateFormat.format(yesterday());

        return currencyFeignClient.getHistoricalCurrency(formattedYesterday, currentCurrency);
    }

    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public int rich(String currentCurrency) {
        //get first value from map
        double todayCourse = getLatestCurrency(currentCurrency).getRates().entrySet().iterator().next().getValue();
        double yesterdayCourse = getYesterdayCurrency(currentCurrency).getRates().entrySet().iterator().next().getValue();

        double difference = todayCourse - yesterdayCourse;

        int result = 0;
        if (difference > 0) {
            result = 1;
        } else if(difference < 0) {
            result = -1;
        }

        return result;
    }
}
