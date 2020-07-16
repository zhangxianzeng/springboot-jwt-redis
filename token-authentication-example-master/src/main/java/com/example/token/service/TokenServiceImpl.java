package com.example.token.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.token.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements  TokenService{

    @Override
    public String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
//将username或者其他的表示放入token中这样就能够进行判断，放username或者id都行，只要是唯一标示
        token = JWT.create().withAudience(user.getUsername()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
