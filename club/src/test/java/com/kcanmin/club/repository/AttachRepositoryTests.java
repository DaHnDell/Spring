package com.kcanmin.club.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.kcanmin.club.entity.Attach;
import com.kcanmin.club.entity.Note;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class AttachRepositoryTests {
  
  @Autowired
  AttachRepository repository;

  @Test
  public void repoExistTest(){
    log.info("existTestStart");
    log.info(repository);
    log.info("existTestend");
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testInsert(){
    log.info("testInsertStart==========================");
    // IntStream.rangeClosed(16, 20).forEach(i -> {
      // Attach attach = Attach.builder().uuid(""+"jhmfjhfj").origin(1+"attachTest.png").path(null).image(false).note(Note.builder().num(14L).build()).build();
      // repository.save(attach);
    // });
  }

  @Test 
  public void testNote(){
    log.info("testNote Start==========================");
    // repository.findById(null)
  }

}
