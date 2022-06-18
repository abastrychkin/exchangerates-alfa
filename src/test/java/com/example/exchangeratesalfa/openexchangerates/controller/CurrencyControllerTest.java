package com.example.exchangeratesalfa.openexchangerates.controller;

import com.example.exchangeratesalfa.openexchangerates.domain.CurrencyTestData;
import com.example.exchangeratesalfa.openexchangerates.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyService;

    @Test
    public void getLatestCurrency_whenValidRequest_returnsValidResponse() throws Exception {
        String testCurrency = "EUR";

        when(currencyService.getLatestCurrency(testCurrency)).thenReturn(CurrencyTestData.currency());

        mockMvc.perform(get("/currency/" + testCurrency))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.disclaimer", equalTo("disclaimer")))
                .andExpect(jsonPath("$.license", equalTo("license")))
                .andExpect(jsonPath("$.timestamp", equalTo(2)))
                .andExpect(jsonPath("$.base", equalTo("USD")))
                .andExpect(jsonPath("$.rates.EUR", equalTo(1.0)));
    }

    @Test
    public void getYesterdayCurrency_whenValidRequest_returnsValidResponse() throws Exception {
        String testCurrency = "EUR";

        when(currencyService.getYesterdayCurrency(testCurrency)).thenReturn(CurrencyTestData.yesterdayCurrency());

        mockMvc.perform(get("/currency/yesterday/" + testCurrency))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.disclaimer", equalTo("disclaimer")))
                .andExpect(jsonPath("$.license", equalTo("license")))
                .andExpect(jsonPath("$.timestamp", equalTo(1)))
                .andExpect(jsonPath("$.base", equalTo("USD")))
                .andExpect(jsonPath("$.rates.EUR", equalTo(2.0)));
    }
}
