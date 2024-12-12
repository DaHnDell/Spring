package com.kcanmin.jdbc.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
}
