package com.example.exchangeratesalfa.openexchangerates.service;

import com.example.exchangeratesalfa.common.Common;
import com.example.exchangeratesalfa.openexchangerates.domain.Currency;
import com.example.exchangeratesalfa.openexchangerates.domain.CurrencyTestData;
import com.example.exchangeratesalfa.openexchangerates.feignclient.CurrencyFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CurrencyServiceTest {
    @MockBean
    private CurrencyFeignClient currencyFeignClient;

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void getLatestCurrency_whenValidClientResponse_returnCurrency() {
        String testCurrency = "EUR";

        when(currencyFeignClient.getLatestCurrency(testCurrency)).thenReturn(CurrencyTestData.currency());

        Currency currency = currencyService.getLatestCurrency(testCurrency);

        assertThat(currency.getDisclaimer()).isEqualTo("disclaimer");
        assertThat(currency.getLicense()).isEqualTo("licence");
        assertThat(currency.getTimestamp()).isEqualTo(2L);
        assertThat(currency.getBase()).isEqualTo("USD");
        assertThat(currency.getRates().entrySet().iterator().next().getKey()).isEqualTo("EUR");
        assertThat(currency.getRates().entrySet().iterator().next().getValue()).isEqualTo(1.0);
    }

    @Test
    public void getYesterdayCurrency_whenValidClientResponse_returnCurrency() {
        String testCurrency = "EUR";
        String yesterdayFormattedStringDate = Common.getYesterdayFormattedString();

        when(currencyFeignClient.getHistoricalCurrency(yesterdayFormattedStringDate,testCurrency)).thenReturn(CurrencyTestData.yesterdayCurrency());

        Currency currency = currencyService.getYesterdayCurrency(testCurrency);

        assertThat(currency.getDisclaimer()).isEqualTo("disclaimer");
        assertThat(currency.getLicense()).isEqualTo("licence");
        assertThat(currency.getTimestamp()).isEqualTo(1L);
        assertThat(currency.getBase()).isEqualTo("USD");
        assertThat(currency.getRates().entrySet().iterator().next().getKey()).isEqualTo("EUR");
        assertThat(currency.getRates().entrySet().iterator().next().getValue()).isEqualTo(2.0);
    }

    @Test
    public void richTest() {

        String testCurrency = "EUR";
        String yesterdayFormattedStringDate = Common.getYesterdayFormattedString();

        when(currencyFeignClient.getLatestCurrency(testCurrency)).thenReturn(CurrencyTestData.currency());
        when(currencyFeignClient.getHistoricalCurrency(yesterdayFormattedStringDate,testCurrency)).thenReturn(CurrencyTestData.yesterdayCurrency());

        assertThat(currencyService.rich(testCurrency)).isEqualTo(1);

        when(currencyFeignClient.getLatestCurrency(testCurrency)).thenReturn(CurrencyTestData.yesterdayCurrency());
        when(currencyFeignClient.getHistoricalCurrency(yesterdayFormattedStringDate,testCurrency)).thenReturn(CurrencyTestData.currency());

        assertThat(currencyService.rich(testCurrency)).isEqualTo(-1);

        when(currencyFeignClient.getLatestCurrency(testCurrency)).thenReturn(CurrencyTestData.currency());
        when(currencyFeignClient.getHistoricalCurrency(yesterdayFormattedStringDate,testCurrency)).thenReturn(CurrencyTestData.currency());

        assertThat(currencyService.rich(testCurrency)).isEqualTo(0);

    }
}
