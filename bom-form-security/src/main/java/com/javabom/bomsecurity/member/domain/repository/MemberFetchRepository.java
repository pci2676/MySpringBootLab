package com.javabom.bomsecurity.member.domain.repository;

import com.javabom.bomsecurity.member.domain.model.Member;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.javabom.bomsecurity.member.domain.model.QMember.member;
import static com.javabom.bomsecurity.member.domain.model.QMemberRole.memberRole;

@Repository
@RequiredArgsConstructor
public class MemberFetchRepository {
    private final JPQLQueryFactory queryFactory;

    public Optional<Member> findByEmailFetch(String email) {
        return Optional.ofNullable(queryFactory.selectFrom(member)
                .innerJoin(member.memberRoles.memberRoles, memberRole).fetchJoin()
                .where(member.email.eq(email))
                .fetchOne());
    }
}
