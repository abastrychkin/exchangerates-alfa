package com.example.exchangeratesalfa.common;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CommonTest {

    //IDK how to mock LocalDate in this case properly

    @Test
    public void getYesterdayFormattedStringTest() {

        LocalDate currentLocalDate = LocalDate.of(2010, 2, 12);
        try (MockedStatic<LocalDate> topDateTimeUtilMock = Mockito.mockStatic(LocalDate.class)) {
            topDateTimeUtilMock.when(() -> LocalDate.now()).thenReturn(currentLocalDate);
            String yesterdayFormattedString = Common.getYesterdayFormattedString();
            topDateTimeUtilMock.verify(()->LocalDate.now());
        }
    }

}
