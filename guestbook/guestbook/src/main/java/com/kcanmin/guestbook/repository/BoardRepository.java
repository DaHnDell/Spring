package com.kcanmin.guestbook.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kcanmin.guestbook.domain.entity.BoardEntity;
import com.kcanmin.guestbook.repository.search.SearchBoardRepository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>, SearchBoardRepository{
  @Query("select b, m from tbl_board b left join member m where b.bno = :bno")
  Object getBoardWithMember(@Param("bno") Long bno);

  @Query("SELECT b, r from tbl_board b left join tbl_reply r on b = r.board where bno = :bno")
  List<Object[]> getBoardWithReply(@Param("bno") Long bno);

  @Query(value = "select count(r), b, m \r\n" + //
        "FROM tbl_board b \r\n" + // 단위가 언제나 엔티티가 된다. 
        "left join member m \r\n" + //
        "left join tbl_reply r on b = r.board \r\n" + //
        "group by b"
  , countQuery = "select count(b) from tbl_board b") // jpa의 특징인 모든 데이터베이스에 접근 가능하게 하는 자유로움이 사라진다.
  Page<Object[]> getBoardWithReplyCount(Pageable pageable);

  //tbl_board에 있는 갯수를 세 오는데 반드시 별칭 줘서 세 올 것.
  @Query("select b, count(r)  \r\n" + //
            "from tbl_board b \r\n" + //
            "left join tbl_reply r on b = r.board\r\n" + //
            "where b.bno = :bno")
  Object getBoardByBno(@Param("bno") Long bno);
}
