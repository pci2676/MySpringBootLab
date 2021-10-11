package com.javabom.core.domain.soldier;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SoldierStatusTest {

    @ParameterizedTest
    @CsvSource(value = {
            "ENABLE,false",
            "DISABLE,true"
    })
    void isInActivateTest(SoldierStatus status, boolean result) {
        assertThat(status.isInActivate()).isEqualTo(result);
    }
}
