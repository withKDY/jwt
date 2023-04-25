package com.example.jwt.config.jwt;

import com.example.jwt.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class PasswordAuthAuthenticationManager implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PasswordAuthAuthenticationToken token = new PasswordAuthAuthenticationToken("eodyd", "qwer", Collections.singleton(new SimpleGrantedAuthority("role")));
        token.setId(1L);
        token.setAccount("eodyd");
        token.setRole("role");
        token.setName("김대용");
        token.setPhone("01012341234");
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PasswordAuthAuthenticationToken.class);
    }
}
