package com.kcanmin.guestbook.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class GuestbookRepositortyTests {
  @Autowired
  private GuestbookRepository repository;

  @Test
  public void testExists(){
    log.info(repository);
  }

}
