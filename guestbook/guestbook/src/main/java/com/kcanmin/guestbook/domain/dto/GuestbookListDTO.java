package com.kcanmin.guestbook.domain.dto;

import java.time.LocalDateTime;

import com.kcanmin.guestbook.domain.entity.GuestbookEntitiy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestbookListDTO {
  private Long gno;
  private String title;
  private String writer;
  private LocalDateTime regDate;
  private LocalDateTime modDate;

  public GuestbookListDTO(GuestbookEntitiy entitiy){
    gno = entitiy.getGno();
    title = entitiy.getTitle();
    writer = entitiy.getWriter();
    regDate = entitiy.getRegDate();
    modDate = entitiy.getModDate();
  }

  // public GuestbookEntitiy toEntitiy(){
  //   return new GuestbookEntitiy().builder().title(title).content(content).writer(writer).build();
  // }
}
