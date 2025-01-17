package com.kcanmin.club.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcanmin.club.entity.dto.LikesDTO;
import com.kcanmin.club.repository.LikesRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class LikesServiceImpl implements LikesService {


  @Autowired
  private LikesRepository repository;

  @Override
  public boolean get(LikesDTO dto) {
    toEntity(dto);
    // repository.findById(null).isPresent();
    return repository.findById(null).isPresent();
  }

  @Override
  public void toggle(LikesDTO dto) {
    if (get(dto) ){
      repository.delete(toEntity(dto));
    }else{
      repository.save(toEntity(dto));
    }
    toEntity(dto);
  }
  
}
