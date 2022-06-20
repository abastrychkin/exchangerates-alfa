package com.example.exchangeratesalfa.service.openexchangerates;

import com.example.exchangeratesalfa.common.Common;
import com.example.exchangeratesalfa.domain.openexchangerates.Currency;
import com.example.exchangeratesalfa.domain.openexchangerates.CurrencyTestData;
import com.example.exchangeratesalfa.feignclient.openexchangerates.CurrencyFeignClient;
import com.example.exchangeratesalfa.service.openexchangerates.CurrencyService;
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
        assertThat(currency.getLicense()).isEqualTo("license");
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
        assertThat(currency.getLicense()).isEqualTo("license");
        assertThat(currency.getTimestamp()).isEqualTo(1L);
        assertThat(currency.getBase()).isEqualTo("USD");
        assertThat(currency.getRates().entrySet().iterator().next().getKey()).isEqualTo("EUR");
        assertThat(currency.getRates().entrySet().iterator().next().getValue()).isEqualTo(2.0);
    }

    @Test
    public void compareWithYesterdayTest_whenGreaterYesterday_returnPositive() {

        String testCurrency = "EUR";
        String yesterdayFormattedStringDate = Common.getYesterdayFormattedString();

        when(currencyFeignClient.getLatestCurrency(testCurrency)).thenReturn(CurrencyTestData.currency());
        when(currencyFeignClient.getHistoricalCurrency(yesterdayFormattedStringDate,testCurrency)).thenReturn(CurrencyTestData.yesterdayCurrency());

        assertThat(currencyService.compareWithYesterday(testCurrency)).isEqualTo(1);

    }

    @Test
    public void compareWithYesterdayTest_whenGreaterLatest_returnNegative() {
        String testCurrency = "EUR";
        String yesterdayFormattedStringDate = Common.getYesterdayFormattedString();

        when(currencyFeignClient.getLatestCurrency(testCurrency)).thenReturn(CurrencyTestData.yesterdayCurrency());
        when(currencyFeignClient.getHistoricalCurrency(yesterdayFormattedStringDate, testCurrency)).thenReturn(CurrencyTestData.currency());

        assertThat(currencyService.compareWithYesterday(testCurrency)).isEqualTo(-1);
    }

    @Test
    public void compareWithYesterdayTest_whenLatestAndYesterdayEqual_returnZero() {
        String testCurrency = "EUR";
        String yesterdayFormattedStringDate = Common.getYesterdayFormattedString();

        when(currencyFeignClient.getLatestCurrency(testCurrency)).thenReturn(CurrencyTestData.currency());
        when(currencyFeignClient.getHistoricalCurrency(yesterdayFormattedStringDate, testCurrency)).thenReturn(CurrencyTestData.currency());

        assertThat(currencyService.compareWithYesterday(testCurrency)).isEqualTo(0);
    }


}
