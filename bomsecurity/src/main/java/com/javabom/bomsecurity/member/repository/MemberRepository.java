package com.javabom.bomsecurity.member.repository;

import com.javabom.bomsecurity.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
