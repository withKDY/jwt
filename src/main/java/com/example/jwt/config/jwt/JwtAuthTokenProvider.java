package com.example.jwt.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Component
public class JwtAuthTokenProvider implements AuthTokenProvider<JwtAuthToken> {
    @Value("${jwt.base64.secret}")
    private String base64Secret;
    private Key key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public JwtAuthToken createAuthToken(String id, String role, Map<String, String> claims, Date expireDate) {
        return new JwtAuthToken(id, key, role, claims, expireDate);
    }

    @Override
    public JwtAuthToken convertAuthToken(String token) {
        return new JwtAuthToken(token, key);
    }

    @Override
    public Authentication getAuthentication(JwtAuthToken authToken) {
        if (authToken.validate()) {
            Claims claims = authToken.getData();
            Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(claims.get(AuthToken.AUTHORITIES_KEY, String.class)));
            User principal = new User(claims.getSubject(), "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, authToken, authorities);
        } else
            throw new JwtException("token error");
    }
}
