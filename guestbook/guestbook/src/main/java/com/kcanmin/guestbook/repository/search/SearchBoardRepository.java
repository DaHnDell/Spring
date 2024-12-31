package com.kcanmin.guestbook.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kcanmin.guestbook.domain.entity.BoardEntity;

public interface SearchBoardRepository {
  BoardEntity search1();

  Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
  
} 
