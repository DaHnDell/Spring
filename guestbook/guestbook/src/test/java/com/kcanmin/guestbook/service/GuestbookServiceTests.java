package com.kcanmin.guestbook.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

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
  
  
  
  // @Test
  // public void testList(){
  //   // PageRequestDTO prd = new PageRequestDTO();
  //   // PageResultDTO<GuestbookDTO, GuestbookEntitiy> testList = service.list(prd);
  //   // testList.getDtoList().forEach(log::info);  
  //   PageResultDTO<GuestbookDTO, GuestbookEntitiy> prd = service.list(new PageRequestDTO(2, 10));
  //   // .getDtoList().forEach(log::info);
  //   log.info(prd);
  //   prd.getPageList().forEach(log::info);
  // }
    
    @Test
    public void testWrite(){
    log.info("테스트 시작");
    GuestbookDTO dto = GuestbookDTO.builder()
      .title("제목입니다.")
      .content("콘텐트입니다.")
      .writer("작성자입니다.")
    .build();
    assertNotNull(dto);
    Long returnLONG = service.write(dto);
    log.info(returnLONG);
    // service.write(dto);
    log.info("테스트 끝");
  }
}
