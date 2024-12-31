package com.kcanmin.guestbook.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.kcanmin.guestbook.domain.dto.BoardDto;
import com.kcanmin.guestbook.domain.dto.PageRequestDTO;
import com.kcanmin.guestbook.domain.dto.PageResultDTO;
import com.kcanmin.guestbook.repository.BoardRepository;
import com.kcanmin.guestbook.repository.ReplyRepository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
  @Autowired
  BoardRepository repository;

  @Autowired
  BoardService service;

  @Autowired
  ReplyRepository replyRepository;

  @Test
  public void testGet(){
    Long bno = 100L;
    BoardDto dto = service.get(bno);
    log.info(dto);
  }

  @Test
  @Transactional
  public void testResgister(){
    // given
    BoardDto dto = BoardDto.builder().content("서비스 테스트").title("서비스 테스트 제목").memberName("nullbu").memberEmail("null@23com").build();
    // when
    Long res = service.register(dto);
    // then
    assertNotNull(res);
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testRemove(){
    Long bno = 100L;
    // replyRepository.deleteByBoardBno(bno);
    service.remove(bno);
    log.info("리무브 성공");
  }


  @Test
  @Transactional
  public void testList(){
    PageResultDTO<BoardDto, Object[]> res = service.list(PageRequestDTO.builder().page(1).size(10).build());
    log.info(res);
    res.getDtoList().forEach(log::info);
  }


  @Test
  @Transactional
  @Rollback(false)
  public void testMod(){
    BoardDto dto = service.get(101L);
    dto.setMemberEmail("null4@com");
    service.modify(dto);
    log.info(dto);
  }


  
}
