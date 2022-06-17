package com.example.exchangeratesalfa.giphy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
