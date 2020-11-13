package com.javabom.bomsecurity.member.application;

import com.javabom.bomsecurity.member.application.dto.MemberSignUpRequest;
import com.javabom.bomsecurity.member.domain.model.Member;
import com.javabom.bomsecurity.member.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberCommandService {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(MemberSignUpRequest memberSignUpRequest) {
        Member member = Member.builder()
                .email(memberSignUpRequest.getEmail())
                .name(memberSignUpRequest.getName())
                .password(passwordEncoder.encode(memberSignUpRequest.getPassword()))
                .build();
        Member saveMember = memberService.create(member);
        return saveMember.getId();
    }
}
