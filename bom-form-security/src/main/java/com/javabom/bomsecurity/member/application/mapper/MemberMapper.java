package com.javabom.bomsecurity.member.application.mapper;

import com.javabom.bomsecurity.member.application.dto.MemberSignUpRequest;
import com.javabom.bomsecurity.member.domain.model.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberMapper {
    public static Member mapFrom(MemberSignUpRequest request, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

    }
}
