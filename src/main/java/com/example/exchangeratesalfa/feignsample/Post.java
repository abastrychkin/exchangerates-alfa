package com.example.exchangeratesalfa.feignsample;

import lombok.Value;

@Value
public class Post {
    Long id;
    Long userId;
    String title;
    String body;
}
