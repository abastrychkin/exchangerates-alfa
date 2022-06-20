package com.example.exchangeratesalfa.service.openexchangerates;


import com.example.exchangeratesalfa.domain.openexchangerates.Currency;

public interface CurrencyService {
    Currency getLatestCurrency(String currentCurrency);
    Currency getYesterdayCurrency(String currentCurrency);

    //if rich return positive
    //if broke return negative
    //if not changed return 0
    int compareWithYesterday(String currentCurrency);
}
