package com.example.authenticateservice.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class JWTService {

    private final String SECRET = "37b34d3c0242c7872b992ba5009c655e1c48dff1aa1180ca2c854a55f8002c8a1f13e387688aaf33c5adf022ce4e8a3bd0bca171003998f4fa203c5dde3e6588ae084b23d2f306a13595dba9ce7ce01656accc4701e160978fb119ed3ee0d36d5fdec34000c5b9e316d7c88e3ff32fb3a3b3329b75ad1ec9356da2fa191b86f7";

    public void validationToken(String token) {
        Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
    }



    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(bytes);
    }


}
