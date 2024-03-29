package com.example.exchangeratesalfa.service.impl.openexchangerates;

import com.example.exchangeratesalfa.common.Common;
import com.example.exchangeratesalfa.domain.openexchangerates.Currency;
import com.example.exchangeratesalfa.feignclient.openexchangerates.CurrencyFeignClient;
import com.example.exchangeratesalfa.service.openexchangerates.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        String formattedYesterday = Common.getYesterdayFormattedString();

        return currencyFeignClient.getHistoricalCurrency(formattedYesterday, currentCurrency);
    }



    public int compareWithYesterday(String currentCurrency) {
        //get first value from map
        double todayCourseUSDtoCurrency = getLatestCurrency(currentCurrency).getRates().entrySet().iterator().next().getValue();
        double yesterdayCourseUSDtoCurrency = getYesterdayCurrency(currentCurrency).getRates().entrySet().iterator().next().getValue();

        double todayCourseCurrencyToUSD = 1. / todayCourseUSDtoCurrency;
        double yesterdayCourseCurrencyToUSD = 1. / yesterdayCourseUSDtoCurrency;

        double difference = todayCourseCurrencyToUSD - yesterdayCourseCurrencyToUSD;

        int result = 0;
        if (difference > 0) {
            result = 1;
        } else if(difference < 0) {
            result = -1;
        }

        return result;
    }
}
