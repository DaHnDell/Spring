package com.kcanmin.club.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcanmin.club.entity.composite.LikesId;
import com.kcanmin.club.entity.dto.LikesDTO;
import com.kcanmin.club.repository.LikesRepository;
import com.kcanmin.club.repository.MemberRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class LikesServiceImpl implements LikesService {

  @Autowired
  private LikesRepository repository;

  @Autowired
  private MemberRepository memberRepository;

  @Override
  public boolean get(LikesDTO dto) {
    // toEntity(dto);
    if(dto.getMno() == null){
      Long mno = memberRepository.findByEmail(dto.getEmail()).getMno();
      dto.setMno(mno);
    }
    return repository.findById(new LikesId(dto)).isPresent();
  }

  @Override
  public boolean toggle(LikesDTO dto) {
    if(dto.getMno() == null){
      Long mno = memberRepository.findByEmail(dto.getEmail()).getMno();
      dto.setMno(mno);
    }
    boolean ret = get(dto);
    if(ret){
      repository.delete(toEntity(dto));
    }else{
      repository.save(toEntity(dto));
    }
    return ret;
  }
}
