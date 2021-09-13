package com.ssafy.house.model.service;


import com.ssafy.house.model.dto.MemberDto;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtService {
    public static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    private String signature = "VUETOKEN";
    private Long expireMin = 20L;

    public String create(MemberDto memberDto) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setHeaderParam("typ", "JWT");

        jwtBuilder
                .setSubject("로그인 토큰")
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
                .claim("user", memberDto)
                .claim("greeting", "환영합니다." + memberDto.getUsername() + "!!!");

        jwtBuilder.signWith(SignatureAlgorithm.HS256, signature.getBytes());

        String jwt = jwtBuilder.compact();
        logger.info("jwt 토큰 : {}", jwt);
        return jwt;
    }

    public void checkValid(String jwt) {
        Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
    }

    public Map<String, Object> get(String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
        } catch (final Exception  e) {
        	throw new RuntimeException();
        }

        logger.info("claims : {}", claims);
        return claims.getBody();
    }
}
