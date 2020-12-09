package com.javabom.bomconverter.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class StringToLocalDateTimeConverter2Test {

    @DisplayName("문자열을 날짜값으로 변환하기 중간에 T 문자가 추가되었다.")
    @Test
    void convert() {
        //given
        StringToLocalDateTimeConverter2 stringToLocalDateTimeConverter2 = new StringToLocalDateTimeConverter2();
        String stringDate = "2020-12-08T12:22:33";

        //when
        LocalDateTime localDateTime = stringToLocalDateTimeConverter2.convert(stringDate);

        //then
        assertAll(
                "년 월 일",
                () -> assertThat(localDateTime.getYear()).isEqualTo(2020),
                () -> assertThat(localDateTime.getMonthValue()).isEqualTo(12),
                () -> assertThat(localDateTime.getDayOfMonth()).isEqualTo(8)
        );

        assertAll(
                "시 분 초",
                () -> assertThat(localDateTime.getHour()).isEqualTo(12),
                () -> assertThat(localDateTime.getMinute()).isEqualTo(22),
                () -> assertThat(localDateTime.getSecond()).isEqualTo(33)
        );
    }
}