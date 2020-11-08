package com.javabom.bomsecurity.member.api;

import com.javabom.bomsecurity.member.service.MemberService;
import com.javabom.bomsecurity.member.service.dto.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class MemberCommandApi {
    private final MemberService memberService;

    @PostMapping("/members/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody MemberSignUpRequest memberSignUpRequest) {
        Long memberId = memberService.signUp(memberSignUpRequest);
        return ResponseEntity.created(URI.create("/members/" + memberId)).build();
    }
}
