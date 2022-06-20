package com.example.exchangeratesalfa.common;


import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


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
