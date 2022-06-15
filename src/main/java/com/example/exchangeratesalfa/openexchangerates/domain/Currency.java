package com.example.exchangeratesalfa.openexchangerates.domain;

import lombok.Value;
import java.util.Map;

@Value
public class Currency {
    String disclaimer;
    String license;
    Long timestamp;
    String base;
    Map<String, Double> rates;
}
