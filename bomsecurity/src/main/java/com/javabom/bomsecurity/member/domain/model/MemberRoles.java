package com.javabom.bomsecurity.member.domain.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class MemberRoles {
    @OneToMany(mappedBy = "member", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<MemberRole> memberRoles = new ArrayList<>();

    public List<GrantedAuthority> getGratedAuthorities() {
        return memberRoles.stream()
                .map(MemberRole::getGrantedAuthority)
                .collect(Collectors.toList());
    }

    public void add(Member member, Role role) {
        List<Role> roles = memberRoles.stream()
                .map(MemberRole::getRole)
                .collect(Collectors.toList());
        if (roles.contains(role)) {
            throw new IllegalArgumentException(String.format("%s : 이미 존재하는 권한입니다.", role.getRole()));
        }
        memberRoles.add(new MemberRole(member, role));
    }
}
