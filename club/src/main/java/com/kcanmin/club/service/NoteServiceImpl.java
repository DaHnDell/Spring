package com.kcanmin.club.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.kcanmin.club.entity.Attach;
import com.kcanmin.club.entity.Likes;
import com.kcanmin.club.entity.Member;
import com.kcanmin.club.entity.Note;
import com.kcanmin.club.entity.dto.NoteDTO;
import com.kcanmin.club.repository.AttachRepository;
import com.kcanmin.club.repository.LikesRepository;
import com.kcanmin.club.repository.MemberRepository;
import com.kcanmin.club.repository.NoteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class NoteServiceImpl implements NoteService{

  @Autowired
  private NoteRepository repository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private AttachRepository attachRepository;

  @Autowired
  private LikesRepository likesRepository;

  @Override
  public Optional<NoteDTO> get(Long num) {
    log.info("note Get start =========================");    
    // Note note = repository.findByNum(num);
    long count = likesRepository.count(Example.of(Likes.builder().note(Note.builder().num(num).build()).build()));
    log.info("note Get End ===========================");    
    return repository.findById(num).map(this::toDTO).map(d-> {d.setLikesCnt(count); return d;});
    // return EntityToDTO(note);
  }
  
  @Override
  public List<NoteDTO> list(String email) {
    return repository.findNotesBy(email).stream().map(o->{
      NoteDTO dto = toDTO((Note)o[0]);
      dto.setLikesCnt((Long)o[1]);
      dto.setAttachCnt((Long)o[2]);
      return dto;
    }).toList();
    // List<Note> returnList = repository.findByMemberEmail(email);
    // return returnList.stream().map(this::toDTO).toList();
  }
  
  @Override
  @Transactional
  public void modify(NoteDTO noteDTO) {
    Note note = toEntity(noteDTO);
    // Member member = memberRepository.
    Member member = memberRepository.findByEmail(noteDTO.getWriterEmail());
    noteDTO.getAttachDtos().forEach(a -> {
      Attach attach = Attach.builder()
      .uuid(a.getUuid())
      .origin(a.getOrigin())
      .image(a.isImage())
      .path(a.getPath())
      .note(note) // 영속화된 Note 설정
      .size(a.getSize())
      .mime(a.getMime())
          .fileName(a.getFileName())
          .ext(a.getExt())
          .url(a.getUrl())
          .build();
      attachRepository.save(attach); // 개별 Attach 저장
  });
    note.setMember(member);
    memberRepository.save(member);
    repository.save(note);
  }

  
  // @Override
  // public Long register(NoteDTO noteDTO) {
  //   log.info("Long register start ========================="); 
  //   log.info(noteDTO);
  //   Member member = memberRepository.findByEmail(noteDTO.getWriterEmail());
  //   log.info(saveNoteWithAttachments(noteDTO));
  //   noteDTO.setMno(member.getMno());
  //   log.info("Long register End ===========================");    
  //   // Note note = dtoToEntity(noteDTO);
  //   // log.info(note);
  //   return null; //repository.save(dtoToEntity(noteDTO)).getNum();
  // }

//   @Override
// @Transactional
// public Long register(NoteDTO noteDTO) {
//     log.info("Long register start =========================");
//     log.info(noteDTO);

//     // Member 설정
//     Member member = memberRepository.findByEmail(noteDTO.getWriterEmail());
//     noteDTO.setMno(member.getMno());

//     // Note 엔티티 생성
//     Note note = dtoToEntity(noteDTO);
//     note.setMember(member);

//     // Attach DTO -> Attach 엔티티 변환 및 Note에 추가
//     noteDTO.getAttachDtos().forEach(a -> {
//         Attach attach = Attach.builder()
//             .uuid(a.getUuid()) // UUID 확인
//             .origin(a.getOrigin())
//             .image(a.isImage())
//             .path(a.getPath())
//             .note(note) // Note와 연관 관계 설정
//             .size(a.getSize())
//             .mime(a.getMime())
//             .fileName(a.getFileName())
//             .ext(a.getExt())
//             .url(a.getUrl())
//             .build();
//         note.getAttachs().add(attach); // Note의 attachs 리스트에 추가
//     });

//     // Note 저장 (CascadeType.ALL 설정으로 Attach도 저장)
//     Note savedNote = repository.save(note);

//     log.info("Long register End =========================");
//     return savedNote.getNum();
// }

  

//   public Note saveNoteWithAttachments(NoteDTO noteDTO) {
//     Note note = dtoToEntity(noteDTO);
//     repository.save(note); // Note를 먼저 저장하여 영속화

//     // Attach 객체에 영속화된 Note 설정
//     noteDTO.getAttachDtos().forEach(a -> {
//         Attach attach = Attach.builder()
//             .uuid(a.getUuid())
//             .origin(a.getOrigin())
//             .image(a.isImage())
//             .path(a.getPath())
//             .note(note) // 영속화된 Note 설정
//             .size(a.getSize())
//             .mime(a.getMime())
//             .fileName(a.getFileName())
//             .ext(a.getExt())
//             .url(a.getUrl())
//             .build();
//         attachRepository.save(attach); // 개별 Attach 저장
//     });
//     return note;
// }



@Override
@Transactional
public Long register(NoteDTO noteDTO) {
  Note note = toEntity(noteDTO);
  Member member = memberRepository.findByEmail(noteDTO.getWriterEmail());
  note.setMember(member);
  noteDTO.getAttachDtos().forEach(a -> {
      Attach attach = Attach.builder()
          .uuid(a.getUuid())
          .origin(a.getOrigin())
          .image(a.isImage())
          .path(a.getPath())
          .note(note) // 영속화된 Note 설정
          .size(a.getSize())
          .mime(a.getMime())
          .fileName(a.getFileName())
          .ext(a.getExt())
          .url(a.getUrl())
          .build();
      attachRepository.save(attach); // 개별 Attach 저장
  });
  repository.save(note);
  Long returnLong = note.getNum();
  return returnLong;
}

  
  @Override
  public void remove(Long num) {
    log.info("void remove start =========================");    
    repository.deleteById(num);    
    log.info("void remove End ===========================");    
  }

  @Override
  public List<NoteDTO> allList() {
    return repository.findNotes().stream().map(o->{
      NoteDTO dto = toDTO((Note)o[0]);
      dto.setLikesCnt((Long)o[1]);
      dto.setAttachCnt((Long)o[2]);
      return dto;
    }).toList();
    // return repository.findAll().stream().map(this::toDTO).toList();
  }
  
}
