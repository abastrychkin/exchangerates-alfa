package com.example.exchangeratesalfa.controller.gifrates;



import com.example.exchangeratesalfa.common.CommonForTest;
import com.example.exchangeratesalfa.domain.giphy.GifUrlTestData;
import com.example.exchangeratesalfa.service.giphy.GiphyService;
import com.example.exchangeratesalfa.service.openexchangerates.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;


@WebMvcTest(GifRatesController.class)
public class GifRatesControllerTest {
    @MockBean
    CurrencyService currencyService;

    @MockBean
    GiphyService giphyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenCompareWithYesterdayPositive_pageShouldReturnRichGif() throws Exception {

        String currentCurrency = "EUR";
        when(currencyService.compareWithYesterday(currentCurrency)).thenReturn(1);
        when(giphyService.getRandomRichGif()).thenReturn(GifUrlTestData.richGifUrl());

        this.mockMvc.perform(get("/" + currentCurrency)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("rich.gif")));
    }

    @Test
    public void whenCompareWithYesterdayNegative_pageShouldReturnBrokeGif() throws Exception {

        String currentCurrency = "EUR";
        when(currencyService.compareWithYesterday(currentCurrency)).thenReturn(-1);
        when(giphyService.getRandomBrokeGif()).thenReturn(GifUrlTestData.brokeGifUrl());

        this.mockMvc.perform(get("/" + currentCurrency)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("broke.gif")));
    }

    @Test
    public void whenCompareWithYesterdayZero_pageShouldReturnRichGif() throws Exception {

        String currentCurrency = "EUR";
        when(currencyService.compareWithYesterday(currentCurrency)).thenReturn(0);
        when(giphyService.getRandomRichGif()).thenReturn(GifUrlTestData.richGifUrl());

        this.mockMvc.perform(get("/" + currentCurrency)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("rich.gif")));
    }
}
