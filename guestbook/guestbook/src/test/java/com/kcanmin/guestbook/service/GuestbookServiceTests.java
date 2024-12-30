package com.kcanmin.guestbook.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.kcanmin.guestbook.domain.dto.GuestbookDTO;
import com.kcanmin.guestbook.domain.dto.PageRequestDTO;
import com.kcanmin.guestbook.domain.dto.PageResultDTO;
import com.kcanmin.guestbook.domain.entity.GuestbookEntitiy;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
// @AllArgsConstructor
public class GuestbookServiceTests {
  // private GuestbookRepository repository;
  // @Autowired 
  @Autowired
  private GuestbookService service;
  // @Autowired
  
  
  
  @Test
  public void testList(){
    // PageResultDTO<GuestbookDTO, GuestbookEntitiy> testList = service.list(prd);
    // testList.getDtoList().forEach(log::info);  
    // PageResultDTO<GuestbookDTO, GuestbookEntitiy> prd = service.list(new PageRequestDTO(2, 10));
    // .getDtoList().forEach(log::info);
    // log.info(prd);
    // prd.getPageList().forEach(log::info);
    PageRequestDTO prd = PageRequestDTO.builder().page(1).size(10).type("TC").keyword("제목").build();
    PageResultDTO<GuestbookDTO, GuestbookEntitiy> resultDTO = service.list(prd);
    log.info(prd);
    resultDTO.getDtoList().forEach(log::info);
  }
    
  @Test
  @DisplayName("글 작성 서비스 테스트")
  @Transactional
  @Rollback(true)
  public void testWrite(){
  log.info("테스트 시작");
  GuestbookDTO dto = GuestbookDTO.builder()
    .title("테스트의 테스트 여부 제목입니다.")
    .content("테스트의 테스트 여부 콘텐트입니다.")
    .writer("테스트의 테스트 여부 작성자입니다.")
  .build();
  assertNotNull(dto);
  Long returnLONG = service.write(dto);
  log.info(returnLONG);
  // service.write(dto);
  log.info("테스트 끝");
  }

}