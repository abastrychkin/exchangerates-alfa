package com.example.exchangeratesalfa.restservicesample;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Greeting {
    @Getter
    private final long id;
    @Getter
    private final String content;
}
