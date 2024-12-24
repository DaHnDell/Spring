package com.kcanmin.guestbook.domain.dto;

import java.time.LocalDateTime;
import java.util.Optional;

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
    // if(!opt.isPresent()){
    //   return;
    // }
    // GuestbookEntitiy entitiy = opt.get();
    this.gno = entitiy.getGno();
    this.title = entitiy.getTitle();
    this.content = entitiy.getContent();
    this.writer = entitiy.getWriter();
    this.regDate = entitiy.getRegDate();
    this.modDate = entitiy.getModDate();
  }

  // public GuestbookEntitiy toEntitiy(){
  //   return new GuestbookEntitiy().builder().title(title).content(content).writer(writer).build();
  // }
}
