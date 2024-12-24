package com.kcanmin.guestbook.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
// (exclude = )
@Table(name = "tbl_guestbook")
public class GuestbookEntitiy extends BaseEntity{

// 문법 구조상 final로 하는 것이 맞음.

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long gno;

  @Column(nullable = false, length = 100)
  private String title;
  
  @Column(nullable = false, length = 1500)
  private String content;
  
  @Column(nullable = false, length = 50)
  private String writer;

  // nullable - false = not null.

}
