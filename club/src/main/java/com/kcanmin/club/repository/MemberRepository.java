package com.kcanmin.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcanmin.club.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
  Member findByEmail(String email);
  Member findByEmailAndFromSocial(String email, Boolean fromSocial);
}
