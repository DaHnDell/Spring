package com.kcanmin.guestbook.service;

import java.util.List;

import com.kcanmin.guestbook.domain.dto.GuestbookDTO;
import com.kcanmin.guestbook.domain.dto.GuestbookListDTO;
import com.kcanmin.guestbook.domain.dto.GuestbookViewDTO;
import com.kcanmin.guestbook.domain.dto.PageRequestDTO;
import com.kcanmin.guestbook.domain.dto.PageResultDTO;
import com.kcanmin.guestbook.domain.entity.GuestbookEntitiy;

public interface GuestbookService { // 스태틱 키워드를 통해 인터페이스에도 구상 가능 + default 키워드로도 구상이 가능하다.
  Long write(GuestbookDTO dto);
  PageResultDTO<GuestbookDTO, GuestbookEntitiy> list(PageRequestDTO dto);
  void modify(GuestbookDTO dto);
  void remove(Long gno);
  List<GuestbookListDTO> list();
  
  GuestbookViewDTO get(Long gno);
  GuestbookDTO read(Long gno);

  default GuestbookEntitiy toEntitiy(GuestbookDTO dto){
    return GuestbookEntitiy
    .builder()
      .gno(dto.getGno())
      .title(dto.getTitle())
      .content(dto.getContent())
      .writer(dto.getWriter())
    .build();
  }

  default GuestbookDTO toDTO(GuestbookEntitiy entitiy){
    return GuestbookDTO
    .builder()
      .gno(entitiy.getGno())
      .title(entitiy.getTitle())
      .content(entitiy.getContent())
      .writer(entitiy.getWriter())
      .regDate(entitiy.getRegDate())
      .modDate(entitiy.getModDate())
    .build();
  }
}
