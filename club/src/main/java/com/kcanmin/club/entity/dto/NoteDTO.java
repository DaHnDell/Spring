package com.kcanmin.club.entity.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteDTO {
  private Long num;
  private String title;
  private String content;
  private String writerEmail;
  private LocalDateTime regDate, modDate;

}
