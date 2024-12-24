package com.kcanmin.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcanmin.guestbook.domain.entity.GuestbookEntitiy;

// @Repository
public interface GuestbookRepository extends JpaRepository<GuestbookEntitiy, Long> {
  
} 
  


