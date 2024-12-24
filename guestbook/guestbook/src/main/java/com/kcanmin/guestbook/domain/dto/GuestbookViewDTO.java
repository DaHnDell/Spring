package com.kcanmin.guestbook.domain.dto;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import com.kcanmin.guestbook.domain.entity.GuestbookEntitiy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestbookViewDTO {
  private Long gno;
  private String title;
  private String content;
  private String writer;
  private LocalDateTime regDate;
  private LocalDateTime modDate;

  public GuestbookViewDTO(GuestbookEntitiy entitiy){
    gno = entitiy.getGno();
    title = entitiy.getTitle();
    content = entitiy.getContent();
    writer = entitiy.getWriter();
    regDate = entitiy.getRegDate();
    modDate = entitiy.getModDate();
  }

  // public GuestbookEntitiy toEntitiy(){
  //   return new GuestbookEntitiy().builder().title(title).content(content).writer(writer).build();
  // }
}
