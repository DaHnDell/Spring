package com.kcanmin.club.service;

import java.util.List;

import com.kcanmin.club.entity.Member;
import com.kcanmin.club.entity.Note;
import com.kcanmin.club.entity.dto.NoteDTO;

public interface NoteService {
  Long register(NoteDTO noteDTO);

  // findbyNum;
  Note get(Long num);

  List<Note> list();

  void modify(NoteDTO noteDTO);

  void remove(Long num);


  default Note dtoToEntity(NoteDTO noteDTO){
    return Note
      .builder()
      .num(null)
      .title(null)
      .content(null)
      .member(Member
        .builder()
        .email(noteDTO.getWriterEmail())
        .build())
      .build();
  }

  default NoteDTO EntityToDTO(Note note){
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
