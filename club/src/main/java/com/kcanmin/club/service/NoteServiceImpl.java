package com.kcanmin.club.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcanmin.club.entity.Member;
import com.kcanmin.club.entity.Note;
import com.kcanmin.club.entity.dto.NoteDTO;
import com.kcanmin.club.repository.MemberRepository;
import com.kcanmin.club.repository.NoteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{

  @Autowired
  private NoteRepository repository;

  @Autowired
  private MemberRepository memberRepository;

  // @Autowired
  // private MemberRepository memberRepository;

  @Override
  public Optional<NoteDTO> get(Long num) {
    log.info("note Get start =========================");    
    // Note note = repository.findByNum(num);
    log.info("note Get End ===========================");    
    return repository.findById(num).map(this::entityToDTO);
    // return EntityToDTO(note);
  }
  
  @Override
  public List<NoteDTO> list(String email) {
    log.info("List list start =========================");    
    List<Note> returnList = repository.findByMemberEmail(email);
    log.info("List list End ===========================");    
    return returnList.stream().map(this::entityToDTO).toList();
  }
  
  @Override
  public void modify(NoteDTO noteDTO) {
    log.info("void modify start =========================");    
    Long num = noteDTO.getNum();
    Note targetNote = repository.findByNum(num);
    log.info("targetNote ::: " + targetNote);
    targetNote.changeTitle(noteDTO.getTitle());
    log.info("changed Title ::: " + targetNote.getTitle());
    targetNote.changeContent(noteDTO.getContent());
    log.info("changed Content ::: " + noteDTO.getContent());
    log.info("===========================================");
    log.info(targetNote.getTitle() + " ||||| " + targetNote.getContent());
    log.info("===========================================");
    repository.save(targetNote);
    log.info("void modify End ===========================");    
  }
  
  @Override
  public Long register(NoteDTO noteDTO) {
    log.info("Long register start ========================="); 
    Member member = memberRepository.findByEmail(noteDTO.getWriterEmail());
    noteDTO.setMno(member.getMno());
    log.info("Long register End ===========================");    
    return repository.save(dtoToEntity(noteDTO)).getNum();
  }

// @Override
// public Long register(NoteDTO noteDTO) {
//   log.info("Long register start =========================");    
//   Note note = dtoToEntity(noteDTO);

//   Member member = memberRepository.findByEmail(noteDTO.getWriterEmail());
//   note.setMember(member);

//   repository.save(note);
//   Long returnLong = note.getNum();
//   log.info("Long register End ===========================");    
//   return returnLong;
// }

  
  @Override
  public void remove(Long num) {
    log.info("void remove start =========================");    
    repository.deleteById(num);    
    log.info("void remove End ===========================");    
  }

  @Override
  public List<NoteDTO> allList() {
    return repository.findAll().stream().map(this::entityToDTO).toList();
  }
  
}
