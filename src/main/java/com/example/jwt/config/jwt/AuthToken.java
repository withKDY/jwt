package com.example.jwt.config.jwt;

public interface AuthToken <T>{
    String AUTHORITIES_KEY = "role";
    boolean validate();
    T getData();
}
