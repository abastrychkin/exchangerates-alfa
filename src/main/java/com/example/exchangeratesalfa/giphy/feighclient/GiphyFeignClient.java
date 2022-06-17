package com.example.exchangeratesalfa.giphy.feighclient;

import com.example.exchangeratesalfa.giphy.GifUrl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "giphyFeignClient", url = "${giphyApi.baseUrl}")
public interface GiphyFeignClient {

    @GetMapping("/random"
            + "?api_key=" + "${giphyApi.appId}"
            + "&tag=" + "rich")
    String getRandomRichGif();

    @GetMapping("/random"
            + "?api_key=" + "${giphyApi.appId}"
            + "&tag=" + "broke")
    String getRandomBrokeGif();
}

