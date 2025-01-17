package com.kcanmin.club.service;

import java.util.List;
import java.util.Optional;

import com.kcanmin.club.entity.Attach;
import com.kcanmin.club.entity.Member;
import com.kcanmin.club.entity.Note;
import com.kcanmin.club.entity.dto.AttachDTO;
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
    Note note = Note
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
    note.setAttachs(noteDTO.getAttachDtos().stream().map(a->Attach
    .builder()
      .uuid(a.getUuid())
      .origin(a.getOrigin())
      .image(a.isImage())
      .path(a.getPath())
      .fileName(a.getFileName())
      .ext(a.getExt())
      .mime(a.getMime())
      .url(a.getUrl())
      .size(a.getSize())
      .note(note)
    .build()).toList()
    );
    return note;
  }

  // private String uuid;
  // private String origin;
  // private boolean image;
  // private String path;
  // private Note note;

  // private Long size;
  // private String mime;
  // private String fileName;
  // private String ext;
  // private String url;

// RestController Advice (통합 예외 처리) , 전용 예외 처리(404, 403 등등);
  default NoteDTO entityToDTO(Note note){
    NoteDTO dto = NoteDTO
    .builder()
        .num(note.getNum())
        .title(note.getTitle())
        .content(note.getContent())
        .writerEmail(note.getMember().getEmail())
        .regDate(note.getRegDate())
        .modDate(note.getModDate())
        .attachDtos(note.getAttachs().stream().map(a -> AttachDTO.builder()
          .num(a.getNote().getNum())
          .size(a.getSize())
          .url(a.getUrl())
          .mime(a.getMime())
          .ext(a.getExt())
          .fileName(a.getFileName())
          .path(a.getPath())
          .image(a.isImage())
          .origin(a.getOrigin())
          .uuid(a.getUuid())
          .build()).toList())          
      .build();
      return dto;
  }


}
