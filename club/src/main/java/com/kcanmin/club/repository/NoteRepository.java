package com.kcanmin.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcanmin.club.entity.Note;
import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Long>{
  
  // 번호로 노트 가져오기
  Note findByNum(Long num); // findById => 결과값이 Optional이 됨. Orelse 등의 후처리가 필요해지기 때문에 귀찮아서 바로 이렇게 함.

  // 회원번호로 검색
  List<Note> findByMemberMno(Long mno);

  // email로 검색
  List<Note> findByMemberEmail(String email);

}
