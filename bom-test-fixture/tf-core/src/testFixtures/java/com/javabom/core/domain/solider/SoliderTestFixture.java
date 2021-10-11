package com.javabom.core.domain.solider;

import com.javabom.core.domain.soldier.Soldier;
import com.javabom.core.domain.soldier.SoldierClass;
import com.javabom.core.domain.soldier.SoldierStatus;

import java.time.LocalDateTime;

public class SoliderTestFixture {
    public static Soldier createSolider(String name, SoldierClass soldierClass, SoldierStatus status) {
        LocalDateTime now = LocalDateTime.now();
        return Soldier.builder()
                .name(name)
                .soldierClass(soldierClass)
                .status(status)
                .joinDateTime(now)
                .modifiedDateTime(now)
                .build();
    }
}
