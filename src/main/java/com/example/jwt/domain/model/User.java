package com.example.jwt.domain.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {
    private String id;
    private String loginId;
    private String password;
}
