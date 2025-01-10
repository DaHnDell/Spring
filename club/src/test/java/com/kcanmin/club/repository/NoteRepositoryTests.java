package com.kcanmin.club.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.kcanmin.club.entity.Member;
import com.kcanmin.club.entity.Note;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class NoteRepositoryTests {
  @Autowired
  private NoteRepository repository;

  @Test
  @Rollback(false)
  public void testSave(){
    LongStream.rangeClosed(1, 5)
      .boxed()
      .map(ls -> Note
        .builder()
        .title("제"+ ls +"목")
        .content("컨텐트"+ls)
        .member(Member.builder()
          .mno(100L)
          .build())
      .build())
      .forEach(repository::save);
  }

  @Test
  public void testExist(){
    log.info(repository.findByNum(1L));
  }

  @Test
  public void testList(){
    repository.findByMemberMno(100L).forEach(log::info);
  }

  @Test
  public void testList2(){
    repository.findByMemberEmail("user100@kcanmin 100.com").forEach(log::info);
  }
}
