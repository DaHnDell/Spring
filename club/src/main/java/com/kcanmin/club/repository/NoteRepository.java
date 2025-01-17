package com.kcanmin.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcanmin.club.entity.Note;
import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Long>{
  
  // 번호로 노트 가져오기
  Note findByNum(Long num); // findById => 결과값이 Optional이 됨. Orelse 등의 후처리가 필요해지기 때문에 귀찮아서 바로 이렇게 함.

  // 회원번호로 검색
  List<Note> findByMemberMno(Long mno);

  // email로 검색
  List<Note> findByMemberEmail(String email);

  @Query("select n, count(distinct l) as likescnt, count(distinct a) as attachcnt from tbl_note n left join tbl_likes l on l.note.num = n.num left join tbl_attach a on a.note.num = n.num where n.member.email = :email group by n")
  // @Query(value = "SELECT n.*, COUNT(DISTINCT l.note_num, l.member_mno), COUNT(DISTINCT uuid) attachment\r\n" + //
  //       "from tbl_note n\r\n" + //
  //       "left join tbl_member m on m.mno = n.member_mno and email = :email \r\n" + //
  //       "left join tbl_likes l on l.note_num  = n.num \r\n" + //
  //       "left join tbl_attach a on a.note_num = n.num \r\n" + //
  //       "where email = \"user101@kcanmin101.com\"\r\n" + //
  //       "group by n.num;", nativeQuery = true)
  List<Object[]> findNotesBy(@Param("email")String email);

  @Query("select n, count(distinct l) as likescnt, count(distinct a) as attachcnt from tbl_note n left join tbl_likes l on l.note.num = n.num left join tbl_attach a on a.note.num = n.num group by n")
  List<Object[]> findNotes();

}
