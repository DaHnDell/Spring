package com.kcanmin.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.kcanmin.guestbook.domain.entity.GuestbookEntitiy;

// @Repository
public interface GuestbookRepository extends JpaRepository<GuestbookEntitiy, Long>{
  //, QuerydslPredicateExecutor<GuestbookEntitiy> {
  
  

} 
  


