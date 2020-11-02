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
public class AccountEmailValidationTest {

    String name = "박찬인";
    @Autowired
    private AccountRepository accountRepository;

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @DisplayName("이메일_벨리데이션_확인_널값")
    @Test
    public void test1() {
        //given
        String email = null;

        //when
        Account account = Account.testBuilder()
                .email(email)
                .name(name)
                .build();

        //then
        accountRepository.save(account);
    }

    @DisplayName("이메일_벨리데이션_확인_빈칸")
    @Test
    public void test2() {
        //given
        String email = "";

        //when
        Account account = Account.testBuilder()
                .email(email)
                .name(name)
                .build();

        //then
        accountRepository.save(account);
    }

    @DisplayName("이메일_벨리데이션_확인_이메일아닌문자열 exception")
    @Test
    public void test3() {
        //given
        String email = "not email format";

        //when
        Account account = Account.testBuilder()
                .email(email)
                .name(name)
                .build();

        //then
        assertThatThrownBy(() -> accountRepository.save(account))
                .isInstanceOf(ConstraintViolationException.class);

    }

    @DisplayName("이메일_벨리데이션_확인_이메일포맷만지킨_문자열")
    @Test
    public void test4() {
        //given
        String email = "not@Email.String";

        //when
        Account account = Account.testBuilder()
                .email(email)
                .name(name)
                .build();

        //then
        accountRepository.save(account);
    }

    @DisplayName("이메일_벨리데이션_확인_학교메일형식")
    @Test
    public void test5() {
        //given
        String email = "201401452@inu.ac.kr";

        //when
        Account account = Account.testBuilder()
                .email(email)
                .name(name)
                .build();

        //then
        accountRepository.save(account);
    }
}