package com.example.exchangeratesalfa.domain.giphy;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Value;

@Value
public class GifUrl {
    private String url;
}
