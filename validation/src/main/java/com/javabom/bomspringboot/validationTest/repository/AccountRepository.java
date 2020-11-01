package com.javabom.bomspringboot.validationTest.repository;

import com.javabom.bomspringboot.validationTest.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
