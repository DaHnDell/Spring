package com.kcanmin.jdbc.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
  private String id;
  private String pw;
  private String name;
  private String email;
  private String roadAddr;
  private String detailAddr;
  private LocalDateTime regDate;
}
