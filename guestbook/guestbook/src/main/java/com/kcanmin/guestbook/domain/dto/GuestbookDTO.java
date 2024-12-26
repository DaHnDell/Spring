package com.kcanmin.guestbook.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuestbookDTO {
  private Long gno;
  private String title, content, writer;
  private LocalDateTime regDate, modDate;  
}
