package com.kcanmin.club.entity;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.kcanmin.club.entity.composite.LikesId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tbl_likes")
@EnableJpaRepositories
@IdClass(LikesId.class)
@Getter
public class Likes extends BaseEntity{

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  private Note note;
}
