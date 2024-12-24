package com.kcanmin.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.kcanmin.guestbook.domain.dto.GuestbookListDTO;
import com.kcanmin.guestbook.repository.GuestbookRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class GuestbookServiceImplTests {
  private GuestbookRepository repository;
  private GuestbookServiceImpl service;

  @Test
  public void testList(){
    log.info(repository.findAll().stream().map(GuestbookListDTO::new).toList());
  }
}
