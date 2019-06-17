package org.ds.study.validationTest.entity;

import org.ds.study.validationTest.repository.AccountRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountBlankValidationTest {

    private String email = "example@gmail.com";

    @Autowired
    private AccountRepository accountRepository;

    @After
    public void tearDown() throws Exception {
        accountRepository.deleteAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void 널값_넣으면() {
        //given
        String name = null;

        //when
        Account account = Account.testBuilder()
                .email(email)
                .name(name)
                .build();

        //then
        accountRepository.save(account);
    }

    @Test(expected = ConstraintViolationException.class)
    public void 빈칸_넣으면() {
        //given
        String name = "";

        //when
        Account account = Account.testBuilder()
                .email(email)
                .name(name)
                .build();

        //then
        accountRepository.save(account);
    }

    @Test
    public void 정상입력() {
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
