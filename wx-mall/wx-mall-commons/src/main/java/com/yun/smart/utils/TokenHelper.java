package com.yun.smart.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHelper {
	
    public String SECRET="jiujiangsecurityv1";

	/**
     * 生成jwt token
     */
    public String generateToken(String userName,Long date) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(date);
        
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userName)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
	
}
