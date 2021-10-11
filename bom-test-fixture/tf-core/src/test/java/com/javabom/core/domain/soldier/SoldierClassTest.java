package com.javabom.core.domain.soldier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SoldierClassTest {

    @ParameterizedTest
    @CsvSource(value = {
            "PRIVATE,PRIVATE_FIRST_CLASS,false",
            "PRIVATE,CORPORAL,false",
            "PRIVATE,SERGENT,false",
            //---------------
            "PRIVATE_FIRST_CLASS,CORPORAL,false",
            "PRIVATE_FIRST_CLASS,SERGENT,false",
            //---------------
            "CORPORAL,SERGENT,false",
    })
    void isHigherGradeTest1(SoldierClass beforeRole, SoldierClass afterRole, boolean result) {
        assertThat(beforeRole.isHigherThan(afterRole)).isEqualTo(result);
        assertThat(afterRole.isHigherThan(beforeRole)).isNotEqualTo(result);
    }

    @Test
    void isHigherGradeTest2() {
        SoldierClass beforeRole = SoldierClass.PRIVATE;
        SoldierClass afterRole = SoldierClass.PRIVATE;

        assertThat(afterRole.isHigherThan(beforeRole)).isEqualTo(false);
        assertThat(beforeRole.isHigherThan(afterRole)).isEqualTo(false);
    }
}
