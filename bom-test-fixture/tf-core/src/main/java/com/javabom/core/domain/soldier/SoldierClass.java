package com.javabom.core.domain.soldier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SoldierClass {
    PRIVATE(1),
    PRIVATE_FIRST_CLASS(2),
    CORPORAL(3),
    SERGENT(4),
    ;

    private final Integer grade;

    public boolean isHigherThan(SoldierClass other) {
        return this.grade.compareTo(other.grade) > 0;
    }
}
