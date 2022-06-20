package com.example.exchangeratesalfa.service.giphy;

import com.example.exchangeratesalfa.domain.giphy.GifUrl;

public interface GiphyService {
    GifUrl getRandomRichGif();
    GifUrl getRandomBrokeGif();
}
