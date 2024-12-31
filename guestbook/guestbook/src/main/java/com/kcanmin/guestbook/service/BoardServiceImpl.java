package com.kcanmin.guestbook.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.kcanmin.guestbook.domain.dto.BoardDto;
import com.kcanmin.guestbook.domain.dto.GuestbookDTO;
import com.kcanmin.guestbook.domain.dto.PageRequestDTO;
import com.kcanmin.guestbook.domain.dto.PageResultDTO;
import com.kcanmin.guestbook.domain.entity.BoardEntity;
import com.kcanmin.guestbook.domain.entity.GuestbookEntitiy;
import com.kcanmin.guestbook.repository.BoardRepository;
import com.kcanmin.guestbook.repository.ReplyRepository;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

@Service
@Data
public class BoardServiceImpl implements BoardService{

  @Autowired
  private BoardRepository repository;

  @Autowired
  private ReplyRepository replyRepository;

  @Override
  public BoardDto get(Long bno) {
    return toDto((Object[])repository.getBoardByBno(bno));
  }

  @Override
  public Long register(BoardDto dto) {
    BoardEntity boardEntity = toEntity(dto);
    repository.save(boardEntity);
    return boardEntity.getBno();
  }

  @Override
  public void modify(BoardDto dto) {
    repository.save(toEntity(dto));
  }

  @Override
  public void remove(Long bno) {
    replyRepository.deleteByBoardBno(bno);
    repository.deleteById(bno);
  }

  @Override
  public PageResultDTO<BoardDto, Object[]> list(PageRequestDTO dto) {
    Pageable pageable = dto.getPageable(Sort.by(Direction.DESC, "bno"));
    Page<Object[]> page = repository.searchPage(dto.getType(), dto.getKeyword(), pageable);
    Function<Object[], BoardDto> function = e-> toDto(e);
    PageResultDTO<BoardDto, Object[]> resultDTO = new PageResultDTO<>(page, function);
    return resultDTO;
  }

  
  
}
