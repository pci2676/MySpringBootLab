package com.javabom.bomsecurity.member.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER"),
    BLOCK("ROLE_BLOCK");

    private final String role;

    public GrantedAuthority getGrantedAuthority() {
        return (GrantedAuthority) () -> this.role;
    }
}
