package com.kcanmin.club.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcanmin.club.entity.Member;
import com.kcanmin.club.repository.MemberRepository;
import com.kcanmin.club.security.dto.AuthMemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class MyMemberUserDetailService implements UserDetailsService{
  @Autowired
  private MemberRepository repository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info(username);
      Member member = repository.findByEmailAndFromSocial(username, false);
      if(member == null){
        throw new UsernameNotFoundException(username);
      }
      AuthMemberDTO authMemberDTO = new AuthMemberDTO(member.getEmail(), member.getPassword(), member.getMno(), member.isFromSocial(), member.getName(),member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).toList());
      return authMemberDTO;
  }

  // @Override
  // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  //   log.info(username);
  //   Member member = repository.findByEmailAndFromSocial(username, false);
  //   if(member == null){
  //     throw new UsernameNotFoundException(username);
  //   }
  //   AuthMemberDTO authMemberDTO = new AuthMemberDTO(member.getEmail(), member.getPassword(), member.getMno(), member.isFromSocial(), member.getName(),member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE :: " + role.name())).toList());
  // }

  


  // @Override
  // public UserDetails loadUserByUserName(String username) throws UsernameNotFoundException{
  //   log.info(username);
  //   return null;
  // }
  
  

}
