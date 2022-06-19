package com.example.exchangeratesalfa.showcase;

import com.example.exchangeratesalfa.giphy.service.GiphyService;
import com.example.exchangeratesalfa.openexchangerates.service.CurrencyService;
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
        int rich = currencyService.compareWithYesterday(currency);
        String gifUrl = "";

        if (rich >= 0) {
            gifUrl = giphyService.getRandomRichGif().getUrl();
        } else {
            gifUrl = giphyService.getRandomBrokeGif().getUrl();
        }

        model.addAttribute("gifUrl", gifUrl);
        return "giftemplate";
    }
}
