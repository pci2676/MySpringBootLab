package com.javabom.bomsecurity.member.adapter.api;

import com.javabom.bomsecurity.member.application.MemberCommandService;
import com.javabom.bomsecurity.member.application.dto.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberCommandApi {
    private final MemberCommandService memberCommandService;

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody MemberSignUpRequest request) {
        Long memberId = memberCommandService.signUp(request);
        return ResponseEntity.created(URI.create("/members/" + memberId)).build();
    }

}
