package com.kcanmin.club.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder.Default;

@Entity(name = "tbl_note")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"member", "attachs"})
public class Note extends BaseEntity{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long num;
  private String title;
  private String content;
  @ManyToOne(fetch = FetchType.LAZY)

  @Setter
  private Member member;

  public void changeTitle(String title){
    this.title = title;
  }
  public void changeContent(String content){
    this.content = content;
  }

  @Default // fetch 옵션은 로드 관련 개선이라서, 보통 필수가 아니지만 여기선 반필수.
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "note", cascade = CascadeType.ALL, orphanRemoval = true)
  @Setter
  private List<Attach> attachs = new ArrayList<>();
}
