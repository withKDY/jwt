package com.example.jwt.domain.member.controller;

import com.example.jwt.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public String login() {
        return  memberService.memberLogin();
    }
}
