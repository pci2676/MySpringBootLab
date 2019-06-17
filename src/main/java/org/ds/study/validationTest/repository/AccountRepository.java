package org.ds.study.validationTest.repository;

import org.ds.study.validationTest.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
