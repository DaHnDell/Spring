package com.kcanmin.member_post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kcanmin.member_post.service.MemberService;
import com.kcanmin.member_post.vo.Member;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;


@Log4j2
@Controller
@RequestMapping("member")
@AllArgsConstructor
public class MemberController {
  // consumes, headers 는 이후 비동기때 사용.
  // /member/signin 으로 오면 이 메서드를 쓸게~ 하는 어노테이션.
  // return type 
  // String : 해당 위치의 jsp (ex: /WEB-INF/views) 로 foward
  // void : 접속 requestURI 위치를 반환 foward
  private MemberService memberService;
  @RequestMapping(value = "signin", method = RequestMethod.GET)
  public void signIn(){}
  // public String signIn() {
  //     return "member/signin";
  // }
  
  @PostMapping("signin")
  public String postSignIn(Member member, @RequestParam(required = false, value = "remember-id") String remember, HttpSession session) {
    // 옵셔널 = null + 기본값
    // required = 기본값, value = 탐색할 value 가 있는 대상의 name
    // @Nullable 널 가능 여부 추가
    log.info(remember);  
    if(memberService.login(member.getId(), member.getPw())){
      // 성공
      session.setAttribute("member", memberService.findBy(member.getId()));
      return "redirect:/";
    }else{
      // 실패
      return "redirect:member/signin";
    }
    // log.info(member);
    // return null;
  }


  
  
}
