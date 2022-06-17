package com.example.exchangeratesalfa.giphy.feighclient;

import org.springframework.cloud.openfeign.FeignClient;
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

