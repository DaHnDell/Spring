package com.kcanmin.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcanmin.guestbook.domain.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String>{
  
}
