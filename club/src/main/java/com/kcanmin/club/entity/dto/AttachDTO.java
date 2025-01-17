package com.kcanmin.club.entity.dto;

import com.kcanmin.club.entity.Note;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttachDTO {

  private String uuid;
  private String origin;
  private boolean image;
  private String path;
  private Note note;

  private Long size;
  private String mime;
  private String fileName;
  private String ext;
  private String url;

  //note num
  private Long num; // note num
}
