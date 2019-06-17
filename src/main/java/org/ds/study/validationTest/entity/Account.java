package org.ds.study.validationTest.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String email;

    @NotBlank
    private String name;

    @Builder(builderMethodName = "testBuilder")
    private Account(@Email String email, @NotBlank String name) {
        this.email = email;
        this.name = name;
    }
}
