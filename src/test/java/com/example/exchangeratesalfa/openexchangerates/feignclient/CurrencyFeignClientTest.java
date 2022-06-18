package com.example.exchangeratesalfa.openexchangerates.feignclient;

import com.example.exchangeratesalfa.openexchangerates.domain.Currency;
import com.example.exchangeratesalfa.openexchangerates.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import wiremock.org.apache.commons.io.IOUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9091)
public class CurrencyFeignClientTest {
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
                        .withBody(read("stubs/openexchangerates/latest.json"))));

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

        String yesterdayFormattedString = getYesterdayFormattedString();

        // Using WireMock to mock client API:
        stubFor(get(urlEqualTo("/historical/"+ yesterdayFormattedString +".json"
                + "?app_id=" + appId
                + "&base=" + baseCurrency
                + "&symbols=" + testCurrency))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read("stubs/openexchangerates/historical.json"))));

        Currency latestCurrency = currencyFeignClient.getHistoricalCurrency(yesterdayFormattedString,testCurrency);

        // We're asserting if WireMock responded properly
        assertThat(latestCurrency.getDisclaimer()).isEqualTo("Usage subject to terms: https://openexchangerates.org/terms");
        assertThat(latestCurrency.getLicense()).isEqualTo("https://openexchangerates.org/license");
        assertThat(latestCurrency.getTimestamp()).isEqualTo(1655510394);
        assertThat(latestCurrency.getBase()).isEqualTo("USD");
        assertThat(latestCurrency.getRates().entrySet().iterator().next().getKey()).isEqualTo("EUR");
        assertThat(latestCurrency.getRates().entrySet().iterator().next().getValue()).isEqualTo(0.952472);
    }

    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }

    private String getYesterdayFormattedString() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedYesterday = dateFormat.format(yesterday);

        return formattedYesterday;
    }

}
