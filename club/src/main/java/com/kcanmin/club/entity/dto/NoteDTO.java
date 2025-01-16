package com.kcanmin.club.entity.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

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
  private Long mno;

  @Default
  private List<AttachDTO> attachDtos = new ArrayList<>();
}
