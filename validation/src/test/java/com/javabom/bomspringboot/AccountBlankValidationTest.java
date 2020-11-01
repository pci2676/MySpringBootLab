package com.javabom.bomspringboot;

import com.javabom.bomspringboot.validationTest.entity.Account;
import com.javabom.bomspringboot.validationTest.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class AccountBlankValidationTest {

    private String email = "example@gmail.com";

    @Autowired
    private AccountRepository accountRepository;

    @AfterEach
    public void tearDown() {
        accountRepository.deleteAll();
    }

    @DisplayName("널값_넣으면 exception")
    @Test
    public void test1() {
        //given
        String name = null;

        //when
        Account account = Account.testBuilder()
                .email(email)
                .name(name)
                .build();

        //then
        assertThatThrownBy(() -> accountRepository.save(account))
                .isInstanceOf(ConstraintViolationException.class);

    }

    @DisplayName("빈칸_넣으면 exception")
    @Test
    public void test2() {
        //given
        String name = "";

        //when
        Account account = Account.testBuilder()
                .email(email)
                .name(name)
                .build();

        //then
        assertThatThrownBy(() -> accountRepository.save(account))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @DisplayName("정상입력")
    @Test
    public void test3() {
        //given
        String name = "박찬인";

        //when
        Account account = Account.testBuilder()
                .email(email)
                .name(name)
                .build();

        //then
        accountRepository.save(account);
    }

}
