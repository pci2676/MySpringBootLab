package com.javabom.bomsecurity.member.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ADMIN"),
    MEMBER("MEMBER"),
    BLOCK("BLOCK");

    private final String role;
}
