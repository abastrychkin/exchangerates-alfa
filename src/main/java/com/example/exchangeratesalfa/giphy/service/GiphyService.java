package com.example.exchangeratesalfa.giphy.service;

import com.example.exchangeratesalfa.giphy.domain.GifUrl;

public interface GiphyService {
    GifUrl getRandomRichGif();
    GifUrl getRandomBrokeGif();
}
