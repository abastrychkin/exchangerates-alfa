package com.example.exchangeratesalfa.common;

import org.springframework.core.io.ClassPathResource;
import wiremock.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CommonForTest {
    public CommonForTest() {
    }

    public String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }
}