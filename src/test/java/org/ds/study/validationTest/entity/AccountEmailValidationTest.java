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
public class AccountEmailValidationTest {

    @Autowired
    private AccountRepository accountRepository;

    String name = "박찬인";

    @After
    public void tearDown() throws Exception {
        accountRepository.deleteAll();
    }

    @Test
    public void 이메일_벨리데이션_확인_널값() {
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

    @Test
    public void 이메일_벨리데이션_확인_빈칸() {
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

    @Test(expected = ConstraintViolationException.class)
    public void 이메일_벨리데이션_확인_이메일아닌문자열() {
        //given
        String email = "not email format";

        //when
        Account account = Account.testBuilder()
                .email(email)
                .name(name)
                .build();

        //then
        accountRepository.save(account);
    }

    @Test
    public void 이메일_벨리데이션_확인_이메일포맷만지킨_문자열() {
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

    @Test
    public void 이메일_벨리데이션_확인_학교메일형식() {
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