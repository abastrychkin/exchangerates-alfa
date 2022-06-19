package com.example.exchangeratesalfa.openexchangerates.service;


import com.example.exchangeratesalfa.openexchangerates.domain.Currency;

public interface CurrencyService {
    Currency getLatestCurrency(String currentCurrency);
    Currency getYesterdayCurrency(String currentCurrency);

    //if rich return positive
    //if broke return negative
    //if not changed return 0
    int compareWithYesterday(String currentCurrency);
}
