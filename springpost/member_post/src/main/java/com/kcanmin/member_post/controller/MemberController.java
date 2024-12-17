package com.kcanmin.member_post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.kcanmin.member_post.service.MemberService;
import com.kcanmin.member_post.vo.Member;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Log4j2
@Controller
@RequestMapping("member")
@AllArgsConstructor
public class MemberController {
  // private MemberService service
  InternalResourceViewResolver resolver;

  @GetMapping("mv")
  public ModelAndView mvR(ModelAndView mv){
    mv.addObject("test", "abcd");
    mv.setViewName("common/index");
    log.info(mv);
    return mv;
  }

  @RequestMapping(value = {"", "*"}, method = RequestMethod.GET) @ResponseBody
  public Member all() {
    // DispatcherServlet servlet;
    log.info(resolver);
    log.info(resolver.getAttributesMap());
    log.info(resolver.getOrder());
    return new Member();
  }
 
  @RequestMapping(value = {"", "*"}, method = RequestMethod.PUT) @ResponseBody
  public Member all2() {
    return new Member(); // ㅇㅇ
  }
  
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
  public String postSignIn(Member member, @RequestParam(required = false, value = "remember-id") String remember, HttpSession session, RedirectAttributes rttr, HttpServletResponse resp) { // 리다이렉트 문은 rttr을 많이 사용하긴 함.
    // 옵셔널 = null + 기본값
    // required = 기본값, value = 탐색할 value 가 있는 대상의 name
    // @Nullable 널 가능 여부 추가
    
    // get 방식의 메서드를 활용할 경우에 post 와 리다이렉션(새로고침 등) 의 문제가 발생하지 않음.

    log.info(remember);  
    if(memberService.login(member.getId(), member.getPw())){
      // 성공
      session.setAttribute("member", memberService.findBy(member.getId()));
      Cookie cookie = new Cookie("remember-id", member.getId());
      cookie.setPath("/");
      // id 저장 시 cookie 에 remember-id 를 지정
      if(remember != null){
        cookie.setMaxAge(60*60*24*7);
      }else{
        cookie.setMaxAge(0);
      }
      resp.addCookie(cookie);

      // return index
      return "redirect:/";
    }else{
      // 실패
      // return "redirect:/member/signin";
      // return "redirect:signin?msg=failed";
      // model.addAttribute("msg", "failed"); // 모델의 경우 일반적인 포워딩을 위함. 
      // rttr.addAttribute("msg", "failed"); // rttr의 경우 모델이 해야 하는 일과 정확히 같은 규칙의 일을 수행한다. 
      rttr.addFlashAttribute("msg", "failed"); // 반짝 속성!?! // 실제 1회성의 세션 어트리뷰트와 비슷,,,,
      return "redirect:signin";
    }

    // 세션, 쿠키의 정보를 활용하여 사용자 편의성을 증진시킬 수 있는 인터페이스가 스프링에 구현되어 있음.
    
    // log.info(member);
    // return null; 
  }
  
  private MemberService service;

  @RequestMapping("logout")
  public String requestMethodName(HttpSession session){
    session.invalidate();
    return "redirect:";
  }

  @GetMapping("signup")
  public void signup() { }

  @PostMapping("signup")
  public String postSignup(Member member) {
      log.info(member);
      service.register(member);
      return "redirect:signin";
  }
  
  

  
  
}
