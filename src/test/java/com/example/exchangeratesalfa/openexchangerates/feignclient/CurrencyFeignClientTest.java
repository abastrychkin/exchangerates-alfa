package com.example.exchangeratesalfa.openexchangerates.feignclient;

import com.example.exchangeratesalfa.common.Common;
import com.example.exchangeratesalfa.common.CommonForTest;
import com.example.exchangeratesalfa.openexchangerates.domain.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9091)
public class CurrencyFeignClientTest {
    private final CommonForTest commonForTest = new CommonForTest();
    @Autowired
    CurrencyFeignClient currencyFeignClient;

    @Value("${openexchangeratesApi.appId}")
    private String appId;

    @Value("${openexchangeratesApi.baseCurrency}")
    private String baseCurrency;

    @Test
    public void getLatestCurrency_whenValidClient_returnValidResponse() throws Exception {
        String testCurrency = "EUR";

        // Using WireMock to mock client API:
        stubFor(get(urlEqualTo("/latest.json"
                + "?app_id=" + appId
                + "&base=" + baseCurrency
                + "&symbols=" + testCurrency))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(commonForTest.read("stubs/openexchangerates/latest.json"))));

        Currency latestCurrency = currencyFeignClient.getLatestCurrency(testCurrency);

        // We're asserting if WireMock responded properly
        assertThat(latestCurrency.getDisclaimer()).isEqualTo("Usage subject to terms: https://openexchangerates.org/terms");
        assertThat(latestCurrency.getLicense()).isEqualTo("https://openexchangerates.org/license");
        assertThat(latestCurrency.getTimestamp()).isEqualTo(1655553609);
        assertThat(latestCurrency.getBase()).isEqualTo("USD");
        assertThat(latestCurrency.getRates().entrySet().iterator().next().getKey()).isEqualTo("EUR");
        assertThat(latestCurrency.getRates().entrySet().iterator().next().getValue()).isEqualTo(0.952744);
    }

    @Test
    public void getHistoricalCurrency_whenValidClient_returnValidResponse() throws Exception {
        String testCurrency = "EUR";

        String yesterdayFormattedString = Common.getYesterdayFormattedString();

        // Using WireMock to mock client API:
        stubFor(get(urlEqualTo("/historical/"+ yesterdayFormattedString +".json"
                + "?app_id=" + appId
                + "&base=" + baseCurrency
                + "&symbols=" + testCurrency))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(commonForTest.read("stubs/openexchangerates/historical.json"))));

        Currency latestCurrency = currencyFeignClient.getHistoricalCurrency(yesterdayFormattedString,testCurrency);

        // We're asserting if WireMock responded properly
        assertThat(latestCurrency.getDisclaimer()).isEqualTo("Usage subject to terms: https://openexchangerates.org/terms");
        assertThat(latestCurrency.getLicense()).isEqualTo("https://openexchangerates.org/license");
        assertThat(latestCurrency.getTimestamp()).isEqualTo(1655510394);
        assertThat(latestCurrency.getBase()).isEqualTo("USD");
        assertThat(latestCurrency.getRates().entrySet().iterator().next().getKey()).isEqualTo("EUR");
        assertThat(latestCurrency.getRates().entrySet().iterator().next().getValue()).isEqualTo(0.952472);
    }





}
