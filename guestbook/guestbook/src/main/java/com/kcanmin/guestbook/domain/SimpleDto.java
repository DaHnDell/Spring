package com.kcanmin.guestbook.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class SimpleDto {
  private Long sno;
  private String first;
  private String last;
  private LocalDateTime regTime;  
}
