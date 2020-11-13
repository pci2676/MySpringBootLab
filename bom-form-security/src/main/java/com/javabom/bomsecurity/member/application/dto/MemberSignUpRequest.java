package com.javabom.bomsecurity.member.application.dto;

import lombok.Getter;

@Getter
public class MemberSignUpRequest {
    private String email;
    private String name;
    private String password;

    public MemberSignUpRequest(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
