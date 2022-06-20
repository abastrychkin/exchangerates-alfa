package com.example.exchangeratesalfa.domain.openexchangerates;

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
