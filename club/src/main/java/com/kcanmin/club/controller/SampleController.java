package com.kcanmin.club.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;
import com.kcanmin.club.security.dto.AuthMemberDTO;


@Controller
@Log4j2
@RequestMapping("/sample/")
public class SampleController {
  
  @GetMapping("/all")
  public void all(@AuthenticationPrincipal AuthMemberDTO dto){
    log.info("/all Start ::::::::::::::::::::::::::::::");
  }

  @GetMapping("/member")
  public void exMember(@AuthenticationPrincipal AuthMemberDTO dto){
    log.info("/member Start ::::::::::::::::::::::::::::::");
  }

  @GetMapping("/admin")
  public void exAdmin(@AuthenticationPrincipal AuthMemberDTO dto){
    log.info("/Admin Start ::::::::::::::::::::::::::::::");
  }

  @GetMapping("api")
  @ResponseBody
  public AuthMemberDTO getMethodName(@AuthenticationPrincipal AuthMemberDTO dto){
    return dto;
  }

}
