package com.example.exchangeratesalfa.domain.giphy;

import com.example.exchangeratesalfa.common.CommonForTest;
import com.example.exchangeratesalfa.domain.giphy.GifUrl;

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
