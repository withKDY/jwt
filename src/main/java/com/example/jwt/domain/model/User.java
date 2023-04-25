package com.example.jwt.domain.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@ToString
public class User {
    private String id;
    private String loginId;
    private String password;
}
