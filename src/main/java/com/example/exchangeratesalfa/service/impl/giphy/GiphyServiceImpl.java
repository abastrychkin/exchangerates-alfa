package com.example.exchangeratesalfa.service.impl.giphy;

import com.example.exchangeratesalfa.domain.giphy.GifUrl;
import com.example.exchangeratesalfa.feignclient.giphy.GiphyFeignClient;
import com.example.exchangeratesalfa.service.giphy.GiphyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiphyServiceImpl implements GiphyService {
    @Autowired
    private GiphyFeignClient giphyFeignClient;

    @Override
    public GifUrl getRandomRichGif() {
        String json = giphyFeignClient.getRandomRichGif();
        GifUrl gifUrl = getGifUrlFromJson(json);
        return gifUrl;
    }

    @Override
    public GifUrl getRandomBrokeGif() {
        String json = giphyFeignClient.getRandomBrokeGif();
        GifUrl gifUrl = getGifUrlFromJson(json);
        return gifUrl;
    }

    private GifUrl getGifUrlFromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        GifUrl gifUrl = new GifUrl("");
        try {
            jsonNode = mapper.readTree(json);
            JsonNode urlNode = jsonNode.get("data").get("images").get("fixed_height").get("url");
            String urlValue = urlNode.textValue();

            gifUrl = new GifUrl (urlValue);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return gifUrl;
    }
}
