package com.kcanmin.jdbc.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kcanmin.jdbc.vo.Member;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class MemberDaoTests {
  @Autowired
  private MemberDao dao;

  @Test
  public void testGetTime(){
    log.info(dao.getTime());
  }

  @Test
  public void testRegister(){
    Member member = Member.builder().id("1234").pw("4321").name("boot").build();
    dao.register(member);
    log.info("성공~");
  }
}
