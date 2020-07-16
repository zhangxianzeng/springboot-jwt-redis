package com.example.token.service;


import com.example.token.entity.User;

public interface TokenService {
    public String getToken(User user);
}
