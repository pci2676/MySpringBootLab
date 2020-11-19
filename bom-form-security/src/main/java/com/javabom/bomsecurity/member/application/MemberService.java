package com.javabom.bomsecurity.member.application;

import com.javabom.bomsecurity.member.application.dto.MemberSignUpRequest;
import com.javabom.bomsecurity.member.application.mapper.MemberMapper;
import com.javabom.bomsecurity.member.domain.model.Member;
import com.javabom.bomsecurity.member.domain.model.Role;
import com.javabom.bomsecurity.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(MemberSignUpRequest memberSignUpRequest) {
        Member member = MemberMapper.mapFrom(memberSignUpRequest, passwordEncoder);
        member.grantRole(Role.MEMBER);

        memberRepository.findByEmail(member.getEmail())
                .ifPresent((savedMember) -> {
                    throw new IllegalArgumentException(String.format("%s : 이미 저장된 이메일 입니다.", savedMember.getEmail()));
                });
        try {
            Member saveMember = memberRepository.save(member);
            return saveMember.getId();
        } catch (ConstraintViolationException e) {
            log.info(e.getMessage());
            throw new IllegalArgumentException(String.format("%s : 이미 저장된 이메일 입니다.", member.getEmail()));
        }
    }
}
