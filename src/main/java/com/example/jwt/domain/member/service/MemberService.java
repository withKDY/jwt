package com.example.jwt.domain.member.service;

import com.example.jwt.config.jwt.JwtAuthToken;
import com.example.jwt.config.jwt.JwtAuthTokenProvider;
import com.example.jwt.config.jwt.PasswordAuthAuthenticationToken;
import com.example.jwt.domain.member.controller.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final AuthenticationManager authenticationManager;
    private final JwtAuthTokenProvider tokenProvider;

    public String memberLogin() {
        PasswordAuthAuthenticationToken token = new PasswordAuthAuthenticationToken("eodyd", "qwer");
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return createToken((PasswordAuthAuthenticationToken) authentication);
    }

    private String createToken(PasswordAuthAuthenticationToken token) {
        Date expiredDate = Date.from(LocalDateTime.now().plusMinutes(180).atZone(ZoneId.systemDefault()).toInstant());
        Map<String, String> claims = new HashMap<>();
        claims.put("id", token.getId().toString());
        claims.put("name", token.getName());
        claims.put("role", token.getRole());
        claims.put("account", token.getAccount());
        claims.put("phone", token.getPhone());

        JwtAuthToken jwtAuthToken = tokenProvider.createAuthToken(
                token.getPrincipal().toString(),
                token.getAuthorities().toString(),
                claims,
                expiredDate
        );

        return jwtAuthToken.getToken();
    }

}
