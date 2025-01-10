package com.kcanmin.club.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User{

  private Long mno;
  private String email;
  private String name;
  private Boolean fromSocial;

  public AuthMemberDTO(String username, String password, Long mno, Boolean fromSocial, String name, Collection<? extends GrantedAuthority> authorities){
    super(username, password, authorities);
    this.mno = mno;
    this.email = username;
    this.fromSocial = fromSocial;
    this.name = name;
  }
}
