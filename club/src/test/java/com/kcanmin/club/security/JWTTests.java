package com.kcanmin.club.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.kcanmin.club.util.JWTUtil;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class JWTTests {
  private JWTUtil jwtUtil;

  @BeforeEach
  public void testBefore(){
    log.info("testBefore Start ========================");
    jwtUtil = new JWTUtil();
  }

  @Test
  public void testGenToken() throws Exception{
    log.info("testGenToken Start =========================");
    String email = "user101@kcanmin101.com";
    String tokenGen = jwtUtil.generateToken(email);
    log.info("Generated Token Val :: " + tokenGen);
  }

  @Test 
  public void testExtact() throws Exception {
    log.info("testExtract Start ==========================");
    String email = "user101@kcanmin101.com";
    String token = jwtUtil.generateToken(email);

    Thread.sleep(5000);
    String resultEmail = jwtUtil.validateExtract(token + "1");
    log.info(resultEmail);

    // assertEquals(email, resultEmail);
  }
}
