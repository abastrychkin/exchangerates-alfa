package com.example.exchangeratesalfa.feignclient.giphy;

import com.example.exchangeratesalfa.common.CommonForTest;
import com.example.exchangeratesalfa.feignclient.giphy.GiphyFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9092)
public class GiphyFeignClientTest {
    private final CommonForTest commonForTest = new CommonForTest();

    @Autowired
    GiphyFeignClient giphyFeignClient;

    @Value("${giphyApi.appId}")
    private String appId;

    @Test
    public void getRandomRichGif_whenValidClient_returnValidResponse() throws Exception {

        String expectedUrlNode = "\"url\": \"https://media3.giphy.com/media/FQfNdTjV4toFNvAgvS/200.gif?cid=bb0e413b57ecb175c0992a3319d6fcbd1af1649214cacd29&rid=200.gif&ct=g\"";

        // Using WireMock to mock client API:
        stubFor(get(urlEqualTo("/random"
                + "?api_key=" + appId
                + "&tag=" + "rich"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(commonForTest.read("stubs/giphy/rich.json"))));

        String jsonResponse = giphyFeignClient.getRandomRichGif();

        // We're asserting if WireMock responded properly
        assertThat(jsonResponse).contains("\"data\": {");
        assertThat(jsonResponse).contains("\"images\": {");
        assertThat(jsonResponse).contains("\"fixed_height\": {");
        assertThat(jsonResponse).contains(expectedUrlNode);

    }

    @Test
    public void getRandomBrokeGif_whenValidClient_returnValidResponse() throws Exception {
        String expectedUrlNode = "\"url\": \"https://media0.giphy.com/media/iDV0F7qLO7620MhVTX/200.gif?cid=bb0e413b5f204b7665a42be1b470bcad56b5275a2ae2b729&rid=200.gif&ct=g\"";

        // Using WireMock to mock client API:
        stubFor(get(urlEqualTo("/random"
                + "?api_key=" + appId
                + "&tag=" + "broke"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(commonForTest.read("stubs/giphy/broke.json"))));

        String jsonResponse = giphyFeignClient.getRandomBrokeGif();

        // We're asserting if WireMock responded properly
        assertThat(jsonResponse).contains("\"data\": {");
        assertThat(jsonResponse).contains("\"images\": {");
        assertThat(jsonResponse).contains("\"fixed_height\": {");
        assertThat(jsonResponse).contains(expectedUrlNode);

    }
}
