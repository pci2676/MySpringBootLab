package com.javabom.bomsecurity.member.adapter.infra.repository;

import com.javabom.bomsecurity.member.domain.model.Member;
import com.javabom.bomsecurity.member.domain.repository.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends MemberRepository, JpaRepository<Member, Long> {
}
