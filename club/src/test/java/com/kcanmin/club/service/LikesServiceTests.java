package com.kcanmin.club.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kcanmin.club.entity.Likes;
import com.kcanmin.club.entity.Member;
import com.kcanmin.club.entity.Note;
import com.kcanmin.club.entity.dto.LikesDTO;
import com.kcanmin.club.repository.MemberRepository;
import com.kcanmin.club.repository.NoteRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class LikesServiceTests {
  
  @Autowired
  LikesService service;

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  NoteRepository noteRepository;

  @Test
  public void testGet(){
    // dto
    service.get(LikesDTO.builder().mno(103L).num(54L).build());
  }

  @Test
  public void testToggle(){
    service.toggle(LikesDTO.builder().mno(103L).num(54L).build());
  }

  @Test
  public void testDTO(){
    LikesDTO dto = LikesDTO.builder().mno(103L).num(54L).build();
    Likes likes = service.toEntity(dto);
    
    log.info("dto ::::::::::: " + dto.toString());
    log.info("likes ::::::::::::: " + likes.toString());
  }

  @Test
  public void testAdd(){
    List<Long> mnos = new ArrayList<>(memberRepository.findAll().stream().map(Member::getMno).toList());
    List<Long> nums = new ArrayList<>(noteRepository.findAll().stream().map(Note::getNum).toList());
    // Collections.shuffle(members);
    Collections.shuffle(mnos);
    Collections.shuffle(nums);

    // mnos.subList(0, (int)(mnos.size() * .2));
    List<LikesDTO> likesDtos = new ArrayList<>();

    for(int j = 0; j < mnos.size(); j++){
      for(int i = 0; i < nums.size(); i++){
        likesDtos.add(LikesDTO.builder().mno(mnos.get(j)).num(nums.get(i)).build());
      }
    }
    log.info(likesDtos.size());

    likesDtos = likesDtos.subList(0, likesDtos.size()/5);

    log.info("자르기-======================================");
    log.info(likesDtos.size());

    likesDtos.forEach(dto -> service.toggle(dto));
  }

  
}