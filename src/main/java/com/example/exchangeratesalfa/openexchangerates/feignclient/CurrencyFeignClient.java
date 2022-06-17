package com.example.exchangeratesalfa.openexchangerates.feignclient;

import com.example.exchangeratesalfa.feignsample.Post;
import com.example.exchangeratesalfa.openexchangerates.domain.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "currencyFeignClient", url = "${openexchangeratesApi.baseUrl}")
public interface CurrencyFeignClient {

    @GetMapping("/latest.json"
            + "?app_id=" + "${openexchangeratesApi.appId}"
            + "&base=" + "${openexchangeratesApi.baseCurrency}"
            + "&symbols={currentCurrency}")
    Currency getLatestCurrency(@PathVariable String currentCurrency);

    @GetMapping("/historical/{dateYYYYMMDD}.json"
            + "?app_id=" + "${openexchangeratesApi.appId}"
            + "&base=" + "${openexchangeratesApi.baseCurrency}"
            + "&symbols={currentCurrency}")
    Currency getHistoricalCurrency(@PathVariable String dateYYYYMMDD, @PathVariable String currentCurrency);
}
