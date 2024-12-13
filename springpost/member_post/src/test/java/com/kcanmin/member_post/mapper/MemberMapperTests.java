package com.kcanmin.member_post.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class MemberMapperTests {
  @Autowired
  private MemberMapper mapper;

  @Test
  @DisplayName("memberMapper 생성 여부 확인")
  public void mapperTest(){
    assertNotNull(mapper, "메세지");
  }

  @Test
  public void testSignin(){
    log.info(mapper.selectOne("1234"));
  }
}
