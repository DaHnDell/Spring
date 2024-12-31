package com.kcanmin.guestbook.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.kcanmin.guestbook.domain.entity.BoardEntity;
import com.kcanmin.guestbook.domain.entity.ReplyEntity;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {
  @Autowired
  private ReplyRepository repository;

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
    IntStream.rangeClosed(1, 500).forEach(i->{
      ReplyEntity replyEntity = ReplyEntity.builder()
        .text("null" + "text")
        .replyer("replyer" + i)
        .board(BoardEntity.builder().bno(0L+(int)(Math.random() * 99 + 100)).build())
        .build();
      repository.save(replyEntity);
    });
  }

  @Transactional
  @Test
  public void testQuerydsl(){

  }

  @Test
  public void testSelectOne(){
    
    ReplyEntity replyEntity = repository.findById(100L).orElse(null);
    // log.info(repository.findById(100L).orElse(null));
    log.info(replyEntity);
    log.info(replyEntity.getBoard().getMember().getName());
    
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testDeleteByBno(){
    repository.deleteByBoardBno(100L);
  }
}
