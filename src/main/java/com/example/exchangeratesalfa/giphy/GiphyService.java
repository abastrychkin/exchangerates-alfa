package com.example.exchangeratesalfa.giphy;

import org.springframework.http.ResponseEntity;

public interface GiphyService {
    GifUrl getRandomRichGif();
    GifUrl getRandomBrokeGif();
}
