package com.javabom.application.domain.soldier.service;

import com.javabom.application.config.ApplicationConfig;
import com.javabom.application.domain.soldier.dto.SoldierDto;
import com.javabom.application.domain.soldier.repository.SoldierRepository;
import com.javabom.core.domain.soldier.Soldier;
import com.javabom.core.domain.soldier.SoldierClass;
import com.javabom.core.domain.soldier.SoldierStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@EnableAutoConfiguration
@SpringBootTest(classes = {ApplicationConfig.class})
class SoldierServiceTest {

    @Autowired
    private SoldierService service;

    @Autowired
    private SoldierRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

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

    @DisplayName("DISABLE 된 병사는 검색되지 않는다.")
    @Test
    void findActivateSoldierByClassTest1() {
        //given
        Soldier soldier = createSolider("name1", SoldierClass.PRIVATE, SoldierStatus.DISABLE);
        repository.save(soldier);

        //when
        List<SoldierDto> results = service.findActivateSoldierByClass(SoldierClass.PRIVATE);

        //then
        assertThat(results).isEmpty();
    }

    @DisplayName("ENABLE 된 병사중 계급이 일치하지 않으면 검색되지 않는다.")
    @Test
    void findActivateSoldierByClassTest2() {
        //given
        Soldier soldier = createSolider("name1", SoldierClass.SERGENT, SoldierStatus.ENABLE);
        repository.save(soldier);

        //when
        List<SoldierDto> results = service.findActivateSoldierByClass(SoldierClass.PRIVATE);

        //then
        assertThat(results).isEmpty();
    }

    @DisplayName("ENABLE 된 병사중 계급이 일치하면 검색된다.")
    @Test
    void findActivateSoldierByClassTest3() {
        //given
        Soldier soldier = createSolider("name1", SoldierClass.PRIVATE, SoldierStatus.ENABLE);
        repository.save(soldier);

        //when
        List<SoldierDto> results = service.findActivateSoldierByClass(SoldierClass.PRIVATE);

        //then
        assertThat(results).hasSize(1);
    }
}
