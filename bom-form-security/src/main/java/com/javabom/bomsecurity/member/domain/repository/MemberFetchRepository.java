package com.javabom.bomsecurity.member.domain.repository;

import com.javabom.bomsecurity.member.domain.model.Member;

import java.util.Optional;

public interface MemberFetchRepository {
    Optional<Member> findByEmailFetch(String email);
}
