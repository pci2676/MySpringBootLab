package com.javabom.bomsecurity.member.domain.service;

import com.javabom.bomsecurity.member.domain.model.Member;
import com.javabom.bomsecurity.member.domain.model.Role;
import com.javabom.bomsecurity.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member create(Member member) {
        member.grantRole(Role.MEMBER);
        return memberRepository.save(member);
    }
}
