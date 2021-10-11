package com.javabom.core.domain.soldier;

public enum SoldierStatus {
    ENABLE, DISABLE,
    ;

    public boolean isInActivate() {
        return this != ENABLE;
    }
}
