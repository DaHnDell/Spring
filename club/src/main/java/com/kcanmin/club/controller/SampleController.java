package com.kcanmin.club.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.log4j.Log4j2;
import com.kcanmin.club.security.dto.AuthMemberDTO;

@Controller
@Log4j2
@RequestMapping("sample")
public class SampleController {
  
  @GetMapping("all")
  public void all(@AuthenticationPrincipal AuthMemberDTO dto){
    log.info("/all Start ::::::::::::::::::::::::::::::");
    log.info(dto);
  }
  
  @GetMapping("member")
  public void exMember(@AuthenticationPrincipal AuthMemberDTO dto){
    log.info("/member Start ::::::::::::::::::::::::::::::");
    log.info(dto);
  }
  
  @GetMapping("admin")
  @PreAuthorize("hasRole('ADMIN')")
  public void exAdmin(@AuthenticationPrincipal AuthMemberDTO dto){
    log.info("/Admin Start ::::::::::::::::::::::::::::::");
    log.info(dto);
  }

  @GetMapping("api")
  @PreAuthorize("isAuthenticated()")
  // @PreAuthorize("isAnnonymous()")
  @ResponseBody
  public AuthMemberDTO getMethodName(@AuthenticationPrincipal AuthMemberDTO dto){
    log.info(dto);
    return dto;
  }

  @GetMapping("exMemberOnly")
  @ResponseBody
  @PreAuthorize("#dto != null && #dto.username eq\"user100@kcanmin.com\"")
  public String ex(@AuthenticationPrincipal AuthMemberDTO dto) {
    log.info("ex 스타트!~"+dto.getUsername());
    return dto.getEmail();
  }
}
