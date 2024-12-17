package com.kcanmin.member_post.aop;

import java.io.IOException;
import java.net.URLEncoder;

import org.aspectj.lang.JoinPoint;
// import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.kcanmin.member_post.vo.Member;
import com.kcanmin.member_post.vo.Post;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@AllArgsConstructor
@Log4j2
public class AuthAspect {
  private HttpSession httpSession;
  private HttpServletResponse resp;
  private HttpServletRequest req;

  @Before("@annotation(com.kcanmin.member_post.aop.MyPost)")
  public void myPost(JoinPoint joinPoint, Post post){
    Object o = httpSession.getAttribute("member");
    if(o==null || !((Member)o).getId().equals(post.getWriter())){
      throw new RuntimeException("not your post; invalid approach");
    }
  }
  @Before("@annotation(com.kcanmin.member_post.aop.SigninCheck)")
  public void SigninCheck(JoinPoint joinPoint) throws IOException{
    // log.info(req.getRequestURI());
    // log.info(req.getRequestURL());
    // log.info(req.getQueryString());
    if(httpSession.getAttribute("member") == null){
      String url = "/member/signin?url=" + URLEncoder.encode(req.getRequestURI() +  "?" + req.getQueryString(), "utf-8");
      resp.sendRedirect("/msg?msg=" + URLEncoder.encode("plz, Log in first", "utf-8")+"&url="+ url);
    }
  }
}
