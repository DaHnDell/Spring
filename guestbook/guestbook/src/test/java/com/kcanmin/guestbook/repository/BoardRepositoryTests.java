package com.kcanmin.guestbook.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.kcanmin.guestbook.domain.dto.BoardDto;
import com.kcanmin.guestbook.domain.entity.BoardEntity;
import com.kcanmin.guestbook.domain.entity.MemberEntity;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
  @Autowired
  private BoardRepository repository;

  @Transactional
  @Test
  public void testExists(){
    log.info(repository);
  }

  @Test
  @Transactional
  public void testGetBoardByBno(){
    Long bno = 100L;

    log.info(repository.getBoardByBno(bno));
  }

  @Test
  @Transactional
  public void testGetBoardWithReplyCount(){
    Pageable pageable = PageRequest.of(0, 10, Sort.by(Direction.DESC, "bno"));
    Page<Object[]> result = repository.getBoardWithReplyCount(pageable);
    result.forEach(log::info);
  }

  @Transactional
  @Test
  @Rollback(false)
  public void testInsert(){
    // MemberEntity memberEntity = MemberEntity.builder().email("abc@c").password("1234").name("null").build();
    // repository.save(memberEntity);

    IntStream.rangeClosed(2, 100).forEach(i->{
      BoardEntity boardEntity = BoardEntity.builder().title("null" + i + "@com").content("12341234").member(MemberEntity.builder().email("null" + i + "@com").build()).build();
      repository.save(boardEntity);
    });
  }

  @Transactional
  @Test
  public void testQuerydsl(){

  }

  @Transactional
  @Test
  public void testGet(){
    
  }

  @Test
  @Transactional
  public void testGetBoardWithReply(){
    List<Object[]> result = repository.getBoardWithReply(101L);
    result.forEach(arr->log.info(Arrays.toString(arr)));
  }

  @Test
  @Transactional
  public void testSelectOne(){
    BoardEntity boardEntity = repository.findById(101L).get(); 
    log.info(boardEntity);
    log.info(boardEntity.getMember());
  }

  @Test
  public void testGetBoardWithMember(){
    Object result = repository.getBoardWithMember(100L);
    Object[] arr = (Object[])result;
    log.info(Arrays.toString(arr));
  }

  @Test
  public void testGetone(){
    Object[] result = ((Object[])repository.getBoardByBno(100L));
    log.info(Arrays.toString(result));
  }

  @Test
  public void testDelete(){

  }

  @Test
  public void testSearch1(){
    repository.search1();
  }

  @Test
  public void testSearchPage(){
    // Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
    Page<Object[]> result = repository.searchPage("T", "1", 
    PageRequest.of(1, 5, Sort.by(Direction.DESC, "bno", "title")));
    // result.forEach(log::info);
  }

}
