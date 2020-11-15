package com.javabom.bomsecurity.member.application;

import com.javabom.bomsecurity.member.domain.model.Member;
import com.javabom.bomsecurity.member.domain.repository.MemberFetchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberDetailService implements UserDetailsService {
    private final MemberFetchRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmailFetch(username)
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s 존재하지 않는 이메일입니다.", username)));
        return new User(member.getEmail(), member.getPassword(), member.getRoles());
    }
}
