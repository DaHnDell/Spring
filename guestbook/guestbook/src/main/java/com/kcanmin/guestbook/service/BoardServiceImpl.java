package com.kcanmin.guestbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcanmin.guestbook.domain.dto.BoardDto;
import com.kcanmin.guestbook.domain.entity.BoardEntity;
import com.kcanmin.guestbook.repository.BoardRepository;

import lombok.Data;

@Service
@Data
public class BoardServiceImpl implements BoardService{

  @Autowired
  private BoardRepository repository;

  @Override
  public BoardDto get(Long bno) {
    return toDto(repository.getBoardByBno(bno));
  }

  @Override
  public Long register(BoardDto dto) {
    BoardEntity boardEntity = toEntity(dto);
    repository.save(boardEntity);
    return boardEntity.getBno();
  }
  
}
