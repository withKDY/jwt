package com.example.jwt.domain.member.controller;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginDto {
    private String account;
    private String password;
    private String role;
}
