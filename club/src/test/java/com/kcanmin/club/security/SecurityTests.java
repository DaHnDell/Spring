package com.kcanmin.club.security;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class SecurityTests {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  public void testEncoder(){
    log.info(passwordEncoder);
    log.info(passwordEncoder.getClass().getName());

    String pwString = "1234";
    String encodedString = passwordEncoder.encode(pwString);

    log.info(pwString);
    log.info(encodedString);

    //$2a$10$oGDm5rkB/htTtjsg5s68QeCZj/Os8vFFCEW4vcUb/mxMnrtZJHPuS
    //$2a$10$YP7wdCG6hYvKZD9DjVAJUOSwVkJUxuOgnqXko5mAwBha3/us7PKqm

    assertTrue(passwordEncoder.matches(pwString, "$2a$10$YP7wdCG6hYvKZD9DjVAJUOSwVkJUxuOgnqXko5mAwBha3/us7PKqm"));
    assertTrue(passwordEncoder.matches(pwString, "$2a$10$oGDm5rkB/htTtjsg5s68QeCZj/Os8vFFCEW4vcUb/mxMnrtZJHPuS"));
  }
}
