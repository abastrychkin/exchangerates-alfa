package com.example.exchangeratesalfa.giphy.controller;


import com.example.exchangeratesalfa.giphy.service.GiphyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/giphygif")
public class GiphyGifController {
    @Autowired
    private GiphyService giphyService;

    @GetMapping("/rich")
    public String gifRichTemplate(Model model) {
        String gifUrl = giphyService.getRandomRichGif().getUrl();
        model.addAttribute("gifUrl", gifUrl);
        return "giftemplate";
    }

    @GetMapping("/broke")
    public String gifBrokeTemplate(Model model) {
        String gifUrl = giphyService.getRandomBrokeGif().getUrl();
        model.addAttribute("gifUrl", gifUrl);
        return "giftemplate";
    }
}
