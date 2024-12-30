package com.kcanmin.guestbook.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "tbl_reply")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ReplyEntity extends BaseEntity{
  
  @Id  
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rno;

  private String text;

  private String replyer;

  @ManyToOne
  private BoardEntity board;
}
