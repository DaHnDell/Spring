package com.kcanmin.member_post.aop.aspect;

// import java.io.IOException;
// import java.net.URLEncoder;
import java.util.Arrays;

import com.kcanmin.member_post.Exception.NotMyPostException;
import com.kcanmin.member_post.Exception.UnsignedAuthException;
 
// import org.aspectj.lang.JoinPoint;
// // import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
// import org.springframework.stereotype.Component;

import com.kcanmin.member_post.vo.Member;
// import com.kcanmin.member_post.vo.Post;
import com.kcanmin.member_post.vo.Post;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.http.HttpSession;
// import lombok.AllArgsConstructor;
// import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URLEncoder;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


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
  private HttpSession session;
  private HttpServletResponse resp;
  private HttpServletRequest req;

   @Before("@annotation(com.kcanmin.member_post.aop.MyPost)")
  public void myPost(JoinPoint joinPoint) throws IOException{
    log.info("마이포스트 어노테이션 시작. 삭제 및 수정여부?!?!?");
    Object o = session.getAttribute("member");
    if(o == null){
      throw new UnsignedAuthException("본인 게시글 아님"); 
    }
    String id = ((Member) o).getId();

    Object[] args = joinPoint.getArgs();
    for(Object obj : args){
      if(obj instanceof Post && !((Post)obj).getWriter().equals(obj)){
        throw new NotMyPostException("본인 게시글 아님");    
      }
    }
    // String writeParam = myPost.value();
    log.info(Arrays.toString(args));
    log.info(id);
    // log.info(writeParam);

    // if(o == null || ((Member)o).getId().equals(post.getWriter())) {
    //   throw new RuntimeException("본인 게시글 아님");
    // }
  }

  // @Before("write()")
  @Before("@annotation(com.kcanmin.member_post.aop.SigninCheck)")
  public void signinCheck(JoinPoint jp) throws IOException {
    log.info(req.getRequestURL());
    log.info(req.getRequestURI());
    log.info(req.getQueryString());
    log.info(session.getAttribute("member"));
    // if(session.getAttribute("member") == null) {
    //   log.error(url);
    //   resp.sendRedirect("/msg?msg=" + URLEncoder.encode("로그인이 필요한 서비스입니다.", "utf-8") + "&url=" + url);
    //   return;
    // }
    if (session.getAttribute("member") == null ){
      String url = "/member/signin?url=" + URLEncoder.encode(req.getRequestURI() + "?" + req.getQueryString(), "utf-8");
      resp.sendRedirect("/msg?msg=" + URLEncoder.encode("로그인이 필요한 페이지 입니다.", "utf-8")+ "&url="+url);

    }
    // else{
    //   resp.sendRedirect(req.getRequestURL() + req.getQueryString());
    // }
    // }
  } 
}