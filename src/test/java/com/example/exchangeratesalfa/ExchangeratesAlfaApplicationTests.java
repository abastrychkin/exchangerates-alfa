package com.example.exchangeratesalfa;

import com.example.exchangeratesalfa.controller.gifrates.GifRatesController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ExchangeratesAlfaApplicationTests {

	@Autowired
	private GifRatesController gifRatesController;
	@Test
	void contextLoads() {
		assertThat(gifRatesController).isNotNull();
	}

}
