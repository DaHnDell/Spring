package com.kcanmin.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kcanmin.guestbook.repository.BoardRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
  @Autowired
  BoardRepository repository;

  @Autowired
  BoardService service;

  @Test
  public void testGet(){
    Long bno = 100L;
    log.info(service.get(bno));
  }

}
