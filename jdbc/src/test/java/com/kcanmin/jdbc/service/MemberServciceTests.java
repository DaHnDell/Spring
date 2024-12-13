package com.kcanmin.jdbc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class MemberServciceTests {
  @Autowired
  private MemberService memberService;
  
  @Test
  public void testQuit(){
    try{
      memberService.quitMember("abcd");
    }catch(DataAccessException dae){
      log.error(dae.getMessage());
    }
  }
}
