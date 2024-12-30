package com.kcanmin.guestbook.service;

import org.aspectj.weaver.Member;

import com.kcanmin.guestbook.domain.dto.BoardDto;
import com.kcanmin.guestbook.domain.entity.BoardEntity;
import com.kcanmin.guestbook.domain.entity.MemberEntity;

public interface BoardService {
  default BoardEntity toEntity(BoardDto boardDto){
    return BoardEntity.builder()
    .bno(boardDto.getBno())
    .title(boardDto.getTitle())
    .content(boardDto.getContent())
    .member(MemberEntity.builder().email(boardDto.getMemberEmail()).name(boardDto.getMemberName()).build())
    .build();
  }

  default BoardDto toDto(Object[] arr){
    if(arr == null) return null;
    BoardDto.BoardDtoBuilder builder = BoardDto.builder(); 
    for(Object o : arr){
      Class<?> claz = o.getClass();
      if(claz == int.class || claz == Integer.class){
        builder.replyCnt(Integer.parseInt(o.toString()));
      }else if(claz == MemberEntity.class){
        builder.memberEmail(((MemberEntity) o).getEmail());
        builder.memberName(((MemberEntity) o).getName());
      }else if(claz == BoardEntity.class){
        BoardEntity b = (BoardEntity) o;
        builder.bno(b.getBno());
        builder.title(b.getTitle());
        builder.content(b.getContent());
        builder.regDate(b.getRegDate());
        builder.modDate(b.getModDate());
      }
    }
    return builder.build();
      // switch (claz) {
      //   case value:
      //     break;
      //   default:
      //     break;
      // }
  }

  Long register(BoardDto dto);

  BoardDto get(Long bno);
}