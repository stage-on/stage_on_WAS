package com.stageon.stageonwas.domain.auth.jwt;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.util.Date;

@Component
public class JwtProvider {

    private final SecretKey key;
    private final long accessTokenExpiration;
    private final String issuer;

    // 유저ID를 받아 액세스 토큰 생성
    public JwtProvider(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.expiration.access-token}") long accessTokenExpiration,
            @Value("${jwt.issuer}") String issuer) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.accessTokenExpiration = accessTokenExpiration;
        this.issuer = issuer;
    }

    public String generateAccessToken(Long userId) {
        return createToken(String.valueOf(userId), accessTokenExpiration);
    }

    // 빌더패턴을 사용해 Header, Payload, Signature를 조립
    private String createToken(String subject, long expirationTime) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(subject) // Payload: "누구인지" (User ID)
                .setIssuer(issuer)  // Payload: "누가 발급했는지"
                .setIssuedAt(now)   // Payload: "언제 만들었는지"
                .setExpiration(expiry) // Payload: "언제까지 유효한지"
                .signWith(key, SignatureAlgorithm.HS256) // Signature: 서버의 키로 암호화 (변조 방지)
                .compact(); // 최종 문자열로 변환
    }

    // 토큰이 유효한지 검사 (위조, 만료)
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // token에 담긴 userId 반환
    public String getSubject(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
