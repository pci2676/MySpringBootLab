package com.javabom.bomsecurity.member.domain.repository;

import com.javabom.bomsecurity.member.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
