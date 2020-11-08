package com.javabom.bomsecurity.member.service;

import com.javabom.bomsecurity.member.model.Member;
import com.javabom.bomsecurity.member.repository.MemberRepository;
import com.javabom.bomsecurity.member.service.dto.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.javabom.bomsecurity.member.model.Role.MEMBER;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(MemberSignUpRequest memberSignUpRequest) {
        Member member = Member.builder()
                .email(memberSignUpRequest.getEmail())
                .name(memberSignUpRequest.getName())
                .password(passwordEncoder.encode(memberSignUpRequest.getPassword()))
                .role(MEMBER)
                .build();
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }
}
