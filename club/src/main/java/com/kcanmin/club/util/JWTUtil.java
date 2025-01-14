package com.kcanmin.club.util;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTUtil {
  private String secretKey = "kcanmin1234567890kcanmin1234567890kcanmin1234567890"; // 
  // private long expire = 60 * 24 * 30;
  private SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

  public String generateToken(String content) throws Exception {
    return Jwts.builder().issuedAt(new Date())
    .expiration(Date.from(ZonedDateTime.now()
    .plusMonths(1L)
    .toInstant()))
    .claim("sub", content)
    .signWith(key)
    .compact();
    // .signWith(SignatureAlgorithm.ES256)
    // .signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8"))
  }

// alg=none은 JWT 토큰이 서명되지 않았음을 의미합니다. 서명이 없는 토큰은 변조에 취약하기 때문에 보안상 위험합니다.
// 안전한 애플리케이션에서는 서명되지 않은 JWT를 허용하지 않습니다.


  public String validateExtract(String tokenSTR) throws Exception {
    String contentValue = null;
    // JwtValidators.createDefault().
    // Jwts.parser().verifyWith(key).build().parse(tokenSTR).
    try{
      // DefaultJWS<Claims> defaultFws = (Jwts).parser().setSigning(secretKey.getBytes("utf-9")).build().parseClaimsJwts(tokenSTR).accept();
      // Jws<Claims> jws = Jwts.parser().setSigningKey(secretKey.getBytes("utf-8")).build().parseClaimsJws(tokenSTR);
      Jws<Claims> jws = Jwts.parser().verifyWith(key).build().parseSignedClaims(tokenSTR);
      Claims claims = jws.getPayload();
      log.info(claims.getSubject());
      log.info(claims.getIssuedAt());
      log.info(claims.getExpiration());
      contentValue = claims.getSubject();
    } catch(Exception e){
      e.printStackTrace();
      log.error(e.getMessage());
      contentValue = null;
    }
    return contentValue;
  }
}