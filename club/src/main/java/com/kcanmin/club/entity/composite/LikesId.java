package com.kcanmin.club.entity.composite;

import java.io.Serializable;

import com.kcanmin.club.entity.Member;
import com.kcanmin.club.entity.dto.LikesDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class LikesId implements Serializable{
  private Long member;
  private Long note;

  public LikesId(LikesDTO dto){
    member = dto.getMno();
    note = dto.getNum();
  }
}