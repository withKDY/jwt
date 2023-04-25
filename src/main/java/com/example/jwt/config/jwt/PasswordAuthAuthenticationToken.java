package com.example.jwt.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class PasswordAuthAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private Long id;
    private String account;
    private String role;
    private String name;
    private String phone;

    public PasswordAuthAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public PasswordAuthAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
