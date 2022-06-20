package com.example.exchangeratesalfa.domain.openexchangerates;

import com.example.exchangeratesalfa.domain.openexchangerates.Currency;

import java.util.HashMap;
import java.util.Map;

public class CurrencyTestData {

    public static Currency currency(){
        Map<String, Double> rates = new HashMap<String, Double>();
        rates.put("EUR", 1.0);
        return new Currency("disclaimer",
                "license",
                2L,
                "USD",
                rates);
    }

    public static Currency yesterdayCurrency(){
        Map<String, Double> rates = new HashMap<String, Double>();
        rates.put("EUR", 2.0);
        return new Currency("disclaimer",
                "license",
                1L,
                "USD",
                rates);
    }
}
