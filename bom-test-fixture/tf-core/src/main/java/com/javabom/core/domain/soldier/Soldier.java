package com.javabom.core.domain.soldier;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Soldier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private SoldierClass soldierClass;

    private SoldierStatus status;

    private LocalDateTime joinDateTime;

    private LocalDateTime modifiedDateTime;

    @Builder
    public Soldier(String name, SoldierClass soldierClass, SoldierStatus status, LocalDateTime joinDateTime, LocalDateTime modifiedDateTime) {
        this.name = name;
        this.soldierClass = soldierClass;
        this.status = status;
        this.joinDateTime = joinDateTime;
        this.modifiedDateTime = modifiedDateTime;
    }

    public Soldier promote(SoldierClass promotionRole) {
        if (status.isInActivate()) {
            throw new RuntimeException("inactivated member");
        }
        if (soldierClass.isHigherThan(promotionRole)) {
            throw new RuntimeException("can not promotion");
        }

        this.soldierClass = promotionRole;
        return this;
    }

}

