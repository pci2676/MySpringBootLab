package com.javabom.application.domain.soldier.dto;

import com.javabom.core.domain.soldier.Soldier;
import com.javabom.core.domain.soldier.SoldierClass;
import com.javabom.core.domain.soldier.SoldierStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SoldierDto {
    private final Long id;
    private final String name;
    private final SoldierClass soldierClass;
    private final SoldierStatus status;
    private final LocalDateTime joinDateTime;
    private final LocalDateTime modifiedDateTime;

    public static SoldierDto of(Soldier soldier) {
        return SoldierDto.builder()
                .id(soldier.getId())
                .name(soldier.getName())
                .soldierClass(soldier.getSoldierClass())
                .status(soldier.getStatus())
                .joinDateTime(soldier.getJoinDateTime())
                .modifiedDateTime(soldier.getModifiedDateTime())
                .build();
    }

    @Builder
    public SoldierDto(Long id, String name, SoldierClass soldierClass, SoldierStatus status, LocalDateTime joinDateTime, LocalDateTime modifiedDateTime) {
        this.id = id;
        this.name = name;
        this.soldierClass = soldierClass;
        this.status = status;
        this.joinDateTime = joinDateTime;
        this.modifiedDateTime = modifiedDateTime;
    }
}
