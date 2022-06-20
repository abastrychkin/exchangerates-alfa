package com.example.exchangeratesalfa.controller.gifrates;

import com.example.exchangeratesalfa.service.giphy.GiphyService;
import com.example.exchangeratesalfa.service.openexchangerates.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class GifRatesController {
    @Autowired
    CurrencyService currencyService;

    @Autowired
    GiphyService giphyService;

    @GetMapping("{currency}")
    public String getGifTemplate(Model model, @PathVariable String currency) {
        int compareWithYesterdayResult = currencyService.compareWithYesterday(currency);
        String gifUrl = "";

        if (compareWithYesterdayResult >= 0) {
            gifUrl = giphyService.getRandomRichGif().getUrl();
        } else {
            gifUrl = giphyService.getRandomBrokeGif().getUrl();
        }

        model.addAttribute("gifUrl", gifUrl);
        return "giftemplate";
    }
}
