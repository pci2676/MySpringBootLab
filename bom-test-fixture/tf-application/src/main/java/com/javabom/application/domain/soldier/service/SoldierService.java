package com.javabom.application.domain.soldier.service;

import com.javabom.application.domain.soldier.dto.SoldierDto;
import com.javabom.application.domain.soldier.repository.SoldierRepository;
import com.javabom.core.domain.soldier.SoldierClass;
import com.javabom.core.domain.soldier.SoldierStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SoldierService {
    private final SoldierRepository repository;

    public List<SoldierDto> findActivateSoldierByClass(SoldierClass soldierClass) {
        return repository.findAllByStatusAndSoldierClass(SoldierStatus.ENABLE, soldierClass).stream()
                .map(SoldierDto::of)
                .collect(Collectors.toList());
    }
}
