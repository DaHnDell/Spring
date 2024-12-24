package com.kcanmin.guestbook.domain.dto;

import com.kcanmin.guestbook.domain.entity.GuestbookEntitiy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestbookModifyDTO {
  private Long gno;
  private String title;
  private String content;
  private String writer;

  public GuestbookEntitiy toEntitiy(){
    return new GuestbookEntitiy().builder().gno(gno).title(title).content(content).writer(writer).build();
  }
}
