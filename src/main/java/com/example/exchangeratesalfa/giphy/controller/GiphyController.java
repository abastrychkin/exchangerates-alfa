package com.example.exchangeratesalfa.giphy.controller;

import com.example.exchangeratesalfa.giphy.service.GiphyService;
import com.example.exchangeratesalfa.giphy.domain.GifUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/giphy")
public class GiphyController {
    @Autowired
    private GiphyService giphyService;

    @GetMapping("/rich")
    public GifUrl getRandomRichGif() {

        GifUrl result = giphyService.getRandomRichGif();
        return result;
    }

    @GetMapping("/broke")
    public GifUrl getRandomBrokeGif() {
        return giphyService.getRandomBrokeGif();
    }
}
