package com.kcanmin.guestbook.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import com.kcanmin.guestbook.domain.entity.MemberEntity;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {
  @Autowired
  private MemberRepository repository;

  @Transactional
  @Test
  public void testExists(){
    log.info(repository);
  }

  @Transactional
  @Test
  @Rollback(false)
  public void testInsert(){
    // MemberEntity memberEntity = MemberEntity.builder().email("abc@c").password("1234").name("null").build();
    // repository.save(memberEntity);

    IntStream.rangeClosed(2, 100).forEach(i->{
      MemberEntity memberEntity = MemberEntity.builder().email("null" + i + "@com").password("12341234").name("null" + i).build();
      repository.save(memberEntity);
    });
  }

  @Transactional
  @Test
  public void testQuerydsl(){

  }
}
