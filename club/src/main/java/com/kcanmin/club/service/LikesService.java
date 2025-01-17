package com.kcanmin.club.service;

import com.kcanmin.club.entity.Likes;
import com.kcanmin.club.entity.Member;
import com.kcanmin.club.entity.Note;
import com.kcanmin.club.entity.dto.LikesDTO;

public interface LikesService {
  boolean toggle(LikesDTO dto);
  boolean get(LikesDTO dto);
  
    default Likes toEntity(LikesDTO dto){
      Likes likes = Likes
        .builder()
          .member(Member.builder().mno(dto.getMno()).build())
          .note(Note.builder().num(dto.getNum()).build())
        .build();
      return likes;
    }

  default LikesDTO toDTO(Likes likes){
    // Member member = Member.builder().email(
    LikesDTO dto = LikesDTO
      .builder()
        .email(likes.getMember().getEmail())
        .mno(likes.getMember().getMno())
        .modDate(likes.getModDate())
        .regDate(likes.getRegDate())
      .build();
    return dto;
  }
}
