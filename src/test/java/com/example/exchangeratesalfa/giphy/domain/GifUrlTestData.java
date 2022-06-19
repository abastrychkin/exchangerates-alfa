package com.example.exchangeratesalfa.giphy.domain;

import com.example.exchangeratesalfa.common.CommonForTest;

import java.io.IOException;

public class GifUrlTestData {
    private final CommonForTest commonForTest = new CommonForTest();

    public static GifUrl richGifUrl(){
        return new GifUrl("rich.gif");
    }

    public static GifUrl brokeGifUrl(){
        return new GifUrl("broke.gif");
    }

    public String richJson() throws IOException {
        return commonForTest.read("stubs/giphy/rich.json");
    }

    public String brokeJson() throws IOException {
        return commonForTest.read("stubs/giphy/broke.json");
    }

}
