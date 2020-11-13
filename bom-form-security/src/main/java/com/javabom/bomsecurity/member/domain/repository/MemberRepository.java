package com.javabom.bomsecurity.member.domain.repository;

import com.javabom.bomsecurity.member.domain.model.Member;

public interface MemberRepository {
    <S extends Member> S save(S entity);
}
