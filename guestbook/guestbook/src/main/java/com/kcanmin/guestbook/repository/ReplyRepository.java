package com.kcanmin.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcanmin.guestbook.domain.entity.ReplyEntity;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long>{
  
}
