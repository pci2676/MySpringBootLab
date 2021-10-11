package com.javabom.application.domain.soldier.repository;

import com.javabom.core.domain.soldier.Soldier;
import com.javabom.core.domain.soldier.SoldierClass;
import com.javabom.core.domain.soldier.SoldierStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoldierRepository extends JpaRepository<Soldier, Long> {
    List<Soldier> findAllByStatusAndSoldierClass(SoldierStatus status, SoldierClass soldierClass);
}
