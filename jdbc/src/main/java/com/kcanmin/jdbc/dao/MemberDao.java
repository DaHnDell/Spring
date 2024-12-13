package com.kcanmin.jdbc.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.kcanmin.jdbc.vo.Member;

@Component
public class MemberDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public String getTime(){
    return jdbcTemplate.queryForObject("select now()", String.class);
  }

  public int register(Member member){
    return jdbcTemplate.update("insert into tbl_member (id, pw, name) values(?, ?, ?)", member.getId(), member.getPw(), member.getName());
  }

  public List<Member> selectList(){
    return jdbcTemplate.query("select * from tbl_member", new MyMapper());
  }

  // public Map<String, Object> selectMember(String id){
  //   return jdbcTemplate.queryForMap("select * from tbl_member where id = ?", new Object[]{id});
  // }

  public Member selectMember(String id){
    return jdbcTemplate.queryForObject("select * from tbl_member where id = ?", new MyMapper(), id);
  }

  public int delete(String id){
    return jdbcTemplate.update("delete from tbl_member where id = ? ", id);
  }

  public int update(Member member){
    return jdbcTemplate.update("update tbl_member set pw = ?, name = ?, email = ?, road_addr = ?, detail_addr = ? where id = ?", member.getPw(), member.getName(), member.getEmail(), member.getRoadAddr(), member.getDetailAddr(), member.getId());
  }
  // public int update(Member member, Object[] obj){
  //   return jdbcTemplate.update("update tbl_member set pw = ?, name = ?, email = ?, road_addr = ?, detail_addr = ? regdate = now() where id = ?", obj[0], obj[1], obj[2], obj[3], obj[4], member.getId());
  // }

  // 빨간줄은 추상메서드 구현 여부, 노란줄은 제네릭 미표현
  class MyMapper implements RowMapper<Member> {

    @Override
    @Nullable
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
      return Member
      .builder()
      .id(rs.getString("id"))
      .pw(rs.getString("pw"))
      .name(rs.getString("name"))
      .email(rs.getString("email"))
      .roadAddr(rs.getString("road_addr"))
      .detailAddr(rs.getString("detail_addr"))
      .regDate(rs.getTimestamp("regdate").toLocalDateTime())
      .build();   
    }


    
  }
}
