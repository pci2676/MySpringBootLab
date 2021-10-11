package com.javabom.core.domain.soldier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SoldierTest {

    private Soldier createSolider(String name, SoldierClass soldierClass, SoldierStatus status) {
        LocalDateTime now = LocalDateTime.now();
        return Soldier.builder()
                .name(name)
                .soldierClass(soldierClass)
                .status(status)
                .joinDateTime(now)
                .modifiedDateTime(now)
                .build();
    }

    @DisplayName("활성화 된 병사는 낮은 등급에서 높은 등급으로 진급이 가능하다.")
    @Test
    void promotionTest1() {
        //given
        Soldier soldier = createSolider("name1", SoldierClass.PRIVATE, SoldierStatus.ENABLE);

        //when
        Soldier promotedSoldier = soldier.promote(SoldierClass.PRIVATE_FIRST_CLASS);

        //then
        assertThat(promotedSoldier.getSoldierClass()).isEqualTo(SoldierClass.PRIVATE_FIRST_CLASS);
    }

    @DisplayName("활성화 된 병사는 높은 등급에서 낮은 등급으로 진급이 불가능하다.")
    @Test
    void promotionTest2() {
        //given
        Soldier soldier = createSolider("name2", SoldierClass.PRIVATE_FIRST_CLASS, SoldierStatus.ENABLE);

        //when
        //then
        assertThatThrownBy(() -> soldier.promote(SoldierClass.PRIVATE));
    }

    @DisplayName("비활성화 된 병사는 진급이 불가능하다.")
    @Test
    void promotionTest3() {
        //given
        Soldier soldier = createSolider("name3", SoldierClass.PRIVATE_FIRST_CLASS, SoldierStatus.DISABLE);

        //when
        //then
        assertThatThrownBy(() -> soldier.promote(SoldierClass.CORPORAL));
    }
}
