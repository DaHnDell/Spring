package com.kcanmin.club.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kcanmin.club.entity.Likes;
import com.kcanmin.club.entity.Member;
import com.kcanmin.club.entity.Note;
import com.kcanmin.club.entity.composite.LikesId;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class LikesRepositoryTests {

  @Autowired
  private LikesRepository repository;

  @Test
  public void testExist(){
    log.info(repository);
  }

  @Test
  public void testInsert() {
    Likes likes = Likes.builder().member(Member.builder().mno(103L).build()).note(Note.builder().num(11L).build()).build();
    repository.save(likes);
  }
  
  @Test
  public void testDelete() {
    LikesId likesId = LikesId.builder().member(103L).note(11L).build();
    repository.deleteById(likesId);
  }
  
  

}