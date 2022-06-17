package com.example.exchangeratesalfa.openexchangerates.controller;

import com.example.exchangeratesalfa.openexchangerates.domain.Currency;
import com.example.exchangeratesalfa.openexchangerates.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/{currency}")
    public Currency getLatestCurrency(@PathVariable String currency) {
        return currencyService.getLatestCurrency(currency);
    }

    @GetMapping("/yesterday/{currency}")
    public Currency getYesterdayCurrency(@PathVariable String currency) {
        return currencyService.getYesterdayCurrency(currency);
    }



}
