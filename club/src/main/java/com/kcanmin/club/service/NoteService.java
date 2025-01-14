package com.kcanmin.club.service;

import java.util.List;
import java.util.Optional;

import com.kcanmin.club.entity.Member;
import com.kcanmin.club.entity.Note;
import com.kcanmin.club.entity.dto.NoteDTO;

public interface NoteService {
  Long register(NoteDTO noteDTO);

  // findbyNum;
  Optional<NoteDTO> get(Long num);

  List<NoteDTO> list(String email);

  void modify(NoteDTO noteDTO);

  void remove(Long num);

  List<NoteDTO> allList();

  default Note dtoToEntity(NoteDTO noteDTO){
    return Note
      .builder()
        .num(noteDTO.getNum())
        .title(noteDTO.getTitle())
        .content(noteDTO.getContent())
        .member(Member
          .builder()
          .email(noteDTO.getWriterEmail())
          .mno(noteDTO.getMno())
          .build())
      .build();
  }
// RestController Advice (통합 예외 처리) , 전용 예외 처리(404, 403 등등);
  default NoteDTO entityToDTO(Note note){
    return NoteDTO
      .builder()
        .num(note.getNum())
        .title(note.getTitle())
        .content(note.getContent())
        .writerEmail(note.getMember().getEmail())
        .regDate(note.getRegDate())
        .modDate(note.getModDate())
      .build();
  }


}
