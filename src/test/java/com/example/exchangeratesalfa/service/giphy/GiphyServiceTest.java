package com.example.exchangeratesalfa.service.giphy;

import com.example.exchangeratesalfa.domain.giphy.GifUrl;
import com.example.exchangeratesalfa.domain.giphy.GifUrlTestData;
import com.example.exchangeratesalfa.feignclient.giphy.GiphyFeignClient;
import com.example.exchangeratesalfa.service.giphy.GiphyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GiphyServiceTest {
    @MockBean
    private GiphyFeignClient giphyFeignClient;

    @Autowired
    private GiphyService giphyService;

    @Test
    public void getRandomRichGif_whenValidClientResponse_returnGifUrl () throws Exception {
        GifUrlTestData gifUrlTestData = new GifUrlTestData();
        when(giphyFeignClient.getRandomRichGif()).thenReturn(gifUrlTestData.richJson());
        GifUrl gifUrl = giphyService.getRandomRichGif();
        assertThat(gifUrl.getUrl()).isEqualTo("https://media3.giphy.com/media/FQfNdTjV4toFNvAgvS/200.gif?cid=bb0e413b57ecb175c0992a3319d6fcbd1af1649214cacd29&rid=200.gif&ct=g");
    }

    @Test
    public void getRandomBrokeGif_whenValidClientResponse_returnGifUrl () throws Exception {
        GifUrlTestData gifUrlTestData = new GifUrlTestData();
        when(giphyFeignClient.getRandomBrokeGif()).thenReturn(gifUrlTestData.brokeJson());
        GifUrl gifUrl = giphyService.getRandomBrokeGif();
        assertThat(gifUrl.getUrl()).isEqualTo("https://media0.giphy.com/media/iDV0F7qLO7620MhVTX/200.gif?cid=bb0e413b5f204b7665a42be1b470bcad56b5275a2ae2b729&rid=200.gif&ct=g");
    }

}
