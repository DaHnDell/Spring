package com.kcanmin.guestbook.domain.dto;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
  private Long bno;
  private String title;
  private String content;
  private String memberEmail;
  private String memberName;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
  private int replyCnt;
}
