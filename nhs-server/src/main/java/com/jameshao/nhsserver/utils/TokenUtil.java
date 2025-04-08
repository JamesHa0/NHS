package com.jameshao.nhsserver.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class TokenUtil {
    //设置密钥
    private static String secret = "22023237zyh";

    //生成Token
    public static String createToken(){
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))//设置过期时间为3小时
                .sign(Algorithm.HMAC256(secret));
    }

    //验证Token
    public static void authToken(String token){
        // 验证Token
        JWTVerifier jwtverifier = JWT.require(Algorithm.HMAC256(secret)).build();
        jwtverifier.verify(token);
    }
}
